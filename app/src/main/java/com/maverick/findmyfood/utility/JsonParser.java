package com.maverick.findmyfood.utility;

import com.maverick.findmyfood.model.Restaurant;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class JsonParser
{
    private Restaurant parsedJsonObject(JSONObject object)
    {
        //
        Restaurant restaurant =new Restaurant();
        //get name from object
        try {
            String name=object.getJSONObject("restaurant").getString("name");
            String latitude=object.getJSONObject("restaurant").getJSONObject("location").getString("latitude");
            String longitude=object.getJSONObject("restaurant").getJSONObject("location").getString("longitude");
            String address=object.getJSONObject("restaurant").getJSONObject("location").getString("address");
            String cusines=object.getJSONObject("restaurant").getString("cuisines");
            String user_rating=object.getJSONObject("restaurant").getJSONObject("user_rating").getString("aggregate_rating");
            String photos_url=object.getJSONObject("restaurant").getString("photos_url");
            String menu_url=object.getJSONObject("restaurant").getString("menu_url");
            String avg_cost_for_two=object.getJSONObject("restaurant").getString("average_cost_for_two");
            System.out.println(name+""+latitude+""+longitude+""+address+""+user_rating+photos_url+menu_url+avg_cost_for_two);

            restaurant.setName(name);
            restaurant.setLocation_latitude(Double.parseDouble(latitude));
            restaurant.setLocation_longitude(Double.parseDouble(longitude));
            restaurant.setAddress(address);
            restaurant.setCusines(cusines);
            restaurant.setUser_rating(user_rating);
            restaurant.setPhotos_url(photos_url);
            restaurant.setMenu_url(menu_url);
            restaurant.setAevrage_cost_for_two(Integer.parseInt(avg_cost_for_two));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  restaurant;
    }

    private List<Restaurant> parseJsonArray(JSONArray array)
    {
        List<Restaurant> list=new ArrayList<>();
        for (int i=0;i<array.length();i++)
        {
            try {
                Restaurant data=parsedJsonObject((JSONObject) array.get(i));
                list.add(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return list;
    }

    public List<Restaurant> parseResult(JSONObject object)
    {
        JSONArray jsonArray=null;
        try {
            jsonArray=object.getJSONArray("nearby_restaurants");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  parseJsonArray(jsonArray);
    }

}
