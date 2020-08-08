package com.example.progettoingsw;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, LocationListener {
    Location gps_loc;
    Location network_loc;
    Location final_loc;
    double longitude;
    double latitude;
    List<Address> addressList;
    String indirizzo;







        private GoogleMap mMap;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_maps);
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);




            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {

                return;
            }

            try {

                gps_loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                network_loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10,1000, this);

            } catch (Exception e) {
                e.printStackTrace();
            }

            if (gps_loc != null) {
                final_loc = gps_loc;
                latitude = final_loc.getLatitude();
                longitude = final_loc.getLongitude();
            }
            else if (network_loc != null) {
                final_loc = network_loc;
                latitude = final_loc.getLatitude();
                longitude = final_loc.getLongitude();
            }
            else {
                latitude = 0.0;
                longitude = 0.0;
            }


        }
    @Override
    public void onLocationChanged(Location location) {
          latitude = location.getLatitude();
         longitude = location.getLongitude();
        if(latitude!=0.0 | longitude!=0.0) {
            Toast.makeText(this, "Lat: " + latitude + " Long: " + longitude, Toast.LENGTH_SHORT).show();
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            try {
            addressList= geocoder.getFromLocation(
                    latitude, longitude, 1);
                if (addressList != null && addressList.size() > 0){
                    Address address=addressList.get(0);
                    if(address.getAddressLine(0) !=null && address.getAddressLine(0).length()>0 && !address.getAddressLine(0).contentEquals("null"))
                    {indirizzo=address.getAddressLine(0);}
                }

        } catch (IOException e) {
            //Log.e(TAG, "Unable connect to Geocoder", e);
        }

            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }
        else{  runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MapsActivity.this,"Check posizione o internet",Toast.LENGTH_LONG).show();
            }
        });}
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
            .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);}

    @Override
    public void onProviderEnabled(String provider) {}

    @Override
    public void onProviderDisabled(String provider) {}

        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            LatLng napoli = new LatLng(latitude,longitude);
            mMap.addMarker(new MarkerOptions().position(napoli).title(indirizzo));
            CameraPosition cameraPosition = new CameraPosition.Builder().target(napoli).zoom(15).build();

            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
        }
    }
