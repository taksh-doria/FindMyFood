package com.maverick.findmyfood.appmain;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.Timestamp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.maverick.findmyfood.R;
import com.maverick.findmyfood.model.Restaurant;
import com.maverick.findmyfood.utility.Location;
import com.maverick.findmyfood.utility.Review;

import java.util.Date;
import java.util.HashMap;

public class Detail extends AppCompatActivity  implements AppDialog.AppDialogListner {
    RecyclerView review_recycler;
    LinearLayout no_review;
    TextView name,cuisines,cost,address;
    Double latitude,longitude;
    RatingBar rating;
    String menu_url;
    Button menu,favourite,directions,review;
    FirebaseFirestore firestore;
    FirebaseUser user;
    Review review1;


    @Override
    protected void onStart() {
        super.onStart();
        review1.getReviews(name.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent i=getIntent();
        name=(TextView) findViewById(R.id.name);
        cuisines=(TextView) findViewById(R.id.cuisines);
        cost=(TextView)findViewById(R.id.cost);
        address=(TextView)findViewById(R.id.address);
        rating=(RatingBar)findViewById(R.id.rating);
        menu=(Button)findViewById(R.id.getmenu);
        favourite=(Button)findViewById(R.id.addfavourite);
        directions=(Button)findViewById(R.id.directions);
        review=(Button)findViewById(R.id.add_review);


        review_recycler=(RecyclerView)findViewById(R.id.review_recycler);
        review_recycler.setLayoutManager(new LinearLayoutManager(getBaseContext()));


        no_review=(LinearLayout)findViewById(R.id.no_review);

        review1=new Review(review_recycler,no_review);

        firestore=FirebaseFirestore.getInstance();
        user= FirebaseAuth.getInstance().getCurrentUser();
        menu_url=i.getStringExtra("menu");
        latitude=Double.parseDouble(i.getStringExtra("latitude"));
        longitude=Double.parseDouble(i.getStringExtra("longitude"));
        name.setText(i.getStringExtra("name"));
        String rate=i.getStringExtra("rating");
        System.out.println(rate);
        cuisines.setText(Html.fromHtml("<b>Cuisines</b>: "+i.getStringExtra("cuisines")));
        cost.setText(Html.fromHtml("<b>Cost: </b>"+i.getStringExtra("cost")));
        rating.setRating(Float.parseFloat(rate));
        address.setText(Html.fromHtml("<b>Address: </b>"+i.getStringExtra("address")));
        Intent intent=new Intent(this,WebActivity.class);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent.putExtra("menu",menu_url);
                startActivity(intent);
            }
        });
        favourite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                HashMap<String,String> map=new HashMap<>();
                map.put("user",user.getEmail());
                map.put("name",i.getStringExtra("name"));
                map.put("cuisines",i.getStringExtra("cuisines"));
                map.put("latitude",i.getStringExtra("latitude"));
                map.put("longitude",i.getStringExtra("longitude"));
                map.put("cost",i.getStringExtra("cost"));
                map.put("address",i.getStringExtra("address"));
                map.put("rating",i.getStringExtra("rating"));
                map.put("menu_url",i.getStringExtra("menu"));
                map.put("time_stamp",new Timestamp(new Date()).toString());
                firestore.collection("favourites").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(v.getContext(),"Added to Favourites",Toast.LENGTH_SHORT).show();
                        review1.getReviews(name.getText().toString());
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(v.getContext(),"Unable to save data at moment!",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        directions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("button clicked");
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("google.navigation:q="+name.getText()+"&mode=d"));
                intent.setPackage("com.google.android.apps.maps");
                if (intent.resolveActivity(getPackageManager())!=null)
                {
                    startActivity(intent);
                }
            }
        });


        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDialog appDialog=new AppDialog();
                appDialog.show(getSupportFragmentManager(),"Post a Review");
            }
        });



    }

    @Override
    public void postReview(String post) {
        System.out.println("here");
        HashMap<String,String> map=new HashMap<>();
        map.put("username",user.getDisplayName());
        map.put("restaurant",name.getText().toString());
        map.put("review",post);
        map.put("time_stamp",new Timestamp(new Date()).toString());
        firestore.collection("reviews").add(map).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                Toast.makeText(getBaseContext(),"Review Posted", Toast.LENGTH_SHORT).show();
                review1.getReviews(name.getText().toString());

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getBaseContext(),"Error Occured!",Toast.LENGTH_SHORT).show();
            }
        });
    }
}