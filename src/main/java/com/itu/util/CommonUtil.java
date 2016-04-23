package com.itu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by kiran on 4/23/16.
 */
public class CommonUtil {

    public static boolean isThisDateValid(String dateToValidate, String dateFromat){

        if(dateToValidate == null){
            return false;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(dateFromat);
        sdf.setLenient(false);

        try {

            //if not valid, it will throw ParseException
            Date date = sdf.parse(dateToValidate);
            System.out.println(date);

        } catch (ParseException e) {

//            e.printStackTrace();
            return false;
        }

        return true;
    }

    public static boolean isValidString(String str) {
        if(str == null || str.trim() == "" || str.length() < 3)
            return false;
        else
            return true;
    }
}
