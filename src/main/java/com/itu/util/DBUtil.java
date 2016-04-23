package com.itu.util;

import com.itu.mdObjects.RAvail;
import com.itu.mdObjects.User;
import com.itu.metadata.FlywayDataSource;
import java.sql.*;
import java.sql.Date;
import java.util.*;


public class DBUtil {
    private FlywayDataSource dataSource;

    public DBUtil(FlywayDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public RAvail returnAvailableRooms(Date checkIn, int numberOfDays, String roomType) {

        Connection dbConnection = null;
        PreparedStatement preparedStmt;
        RAvail rAvail = null;
        Calendar c = Calendar.getInstance();
        StringBuilder sb = new StringBuilder();
        sb.append("select room_type, price, count(room_number) as count ");
        sb.append("from room_type, room_info where room_info.room_type_id=room_type.id and room_number IN ");
        sb.append("((select room_number from room_info) MINUS ");
        sb.append("(select room_number from reservation_details ");
        sb.append("where booking_for_date IN (");

        for (int i=0 ;i<numberOfDays; i++) {
            c.setTime(checkIn);
            c.add(Calendar.DATE, i);
            sb.append("'"+ new Date(c.getTimeInMillis()).toString() + "'"+((i==numberOfDays-1) ? "":","));
        }

        sb.append("))) and room_type=?");
        try {
            dbConnection = this.dataSource.getConnection();
            preparedStmt = dbConnection.prepareStatement(sb.toString());
            preparedStmt.setString(1,roomType);
            ResultSet resultSet = preparedStmt.executeQuery();
            rAvail = new RAvail();
            if(resultSet.next()) {
                rAvail.setRoomType(resultSet.getString("room_type"));
                rAvail.setPrice(resultSet.getInt("price"));
                rAvail.setCount(resultSet.getInt("count"));
            }
            resultSet.close();
            preparedStmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return rAvail;
    }


    public boolean makeReservation(User user, Date checkIn, int numberOfDays, String roomType) {

        Connection dbConnection = null;
        PreparedStatement preparedStmt;
        int rows=0,userid=0;
        int room = 0;
        boolean retVal=false;
        String conf_code=null;
        Calendar c = Calendar.getInstance();
        StringBuilder sb = new StringBuilder();
        sb.append("select room_number as count ");
        sb.append("from room_type, room_info where room_info.room_type_id=room_type.id and room_number IN ");
        sb.append("((select room_number from room_info) MINUS ");
        sb.append("(select room_number from reservation_details ");
        sb.append("where booking_for_date IN (");

        for (int i=0 ;i<numberOfDays; i++) {
            c.setTime(checkIn);
            c.add(Calendar.DATE, i);
            sb.append("'"+ new Date(c.getTimeInMillis()).toString() + "'"+((i==numberOfDays-1) ? "":","));
        }

        sb.append("))) and room_type=?");
        try {
            dbConnection = this.dataSource.getConnection();
            preparedStmt = dbConnection.prepareStatement(sb.toString());
            preparedStmt.setString(1,roomType);
            ResultSet resultSet = preparedStmt.executeQuery();
            if(resultSet.next()) {
                room = resultSet.getInt("room_number");
            }
            resultSet.close();
            preparedStmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        //adding to DB

        try {

            dbConnection = this.dataSource.getConnection();
            dbConnection.setAutoCommit(false);
            preparedStmt = dbConnection.prepareStatement("insert into user_info (first_name, last_name, address, email) " +
                    "VALUES (?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
            preparedStmt.setString(1,user.getFirstName());
            preparedStmt.setString(2,user.getLastName());
            preparedStmt.setString(3,user.getAddress());
            preparedStmt.setString(4,user.getEmail());

            rows += preparedStmt.executeUpdate();
            ResultSet generatedKeysRs = preparedStmt.getGeneratedKeys();
            if(generatedKeysRs.next()) {
                userid = generatedKeysRs.getInt(1);
            }
            generatedKeysRs.close();
            preparedStmt.close();



            preparedStmt = dbConnection.prepareStatement("INSERT INTO reservation_details " +
                    "(confirmation_code, user_id, booking_for_date, room_number, status) " +
                    "VALUES (?,?,?,?,?)");

            conf_code = UUID.randomUUID().toString();
            for (int i=0 ;i<numberOfDays; i++) {
                c.setTime(checkIn);
                c.add(Calendar.DATE, i);
                preparedStmt.setString(1, conf_code);
                preparedStmt.setInt(2,userid);
                preparedStmt.setDate(3,new Date(c.getTimeInMillis()));
                preparedStmt.setInt(4,room);
                preparedStmt.setInt(5, 1);
                preparedStmt.addBatch();

            }


            int[] atts = preparedStmt.executeBatch();
            rows +=atts.length;

            preparedStmt.close();

            if (rows == (1+numberOfDays)) {
                dbConnection.commit();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return retVal;

    }

    public List<String> listOfRoomTypes() {

        Connection dbConnection = null;
        PreparedStatement preparedStmt;
        List<String> roomTypes = new ArrayList<String>();

        try {
            dbConnection = this.dataSource.getConnection();
            preparedStmt = dbConnection.prepareStatement("select room_type from room_type");
            ResultSet resultSet = preparedStmt.executeQuery();
            while (resultSet.next()) {
                roomTypes.add(resultSet.getString("room_type"));
            }
            resultSet.close();
            preparedStmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return roomTypes;
    }

    public boolean cancelReservation(String confirmation_code) {

        int id = 0;
        Connection dbConnection = null;
        PreparedStatement preparedStmt;
        try {
            dbConnection = this.dataSource.getConnection();
            dbConnection.setAutoCommit(false);

            preparedStmt = dbConnection.prepareStatement("update reservation_details set status=? where confirmation_code=?");
            preparedStmt.setInt(1, 0);
            preparedStmt.setString(2, confirmation_code);
            id = preparedStmt.executeUpdate();
            preparedStmt.close();


            if (id < 0) {
                dbConnection.commit();
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        finally {
            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        return false;
    }

    public void checkReservation(String confirmation_code) {

    }
}
