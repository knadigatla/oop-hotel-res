package com.itu.mdObjects;


import java.util.List;

public class Room {
    private String roomType;
    private List<Integer> roomNumbers;
    private int price;
    private String roomTypeDetails;

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public List<Integer> getRoomNumbers() {
        return roomNumbers;
    }

    public void setRoomNumbers(List<Integer> roomNumbers) {
        this.roomNumbers = roomNumbers;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getRoomTypeDetails() {
        return roomTypeDetails;
    }

    public void setRoomTypeDetails(String roomTypeDetails) {
        this.roomTypeDetails = roomTypeDetails;
    }
}
