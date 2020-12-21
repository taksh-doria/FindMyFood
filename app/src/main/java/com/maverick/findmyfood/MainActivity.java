    package com.maverick.findmyfood;

    import androidx.appcompat.app.AppCompatActivity;
    import androidx.core.app.ActivityCompat;
    import androidx.core.content.ContextCompat;

    import android.Manifest;
    import android.app.AlertDialog;
    import android.content.DialogInterface;
    import android.content.pm.PackageManager;
    import android.location.LocationManager;
    import android.os.Bundle;

    public class MainActivity extends AppCompatActivity {
        public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            checkLocationPermission();
        }

        public void checkLocationPermission() {
            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this,
                            new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                            MY_PERMISSIONS_REQUEST_LOCATION);
            }
            else
                {

            }
        }
    }