package com.itu.main;


import com.itu.mdObjects.RAvail;
import com.itu.mdObjects.Reservation;
import com.itu.mdObjects.User;
import com.itu.metadata.FlywayDataSourceImpl;
import com.itu.metadata.FlywayMigrations;
import com.itu.util.CommonUtil;
import com.itu.util.DBUtil;

import java.beans.PropertyVetoException;
import java.util.*;


public class HotelMain {


    public static void printMainMenu() {


        System.out.println("=======================================================");
        System.out.println("*             HOTEL RESERVATION SYSTEM                *");
        System.out.println("=======================================================");
        System.out.println("* Please select an option from the list:              *");
        System.out.println("*        1. Check Availability and Room Prices        *");
        System.out.println("*        2. Make Reservation                          *");
        System.out.println("*        3. Check Reservation Status                  *");
        System.out.println("*        4. Cancel Reservation                        *");
        System.out.println("*        5. Exit                                      *");
        System.out.println("=======================================================");
        System.out.println("");

    }

    public static void main(String[] args) {

        int userChoice;
        Scanner sc = new Scanner(System.in);
        FlywayMigrations fm = null;
        DBUtil dbUtil = null;
        List<String> roomTypes = null;
        String userSelectedRoomType;


        try {
            fm = new FlywayMigrations(new FlywayDataSourceImpl());
            dbUtil = new DBUtil(new FlywayDataSourceImpl());
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }

        do {
            printMainMenu();



            userChoice = sc.nextInt();

            switch (userChoice) {
                case 1:
                    roomTypes = dbUtil.listOfRoomTypes();
                    int j =1,roomTypeSel;
                    for(int i=0;i<roomTypes.size();i++){
                        System.out.println(j+ " : "+roomTypes.get(i));
                        j++ ;
                    }
                    System.out.println("Choose room type:");
                    roomTypeSel = sc.nextInt();
                    if (roomTypeSel < 1 || roomTypeSel > roomTypes.size()) {
                        System.out.println("Invalid Selection..!!");
                        break;
                    }

                    userSelectedRoomType = roomTypes.get(roomTypeSel-1);

                    System.out.println("Enter check in date [format:yyyy-mm-dd]");
                    String checkInDateEntered = sc.next();
                    if(!CommonUtil.isThisDateValid(checkInDateEntered,"yyyy-MM-dd")) {
                        System.out.println("Invalid Date format please use yyyy-MM-dd");
                        break;
                    }


                    java.sql.Date checkInDate = java.sql.Date.valueOf(checkInDateEntered);

                    if(checkInDate.compareTo(new java.sql.Date(Calendar.getInstance().getTimeInMillis())) <= 0) {
                        System.out.println("You need to book atleast one day in-advance..!!");
                        break;
                    }
                    System.out.println("Enter number of days(Maximum 7 days):");
                    int numDays = sc.nextInt();
                    if(numDays > 7 || numDays < 1) {
                        System.out.println("Invalid Number Of Days, please enter 1-7 days");
                        break;
                    }
                    RAvail RAvailOut = dbUtil.returnAvailableRooms(checkInDate, numDays, userSelectedRoomType);
                    System.out.println("Availability:");
                    System.out.println("ROOM TYPE        :  "+RAvailOut.getRoomType());
                    System.out.println("ROOM PRICE       :  "+RAvailOut.getPrice()+"$");
                    System.out.println("ROOMS AVAILABLE: :  "+RAvailOut.getCount());

                    break;

                case 2:


                    roomTypes = dbUtil.listOfRoomTypes();
                    j =1;
                    for(int i=0;i<roomTypes.size();i++){
                        System.out.println(j+ " : "+roomTypes.get(i));
                        j++ ;
                    }
                    System.out.println("Choose room type:");
                    roomTypeSel = sc.nextInt();
                    if (roomTypeSel < 1 || roomTypeSel > roomTypes.size()) {
                        System.out.println("Invalid Selection..!!");
                        break;
                    }

                    userSelectedRoomType = roomTypes.get(roomTypeSel-1);

                    System.out.println("Enter check in date [format:yyyy-mm-dd]");
                    checkInDateEntered = sc.next();
                    if(!CommonUtil.isThisDateValid(checkInDateEntered,"yyyy-MM-dd")) {
                        System.out.println("Invalid Date format please use yyyy-MM-dd");
                        break;
                    }


                    checkInDate = java.sql.Date.valueOf(checkInDateEntered);

                    if(checkInDate.compareTo(new java.sql.Date(Calendar.getInstance().getTimeInMillis())) <= 0) {
                        System.out.println("You need to book atleast one day in-advance..!!");
                        break;
                    }
                    System.out.println("Enter number of days(Maximum 7 days):");
                    numDays = sc.nextInt();
                    if(numDays > 7 || numDays < 1) {
                        System.out.println("Invalid Number Of Days, please enter 1-7 days");
                        break;
                    }
                    RAvailOut = dbUtil.returnAvailableRooms(checkInDate, numDays, userSelectedRoomType);
                    System.out.println("Availability:");
                    System.out.println("ROOM TYPE        :  "+RAvailOut.getRoomType());
                    System.out.println("ROOM PRICE       :  "+RAvailOut.getPrice()+"$");
                    System.out.println("ROOMS AVAILABLE: :  "+RAvailOut.getCount());

                    System.out.println("Do you want to continue with reservation(Yes/No)?: ");
                    String option = sc.next();
                    if(option.equalsIgnoreCase("yes") || option.equalsIgnoreCase("y")) {
                        System.out.println("Please Enter User Information:");
                        System.out.println("First Name :");
                        String fname = sc.next();
                        System.out.println("Last Name  :");
                        String lname = sc.next();
                        sc.nextLine();
                        System.out.println("Address    :");
                        String address = sc.nextLine();
                        System.out.println("email id   :");
                        String email = sc.next();

                        if(CommonUtil.isValidString(fname) && CommonUtil.isValidString(lname)
                                && CommonUtil.isValidString(address) && CommonUtil.isValidString(email)) {
                            User user = new User();
                            user.setFirstName(fname);
                            user.setLastName(lname);
                            user.setAddress(address);
                            user.setEmail(email);
                            String result = dbUtil.makeReservation(user, checkInDate, numDays, userSelectedRoomType);

                            if(result.equalsIgnoreCase("failed"))
                                System.out.println("System is currently unavailable, please try later");
                            else
                                System.out.println("Reservation Successful, Confirmation code is "+result);
                        } else {
                            System.out.println("Invalid user Information, Make sure each field must be altleast 3 character length.");
                            break;
                        }
                    }
                    break;

                case 3:
                    System.out.println("Please enter the confirmation number to check status:");
                    String conf_number = sc.next();
                    List<Reservation> reservations = dbUtil.checkReservation(conf_number);
//                    System.out.println("the size of reservation array is "+reservations.size());
                    if(reservations.size() == 0) {
                        System.out.println("Invalid Confirmation Number..!!");
                    }else {
                        System.out.println("Reservation Confirmation code: "+ reservations.get(0).getConfirmationNumber());
                        System.out.println("Reserved Room Number: "+ reservations.get(0).getRoomNumber());
                        System.out.println("Reservation Dates are: ");
                        for (Reservation res:reservations) {
                            System.out.println(res.getReservationDate());
                        }
                        System.out.println("The Reservation Status is "+((reservations.get(0).getStatus() == 1) ? "RESERVED":"CANCELLED"));

                    }

                    break;

                case 4:
                    System.out.println("Please enter the confirmation number to cancel reservation:");
                    String conf_number1 = sc.next();

                    if(dbUtil.cancelReservation(conf_number1))
                        System.out.println("Reservation "+conf_number1+" CANCELLED");
                    else
                        System.out.println("Cancellation failed, contact customer support");

                    break;

                case 5:
                    System.out.println("EXITING HOTEL RESERVATION SYSTEM - THANK YOU!");
                    break;

                default:
                    System.out.println("Wrong option. Please choose from the available options.(1-5)");

            }

        } while (userChoice != 5);


    }

}