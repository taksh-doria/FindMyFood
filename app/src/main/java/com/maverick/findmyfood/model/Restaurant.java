package com.maverick.findmyfood.model;

import com.maverick.findmyfood.utility.Location;

public class Restaurant
{
    String name;
    String address;
    Double location_latitude,location_longitude;
    String cusines;
    String user_rating;
    String photos_url,menu_url;
    int aevrage_cost_for_two;
    boolean takeaway,delivery;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getLocation_latitude() {
        return location_latitude;
    }

    public void setLocation_latitude(Double location_latitude) {
        this.location_latitude = location_latitude;
    }

    public Double getLocation_longitude() {
        return location_longitude;
    }

    public void setLocation_longitude(Double location_longitude) {
        this.location_longitude = location_longitude;
    }

    public String getCusines() {
        return cusines;
    }

    public void setCusines(String cusines) {
        this.cusines = cusines;
    }

    public String getUser_rating() {
        return user_rating;
    }

    public void setUser_rating(String user_rating) {
        this.user_rating = user_rating;
    }

    public String getPhotos_url() {
        return photos_url;
    }

    public void setPhotos_url(String photos_url) {
        this.photos_url = photos_url;
    }

    public String getMenu_url() {
        return menu_url;
    }

    public void setMenu_url(String menu_url) {
        this.menu_url = menu_url;
    }

    public int getAevrage_cost_for_two() {
        return aevrage_cost_for_two;
    }

    public void setAevrage_cost_for_two(int aevrage_cost_for_two) {
        this.aevrage_cost_for_two = aevrage_cost_for_two;
    }

    public boolean isTakeaway() {
        return takeaway;
    }

    public void setTakeaway(boolean takeaway) {
        this.takeaway = takeaway;
    }

    public boolean isDelivery() {
        return delivery;
    }

    public void setDelivery(boolean delivery) {
        this.delivery = delivery;
    }
}
