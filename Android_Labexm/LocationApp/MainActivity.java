package com.example.locationapp;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

//import the following
import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
//FusedLocationProviderClient is part of the Google Play Services Location API, which provides a simple and effective way to retrieve location information on Android devices.
import com.google.android.gms.location.LocationServices;
//The LocationServices class is a part of the Google Play Services API, specifically designed to provide access to location-related services in Android applications.
import com.google.android.gms.tasks.OnSuccessListener;
//OnSuccessListener is part of the Google Play Services Tasks API, which is designed to handle asynchronous operations in a clean and efficient manner.

public class MainActivity extends AppCompatActivity {
    private static final int LOCATION_PERMISSION_REQUEST_CODE = 1;
    private FusedLocationProviderClient fusedLocationClient;
    private TextView locationTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //create an object for the TextView
        locationTextView = findViewById(R.id.locationTextView);
        //create an instance of FusedLocationProviderClient
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        //request for runtime permission to access location
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            //permission not granted
            //request for permission
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, LOCATION_PERMISSION_REQUEST_CODE);
        } else {
            //permission already granted
            getLastLocation();
        }
    }
    private void getLastLocation() {
        //retrieve the last known location of the device from the FusedLocationProviderClient

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {

                   //attaches a listener that will be called when the task completes successfully.
                   //If the location retrieval is successful, the listener’s onSuccess() method will be triggered with the retrieved Location object.

                    @Override
                    public void onSuccess(Location location) {
                        if (location != null) {
                            locationTextView.setText("Latitude: " + location.getLatitude() + ", Longitude: " + location.getLongitude());
                        } else {
                            locationTextView.setText("Location not available");
                        }
                    }
                });
    }

    //handle permission request
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            } else {
                Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}