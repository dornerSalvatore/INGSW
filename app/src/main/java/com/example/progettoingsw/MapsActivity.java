package com.example.progettoingsw;


import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;

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

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    Location gps_loc;
    Location network_loc;
    Location final_loc;
    double longitude;
    double latitude;
    List<Address> addressList;
    String indirizzo;
    LocationManager locationManager;
    SupportMapFragment mapFragment;



    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE) != PackageManager.PERMISSION_GRANTED) {

            return;
        }

        try {

           if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 10000, new LocationListener() {
                    @Override
                    public void onLocationChanged(Location location) {
                        latitude = location.getLatitude();
                        longitude = location.getLongitude();
                        LatLng lng = new LatLng(latitude, longitude);
                        Geocoder geocoder = new Geocoder(getApplicationContext());
                        try {
                            List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
                            if (addressList != null && addressList.size() > 0) {
                                Address address = addressList.get(0);
                                if (address.getAddressLine(0) != null && address.getAddressLine(0).length() > 0 && !address.getAddressLine(0).contentEquals("null")) {
                                    indirizzo = address.getAddressLine(0);
                                    mMap.addMarker(new MarkerOptions().position(lng).title(indirizzo));
                                    CameraPosition cameraPosition = new CameraPosition.Builder().target(lng).zoom(15).build();


                                    mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                                }
                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }


                    }

                    @Override
                    public void onStatusChanged(String provider, int status, Bundle extras) {

                    }

                    @Override
                    public void onProviderEnabled(String provider) {




                    }

                        @Override
                        public void onProviderDisabled(String provider) {
                            //Toast.makeText(MapsActivity.this,"Posizione disattivata",Toast.LENGTH_SHORT).show();
                            Intent intent= new Intent(MapsActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();


                        }
                    });}
           else if(locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)){
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 10000, new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    LatLng lng = new LatLng(latitude, longitude);
                    Geocoder geocoder = new Geocoder(getApplicationContext());
                    try {
                        List<Address> addressList = geocoder.getFromLocation(latitude, longitude, 1);
                        if (addressList != null && addressList.size() > 0) {
                            Address address = addressList.get(0);
                            if (address.getAddressLine(0) != null && address.getAddressLine(0).length() > 0 && !address.getAddressLine(0).contentEquals("null")) {
                                indirizzo = address.getAddressLine(0);
                                mMap.addMarker(new MarkerOptions().position(lng).title(indirizzo));
                                CameraPosition cameraPosition = new CameraPosition.Builder().target(lng).zoom(15).build();


                                mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                }

                @Override
                public void onStatusChanged(String provider, int status, Bundle extras) {

                }

                @Override
                public void onProviderEnabled(String provider) {




                }

                @Override
                public void onProviderDisabled(String provider) {
                    Toast.makeText(MapsActivity.this,"Posizione disattivata",Toast.LENGTH_SHORT).show();
                    Intent intent= new Intent(MapsActivity.this,MainActivity.class);
                    startActivity(intent);
                    finish();


                }
            });
           } else {
               Toast.makeText(MapsActivity.this,"Posizione disattivata",Toast.LENGTH_SHORT).show();
               Intent intent= new Intent(MapsActivity.this,MainActivity.class);
               startActivity(intent);
               finish();}


        } catch (Exception e) {
                e.printStackTrace();
            }

           /* if (gps_loc != null) {
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

            mapFragment.getMapAsync(this);

        }
    @Override
    public void onLocationChanged(Location location) {
            if(location.getProvider()!=null) {

                    latitude = location.getLatitude();
                    longitude = location.getLongitude();
                    Toast.makeText(this, "Lat: " + latitude + " Long: " + longitude, Toast.LENGTH_SHORT).show();
                    Geocoder geocoder = new Geocoder(this, Locale.getDefault());
                    try {
                        addressList = geocoder.getFromLocation(
                                latitude, longitude, 1);
                        if (addressList != null && addressList.size() > 0) {
                            Address address = addressList.get(0);
                            if (address.getAddressLine(0) != null && address.getAddressLine(0).length() > 0 && !address.getAddressLine(0).contentEquals("null")) {
                                indirizzo = address.getAddressLine(0);
                            }
                        }

                    } catch (IOException e) {
                        //Log.e(TAG, "Unable connect to Geocoder", e);
                    }


                    mapFragment.getMapAsync(this);
                }





    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {


        }

    @Override
    public void onProviderEnabled(String provider) {



    }




    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(MapsActivity.this,"Posizione disattivata",Toast.LENGTH_SHORT).show();

        Intent openPage1 = new Intent(MapsActivity.this, MainActivity.class);
        // passo all'attivazione dell'activity Pagina.java
        startActivity(openPage1);
        finish();



*/
        }


        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            /*LatLng napoli = new LatLng(latitude,longitude);
            mMap.addMarker(new MarkerOptions().position(napoli).title(indirizzo));
            CameraPosition cameraPosition = new CameraPosition.Builder().target(napoli).zoom(15).build();


            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));*/
        }


    }
