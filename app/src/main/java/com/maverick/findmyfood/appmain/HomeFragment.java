package com.maverick.findmyfood.appmain;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.maverick.findmyfood.R;
import com.maverick.findmyfood.utility.Location;
import com.maverick.findmyfood.utility.Permission;


public class HomeFragment extends Fragment {

    private  LocationManager manager;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private android.location.Location user_location;
      @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_home, container, false);
        progressBar=(ProgressBar) root.findViewById(R.id.p_bar);
        recyclerView=(RecyclerView)root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
          System.out.println("Home fragment started");
          Permission.checkLocationPermission(root.getContext(),getActivity());
          manager=(LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
          Location location=new Location(recyclerView,progressBar);
          System.out.println("requesting location");
          getLocation(root.getContext());
          if (user_location!=null && checkConnection(root.getContext()))
          {
              System.out.println(user_location.toString());
              location.user_location=user_location;
              location.getResturantData(user_location);
          }
          else {
              System.out.print("null location object");
          }
        return root;
    }
    public void getLocation(Context context)
    {
        user_location = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        user_location = manager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
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