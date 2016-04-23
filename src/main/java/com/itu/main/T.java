package com.itu.main;

import java.sql.Date;
import java.util.Calendar;
import java.util.Scanner;

/**
 * Created by kiran on 4/22/16.
 */
public class T {

    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
//
//        System.out.println("enter the date: ");
//        String date1 = scnr.nextLine();
////        System.out.println("enter number of days: ");
////        int days = scnr.nextInt();
//
//        Date test = java.sql.Date.valueOf(date1);
////        System.out.println(test.toString());
//
//
//        Calendar c = Calendar.getInstance();
//        StringBuilder sb = new StringBuilder();
//        sb.append("select room_number, room_type, price, details ");
//        sb.append("from room_type, room_info where room_info.room_type_id=room_type.id and room_number IN ");
//        sb.append("((select room_number from room_info) MINUS ");
//        sb.append("(select room_number from reservation_details ");
//        sb.append("where booking_for_date IN (");
//
//        for (int i=0 ;i<3; i++) {
//            c.setTime(test);
//            c.add(Calendar.DATE, i);
//            sb.append("'"+ new Date(c.getTimeInMillis()).toString() + "'"+((i==2) ? "":","));
//        }
//
//        sb.append(")))");
//
//        System.out.println(sb.toString());

        Date checkInDate = java.sql.Date.valueOf("2016-04-24");

        if(checkInDate.compareTo(new java.sql.Date(Calendar.getInstance().getTimeInMillis())) < 0) {
            System.out.println("greater");
        }
        else
            System.out.println("lesser");



    }
}
