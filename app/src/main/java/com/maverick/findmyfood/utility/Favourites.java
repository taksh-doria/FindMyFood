package com.maverick.findmyfood.utility;

import android.os.AsyncTask;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.maverick.findmyfood.model.Restaurant;

import java.util.ArrayList;
import java.util.List;

public class Favourites {

    private final RecyclerView view;
    private final LinearLayout layout;
    private final ProgressBar pbar;
    List<Restaurant> list=null;


    public Favourites(LinearLayout layout, RecyclerView view, ProgressBar progressBar) {
        this.view = view;
        this.layout = layout;
        this.pbar = progressBar;
    }
    public void getList()
    {
        new Firestore().execute();
    }
    public class Firestore extends AsyncTask<Void, Void, List<Restaurant>> {

        @Override
        protected List<Restaurant> doInBackground(Void... voids) {
            FirebaseAuth mauth = FirebaseAuth.getInstance();
            FirebaseUser user = mauth.getCurrentUser();
            FirebaseFirestore db = FirebaseFirestore.getInstance();
            System.out.println(user.getEmail());
            db.collection("favourites").whereEqualTo("user", user.getEmail()).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    List<Restaurant> restaurants = new ArrayList<Restaurant>();
                    if (!queryDocumentSnapshots.isEmpty())
                    {
                        for (DocumentSnapshot document:queryDocumentSnapshots.getDocuments())
                        {
                            Restaurant restaurant = new Restaurant();
                            restaurant.setName(document.getString("name"));
                            restaurant.setCusines(document.getString("cuisines"));
                            restaurant.setAevrage_cost_for_two(Integer.parseInt(document.getString("cost")));
                            restaurant.setMenu_url(document.getString("menu"));
                            restaurant.setUser_rating(document.getString("rating"));
                            restaurant.setLocation_longitude(Double.parseDouble(document.getString("longitude")));
                            restaurant.setLocation_latitude(Double.parseDouble(document.getString("latitude")));
                            restaurant.setAddress(document.getString("address"));
                            restaurants.add(restaurant);
                        }
                    }
                    if (restaurants!=null)
                    {
                        if (!restaurants.isEmpty())
                        {
                            layout.setVisibility(View.GONE);
                            view.setAdapter(new ListAdapter(restaurants));
                            pbar.setVisibility(View.INVISIBLE);
                        }
                        else
                        {
                            layout.setVisibility(View.VISIBLE);
                            pbar.setVisibility(View.INVISIBLE);
                        }
                    }
                    else
                    {
                        layout.setVisibility(View.VISIBLE);
                        pbar.setVisibility(View.INVISIBLE);
                    }
                    list=restaurants;
                }
            });
            return list;
        }
    }
}
