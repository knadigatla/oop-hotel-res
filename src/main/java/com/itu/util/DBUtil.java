package com.itu.util;

import com.itu.metadata.FlywayDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;


public class DBUtil {
    private FlywayDataSource dataSource;

    DBUtil(FlywayDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void returnAvailableRooms() {

        HashMap<String, Object> result = new HashMap<String, Object>();
        Connection dbConnection = null;
        PreparedStatement preparedStmt;
        try {
            dbConnection = this.dataSource.getConnection();

            preparedStmt = dbConnection.prepareStatement("select field_name, field_type, field_value, stage_title from "+
                    "plumber_attributes, plumber_recursivemodel where plumber_attributes.id = plumber_recursivemodel.id "+
                    "and plumber_attributes.id = ?");
            preparedStmt.setInt(1, id);
            ResultSet resultSet = preparedStmt.executeQuery();

            if(resultSet.next()) {
                AttributeO attribute = new AttributeO();
                attribute.setFieldName(resultSet.getString("field_name"));
                attribute.setFieldType(resultSet.getString("field_type"));
                attribute.setFieldValue(resultSet.getString("field_value"));
                result.put("attribute "+Integer.toString(id), attribute);
                result.put("modelTitle",resultSet.getString("stage_title"));
            }
            resultSet.close();
            preparedStmt.close();

        } catch (SQLException e) {
            e.printStackTrace();
            result.put("error code", e.getErrorCode());
            result.put("error", e.getMessage().substring(0, e.getMessage().indexOf(':')));
        }
        finally {
            if (dbConnection != null) {
                try {
                    dbConnection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    result.put("error code", e.getErrorCode());
                    result.put("error", e.getMessage().substring(0,e.getMessage().indexOf(':')));
                }
            }
        }


        return result;
    }

}
