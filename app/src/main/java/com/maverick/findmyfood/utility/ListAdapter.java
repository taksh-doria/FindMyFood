package com.maverick.findmyfood.utility;

import android.app.Activity;
import android.content.Intent;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maverick.findmyfood.R;
import com.maverick.findmyfood.appmain.Detail;
import com.maverick.findmyfood.model.Restaurant;
import com.thekhaeng.pushdownanim.PushDownAnim;

import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ResturantViewHolder>
{
    private List<Restaurant> restaurants;
    public ListAdapter(List<Restaurant> restaurants)
    {
        this.restaurants=restaurants;
    }

    @NonNull
    @Override
    public ResturantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.inflate(R.layout.restaurantitem,parent,false);
        return new ResturantViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ResturantViewHolder holder, int position)
    {
        Restaurant data=restaurants.get(position);
        holder.bind(data);
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public class ResturantViewHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        TextView rating;
        TextView cuisines;
        public ResturantViewHolder(@NonNull View itemView) {
            super(itemView);
        }
        public void bind(Restaurant item)
        {
            name=(TextView)itemView.findViewById(R.id.restaurant_name);
            rating=(TextView)itemView.findViewById(R.id.user_rating);
            cuisines=(TextView)itemView.findViewById(R.id.restaurant_cuisines);
            name.setText(item.getName());
            rating.setText(item.getUser_rating());
            rating.setClickable(false);
            cuisines.setText(Html.fromHtml("<b>Casual Dining:</b> "+item.getCusines()));
            PushDownAnim.setPushDownAnimTo(itemView).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    System.out.println("average cost: "+item.getAevrage_cost_for_two());
                    Intent intent=new Intent(itemView.getContext(), Detail.class);
                    intent.putExtra("name",item.getName());
                    intent.putExtra("cuisines",item.getCusines());
                    intent.putExtra("rating",item.getUser_rating());
                    intent.putExtra("latitude",String.valueOf(item.getLocation_latitude()));
                    intent.putExtra("longitude",String.valueOf(item.getLocation_longitude()));
                    intent.putExtra("address",item.getAddress());
                    intent.putExtra("cost",String.valueOf(item.getAevrage_cost_for_two()));
                    intent.putExtra("menu",item.getMenu_url());
                    itemView.getContext().startActivity(intent);
                }
            });
        }

    }
}
