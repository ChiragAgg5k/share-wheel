package com.obsidian.sharewheel.objects;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RidePost {

    private String pickup;
    private String whereTo;
    private String date;
    private String time;
    private String seats;
    private String price;
    private String id;
    FirebaseUser user;

    public RidePost() {
    }

    public RidePost(String pickup, String whereTo, String date, String time, String seats, String price) {
        this.pickup = pickup;
        this.whereTo = whereTo;
        this.date = date;
        this.time = time;
        this.seats = seats;
        this.price = price;
        user = FirebaseAuth.getInstance().getCurrentUser();
        id = user.getUid();
    }

    // Getters and setters for the fields

    public String getPickup() {
        return pickup;
    }

    public void setPickup(String pickup) {
        this.pickup = pickup;
    }

    public String getWhereTo() {
        return whereTo;
    }

    public void setWhereTo(String whereTo) {
        this.whereTo = whereTo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getId() {
        return id;
    }
}
