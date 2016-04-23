package com.itu.main;

import com.itu.mdObjects.RAvail;
import com.itu.mdObjects.User;
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

            System.out.println(dbUtil.listOfRoomTypes().size());
            System.out.println();
            System.out.println();

            User user = new User();
            user.setFirstName("Kiran");
            user.setLastName("nadigatla");
            user.setAddress("sunnyvale");
            user.setEmail("ki@gmail.com");

            if(dbUtil.makeReservation(user, java.sql.Date.valueOf("2016-04-29"), 3, "Guest Room King"))
                System.out.println("Successfulllllll");
            else
                System.out.println("Failed :(");



        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
    }
}
