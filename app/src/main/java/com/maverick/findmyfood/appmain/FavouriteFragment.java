package com.maverick.findmyfood.appmain;

import android.app.AlertDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.maverick.findmyfood.R;
import com.maverick.findmyfood.utility.Favourites;


public class FavouriteFragment extends Fragment {

    LinearLayout layout;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_favourite, container, false);
        layout=(LinearLayout)v.findViewById(R.id.no_data);
        recyclerView=(RecyclerView)v.findViewById(R.id.favourites_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        progressBar=(ProgressBar)v.findViewById(R.id.progressBar_fav);
        Favourites favourites=new Favourites(layout,recyclerView,progressBar);
        if (checkConnection(v.getContext()))
        {
            favourites.getList();
        }
        ItemTouchHelper helper=new ItemTouchHelper(favourites.callback);
        helper.attachToRecyclerView(recyclerView);
        return  v;
    }

    public boolean checkConnection(Context context)
    {
        ConnectivityManager conMgr =  (ConnectivityManager)getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();
        if (netInfo == null){

            new AlertDialog.Builder(context)
                    .setTitle(getResources().getString(R.string.app_name))
                    .setMessage(getResources().getString(R.string.internet_error))
                    .setPositiveButton("OK", null).show();
            return false;
        }else{
            return  true;
        }

    }
}