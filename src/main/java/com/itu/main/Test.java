package com.itu.main;

import com.itu.mdObjects.RAvail;
import com.itu.metadata.FlywayDataSourceImpl;
import com.itu.metadata.FlywayMigrations;
import com.itu.util.DBUtil;

import java.beans.PropertyVetoException;


public class Test {

    public static void main(String[] args) {

        try {
            FlywayMigrations fm = new FlywayMigrations(new FlywayDataSourceImpl());

            DBUtil dbUtil = new DBUtil(new FlywayDataSourceImpl());

            RAvail test = dbUtil.returnAvailableRooms(java.sql.Date.valueOf("2016-04-29"),3, "Guest Room King");

            System.out.println(test.getRoomType() + " " + test.getPrice() +" "+ test.getCount());

        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }
}
