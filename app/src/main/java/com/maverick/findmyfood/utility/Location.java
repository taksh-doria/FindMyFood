package com.maverick.findmyfood.utility;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.maverick.findmyfood.R;
import com.maverick.findmyfood.model.Restaurant;
import com.maverick.findmyfood.utility.adapter.ListAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class Location
{
    public RecyclerView recyclerView;
    public android.location.Location user_location;
    public ProgressBar progressBar;

    public Location(RecyclerView recyclerView,ProgressBar pbar) {
        this.recyclerView=recyclerView;
        this.progressBar=pbar;
    }

    public android.location.Location getLocation(Context context)
    {
        FusedLocationProviderClient fusedLocationProviderClient=LocationServices.getFusedLocationProviderClient(context);
        Task<android.location.Location> task=fusedLocationProviderClient.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<android.location.Location>() {
            @Override
            public void onSuccess(android.location.Location location) {
                if (location!=null)
                {
                       user_location=location;
                }
            }
        });
        return user_location;
    }

    public void getResturantData(android.location.Location location)
    {
        String url="https://developers.zomato.com/api/v2.1/geocode?lat="+location.getLatitude()+"&lon="+location.getLongitude()+
                "&user-key="+R.string.places_api_key;

        new PlaceTask().execute(url);
    }


    private class PlaceTask extends AsyncTask<String,Integer,String>
    {

        @Override
        protected String doInBackground(String... strings) {
            String data=null;
            try {
                data=downloadUrl(strings[0]);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return data;
        }

        private String downloadUrl(String string) throws IOException
        {
            URL url=new URL(string);
            HttpURLConnection connection=(HttpURLConnection)url.openConnection();
            connection.setRequestProperty("user-key", "b5446928c3c6f0cb7893c1daabfb4e1c");
            connection.setRequestProperty("Accept", "application/json");
            connection.connect();
            InputStream stream=connection.getInputStream();
            BufferedReader reader=new BufferedReader(new InputStreamReader(stream));
            StringBuilder builder=new StringBuilder();
            String line="";
            while ((line=reader.readLine())!=null)
            {
                builder.append(line);
            }
            String data=builder.toString();
            reader.close();
            return  data;
        }

        @Override
        protected void onPostExecute(String s) {
            System.out.println(s);
            new ParserTask().execute(s);

        }

        private class ParserTask extends  AsyncTask<String,Integer, List<Restaurant>> {
            @Override
            //parser to parse json data from web
            protected List<Restaurant> doInBackground(String... strings) {
                JsonParser parser=new JsonParser();
                List<Restaurant> fetched_resturantList=null;
                JSONObject object=null;
                try {
                    object=new JSONObject(strings[0]);
                    fetched_resturantList=parser.parseResult(object);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return fetched_resturantList;
            }

            @Override
            protected void onPostExecute(List<Restaurant> hashMaps) {
                super.onPostExecute(hashMaps);
                progressBar.setVisibility(View.INVISIBLE);
                System.out.println(hashMaps);
                recyclerView.setAdapter(new ListAdapter(hashMaps));
            }
        }
    }
}
