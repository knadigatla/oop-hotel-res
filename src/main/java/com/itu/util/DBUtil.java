package com.itu.util;

import com.itu.mdObjects.RAvail;
import com.itu.metadata.FlywayDataSource;
import java.sql.*;
import java.util.Calendar;
import java.util.HashMap;


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

}
