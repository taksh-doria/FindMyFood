package com.maverick.findmyfood.appmain;

import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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
    private android.location.Location user_location;
      @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root= inflater.inflate(R.layout.fragment_home, container, false);
        recyclerView=(RecyclerView)root.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
          System.out.println("Home fragment started");
          Permission.checkLocationPermission(root.getContext(),getActivity());
          manager=(LocationManager)getActivity().getSystemService(Context.LOCATION_SERVICE);
          Location location=new Location(recyclerView);
          System.out.println("requesting location");
          getLocation(root.getContext());
          if (user_location!=null)
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
}