package com.maverick.findmyfood.utility;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.maverick.findmyfood.R;
import com.maverick.findmyfood.model.Restaurant;

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
        holder.name.setText(data.getName());
    }

    @Override
    public int getItemCount() {
        return restaurants.size();
    }

    public class ResturantViewHolder extends RecyclerView.ViewHolder
    {
        TextView name;
        public ResturantViewHolder(@NonNull View itemView) {
            super(itemView);
            name=(TextView)itemView.findViewById(R.id.restaurant_name);
        }

    }
}
