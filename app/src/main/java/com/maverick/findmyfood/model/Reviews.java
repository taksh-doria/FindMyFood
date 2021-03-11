package com.maverick.findmyfood.model;

import com.google.firebase.Timestamp;

public class Reviews
{
    String user;
    String restaurant_name;
    Timestamp date;
    String message;

    public Reviews(String user, String restaurant_name, String message) {
        this.user = user;
        this.restaurant_name = restaurant_name;
        this.message = message;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getRestaurant_name() {
        return restaurant_name;
    }

    public void setRestaurant_name(String restaurant_name) {
        this.restaurant_name = restaurant_name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
