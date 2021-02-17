package com.maverick.findmyfood.appmain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.maverick.findmyfood.R;
import com.maverick.findmyfood.model.Restaurant;
import com.maverick.findmyfood.utility.Location;

public class Detail extends AppCompatActivity {
    TextView name,cuisines,cost,address;
    Double latitude,longitude;
    RatingBar rating;
    String menu_url;
    Button menu,favourite,directions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        name=(TextView) findViewById(R.id.name);
        cuisines=(TextView) findViewById(R.id.cuisines);
        cost=(TextView)findViewById(R.id.cost);
        address=(TextView)findViewById(R.id.address);
        rating=(RatingBar)findViewById(R.id.rating);
        menu=(Button)findViewById(R.id.getmenu);
        favourite=(Button)findViewById(R.id.addfavourite);
        directions=(Button)findViewById(R.id.directions);
        Intent i=getIntent();
        menu_url=i.getStringExtra("menu");
        //latitude=Double.parseDouble(i.getStringExtra("latitude"));
        //longitude=Double.parseDouble(i.getStringExtra("longitude"));
        name.setText(i.getStringExtra("name"));
        cuisines.setText(Html.fromHtml("<b>Cuisines</b>: "+i.getStringExtra("cuisines")));

    }
}