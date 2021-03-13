package com.maverick.findmyfood.utility;

import android.os.AsyncTask;
import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.maverick.findmyfood.model.Reviews;
import com.maverick.findmyfood.utility.adapter.ReviewAdapter;

import java.util.ArrayList;
import java.util.List;

public class Review
{
    FirebaseAuth mauth = FirebaseAuth.getInstance();
    FirebaseUser user = mauth.getCurrentUser();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    List<Reviews> reviews;
    private RecyclerView recyclerView;
    private LinearLayout layout;
    public Review(RecyclerView review_recycler, LinearLayout no_review) {
        this.recyclerView=review_recycler;
        this.layout=no_review;
    }

    public void getReviews(String restaurant_name)
    {
        new FireReview(restaurant_name).execute();
    }

    private class FireReview  extends AsyncTask<Void,Void, List<Reviews>> {
        private String restaurant_name;
        public FireReview(String restaurant_name) {
            this.restaurant_name=restaurant_name;
        }

        @Override
        protected List<Reviews> doInBackground(Void... voids) {
            db.collection("reviews").whereEqualTo("restaurant",restaurant_name).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                @Override
                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                    List<Reviews> list=new ArrayList<>();
                    if (!queryDocumentSnapshots.isEmpty())
                    {
                        for (DocumentSnapshot documentSnapshot:queryDocumentSnapshots.getDocuments())
                        {
                            System.out.println("review available: "+documentSnapshot.get("review"));
                            Reviews reviews=new Reviews(documentSnapshot.get("username").toString(),documentSnapshot.get("restaurant").toString(),documentSnapshot.get("review").toString());
                            list.add(reviews);
                        }
                    }
                    reviews=list;
                    System.out.println("outside firestore methods");
                    if (reviews!=null&&(!reviews.isEmpty()))
                    {
                        System.out.println("not empty");
                        layout.setVisibility(View.GONE);
                        recyclerView.setAdapter(new ReviewAdapter(reviews));
                    }
                    else {
                        System.out.println("inside else block");
                        recyclerView.setVisibility(View.GONE);
                        layout.setVisibility(View.VISIBLE);
                    }
                }
            });
            return reviews;
        }
    }
}
