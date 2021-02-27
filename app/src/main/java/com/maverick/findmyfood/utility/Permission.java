package com.maverick.findmyfood.utility;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.usage.NetworkStatsManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.EventLogTags;
import android.view.View;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.maverick.findmyfood.R;

import static android.content.Context.*;
import static androidx.core.content.ContextCompat.getSystemService;
import static com.maverick.findmyfood.MainActivity.MY_PERMISSIONS_REQUEST_LOCATION;

public class Permission {
    public static void checkLocationPermission(Context context, Activity activity) {
        if (ContextCompat.checkSelfPermission(context,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION);
        }
        return;
    }

}
