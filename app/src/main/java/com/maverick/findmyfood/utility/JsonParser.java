package com.maverick.findmyfood.utility;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class JsonParser
{
    private HashMap<String,String> parsedJsonObject(JSONObject object)
    {
        //
        HashMap<String,String> datalist=new HashMap<>();
        //get name from object
        try {
            String name=object.getString("name");
            String latitude=object.getJSONObject("geometry").getJSONObject("location").getString("lat");
            String longitude=object.getJSONObject("geometry").getJSONObject("location").getString("lon");
            datalist.put("name",name);
            datalist.put("lat",latitude);
            datalist.put("lon",longitude);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  datalist;
    }

    private List<HashMap<String,String>> parseJsonArray(JSONArray array)
    {
        List<HashMap<String,String >> list=new ArrayList<>();
        for (int i=0;i<array.length();i++)
        {
            try {
                HashMap<String,String> data=parsedJsonObject((JSONObject) array.get(i));
                list.add(data);
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
        return list;
    }

    public List<HashMap<String,String>> parseResult(JSONObject object)
    {
        JSONArray jsonArray=null;
        try {
            jsonArray=object.getJSONArray("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  parseJsonArray(jsonArray);
    }

}
