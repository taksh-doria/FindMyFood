package com.maverick.findmyfood.utility.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maverick.findmyfood.R;
import com.maverick.findmyfood.model.Reviews;

import java.util.List;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>
{

    private List<Reviews> list;

    public ReviewAdapter(List<Reviews> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public ReviewAdapter.ReviewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.review_item,parent,false);
        return new ReviewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReviewAdapter.ReviewViewHolder holder, int position) {
            Reviews reviews=list.get(position);
            System.out.println("review user name"+reviews.getUser());
            holder.bind(reviews);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ReviewViewHolder extends  RecyclerView.ViewHolder{
        TextView username;
        TextView review;
        public ReviewViewHolder(@NonNull View itemView) {
            super(itemView);
        }

        public void bind(Reviews reviews)
        {
            username=(TextView)itemView.findViewById(R.id.user_name);
            review=(TextView)itemView.findViewById(R.id.review);
            username.setText(reviews.getUser());
            review.setText(reviews.getMessage());
        }
    }
}
