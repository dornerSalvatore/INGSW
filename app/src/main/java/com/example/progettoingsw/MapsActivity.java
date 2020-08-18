package com.example.progettoingsw;


import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.annotation.SuppressLint;
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

import android.os.StrictMode;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.example.progettoingsw.Connection.ConnectionClass;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.sql.Array;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback/*GoogleMap.OnMarkerClickListener*/ {

    double longitude;
    double latitude;
    List<Address> addressList;
    String indirizzo;
    LocationManager locationManager;
    SupportMapFragment mapFragment;
    Connection con;
    String citta;
    String nome;
    float longi;
    float lat;
    String ind;




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

                if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
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
                                        indirizzo = addressList.get(0).getAddressLine(0);
                                        citta = addressList.get(0).getCountryName();
                                        con = connectionClass(ConnectionClass.un.toString(), ConnectionClass.pass.toString(), ConnectionClass.db.toString(), ConnectionClass.ip.toString());
                                        if (con == null) {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Toast.makeText(MapsActivity.this, "Check DBMS Connection", Toast.LENGTH_LONG).show();
                                                }
                                            });

                                        } else {
                                            try {
                                                String sql = "SELECT * FROM Struttura WHERE  latitudine >='"+(latitude-0.03)+"'AND latitudine <= '"+(latitude+0.03)+"'and longitudine >='"+(longitude-1)+"'AND longitudine <= '"+(longitude+1)+"'";

                                                Statement stmt = con.createStatement();
                                                ResultSet rs = stmt.executeQuery(sql);

                                                while(rs.next()) {
                                                     nome = rs.getString("nome");
                                                     longi=rs.getFloat("Longitudine");
                                                     lat=rs.getFloat("Latitudine");
                                                     ind=rs.getString("indirizzo");
                                                    LatLng lng1 = new LatLng(lat, longi);
                                                    MarkerOptions markerOpt = new MarkerOptions()
                                                            .position(lng1).title(nome+":"+ind);
                                                    mMap.addMarker(markerOpt);
                                                    markerOpt.isVisible();




                                                }

                                            } catch (Exception e) {

                                                Log.e("SQL Error : ", e.getMessage());
                                            }
                                        }
                                        mMap.addMarker(new MarkerOptions().position(lng).title("sei qui"));
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
                            Intent intent = new Intent(MapsActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();


                        }
                    });
                } else if (locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
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
                                        indirizzo = addressList.get(0).getAddressLine(0);
                                        citta = addressList.get(0).getCountryName();
                                        con = connectionClass(ConnectionClass.un.toString(), ConnectionClass.pass.toString(), ConnectionClass.db.toString(), ConnectionClass.ip.toString());
                                        if (con == null) {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Toast.makeText(MapsActivity.this, "Check DBMS Connection", Toast.LENGTH_LONG).show();
                                                }
                                            });

                                        } else {
                                            try {
                                                String sql = "SELECT * FROM Struttura WHERE  latitudine >='"+(latitude-0.03)+"'AND latitudine <= '"+(latitude+0.03)+"'and longitudine >='"+(longitude-1)+"'AND longitudine <= '"+(longitude+1)+"'";

                                                Statement stmt = con.createStatement();
                                                ResultSet rs = stmt.executeQuery(sql);

                                                while(rs.next()) {
                                                    nome = rs.getString("nome");
                                                    longi=rs.getFloat("Longitudine");
                                                    lat=rs.getFloat("Latitudine");
                                                    ind=rs.getString("indirizzo");
                                                    LatLng lng1 = new LatLng(lat, longi);
                                                    MarkerOptions markerOpt = new MarkerOptions()
                                                            .position(lng1).title(nome+":"+ind);
                                                    mMap.addMarker(markerOpt);
                                                    markerOpt.isVisible();




                                                }

                                            } catch (Exception e) {

                                                Log.e("SQL Error : ", e.getMessage());
                                            }
                                        }
                                        mMap.addMarker(new MarkerOptions().position(lng).title("sei qui"));
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
                            Toast.makeText(MapsActivity.this, "Posizione disattivata", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MapsActivity.this, RicercaActivity.class);
                            startActivity(intent);
                            finish();


                        }
                    });
                } else {
                    Toast.makeText(MapsActivity.this, "Posizione disattivata", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MapsActivity.this, RicercaActivity.class);
                    startActivity(intent);
                    finish();
                }


            } catch (Exception f) {
                f.printStackTrace();
            }


    }



        @Override
        public void onMapReady(GoogleMap googleMap) {
            mMap = googleMap;
            //googleMap.setOnMarkerClickListener(this);
            mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {

                @Override
                public void onInfoWindowClick(Marker arg0) {
                    if (arg0 != null && !arg0.getTitle().equals("sei qui")) {
                        Intent intent1 = new Intent(MapsActivity.this, StrutturaActivity.class);
                        intent1.putExtra("string",arg0.getTitle());
                        startActivity(intent1);
                    }


                }
            });
        }





    @SuppressLint("NewApi")
    public Connection connectionClass(String user, String password, String database, String server){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String connectionURL = null;
        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connectionURL = "jdbc:jtds:sqlserver://" + server+"/" + database + ";user=" + user + ";password=" + password + ";";
            connection = DriverManager.getConnection(connectionURL);
        }catch (Exception e){
            Log.e("SQL Connection Error : ", e.getMessage());
        }

        return connection;
    }


    }
