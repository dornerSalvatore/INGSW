package com.example.progettoingsw;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.progettoingsw.Connection.ConnectionClass;


public class MainActivity extends AppCompatActivity {


    Connection con;
    EditText username;
    EditText password;
    Button bottone1;
    Button bottone3;
    Button bottone2;

    String valore,valore1;
    int flag=0;


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        con =connectionClass(ConnectionClass.un.toString(),ConnectionClass.pass.toString(),ConnectionClass.db.toString(),ConnectionClass.ip.toString());
        androidx.appcompat.widget.Toolbar toolbar= (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);









         username = (EditText) findViewById(R.id.editTextTextPersonName);
        username.setText(username.getText().toString());
        password = (EditText) findViewById(R.id.editTextTextPassword);
        username.setText(username.getText().toString());
        bottone1=(Button) findViewById(R.id.bottone1);




        bottone1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valore=username.getText().toString();
                if (valore.isEmpty()) {
                    Toast.makeText(MainActivity.this, "campo username vuoto", Toast.LENGTH_LONG).show();
                } else {


                    new MainActivity.checkLogin().execute("");


                }
            }
        });




        bottone3=(Button) findViewById(R.id.bottone3);
        bottone3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openPage1 = new Intent(MainActivity.this,MainActivity.class);
                // passo all'attivazione dell'activity Pagina.java
                startActivityForResult(openPage1, 0);
            }
        });

         bottone2=(Button) findViewById(R.id.bottone2);
        bottone2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openPage1 = new Intent(MainActivity.this,RegistrazioneActivity.class);
                // passo all'attivazione dell'activity Pagina.java
                startActivity(openPage1);

            }
        });


        };

   @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater=getMenuInflater();
        menuInflater.inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.logout:{
                Intent openPage1 = new Intent(MainActivity.this,RicercaActivity.class);
                // passo all'attivazione dell'activity Pagina.java
                startActivity(openPage1);

            }
        }

        return super.onOptionsItemSelected(item);
    }

    public class checkLogin extends AsyncTask<String, String, String> {

        String z = null;
        Boolean isSuccess = false;


        @Override
        protected void onPreExecute() {


        }

        @Override
        protected void onPostExecute(String s) {
            username.setText("");
            password.setText("");

        }

        @Override
        protected String doInBackground(String... strings) {


            if(con == null){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this,"Check DBMS Connection",Toast.LENGTH_LONG).show();
                    }
                });
                z = "On Internet Connection";
            }
            else {

                    try {
                        String sql = "SELECT * FROM Utente WHERE username = '" + username.getText() + "' AND passwd= '" + password.getText() + "' ";
                        Statement stmt = con.createStatement();
                        ResultSet rs = stmt.executeQuery(sql);


                        if (rs.next() ) {

                            z = "Success";
                            String nickname = rs.getString("nickname");
                            flag=rs.getInt("FlagBlacklist");
                            if(flag==0){
                                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                                intent.putExtra("nickname", nickname);
                                startActivity(intent);
                                finish();}
                            else{ runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "Account Bloccato", Toast.LENGTH_LONG).show();
                                }
                            });}

                        } else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "Check username or password", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                    } catch (Exception e) {
                        isSuccess = false;
                        Log.e("SQL Error : ", e.getMessage());
                    }
                }

            return z;
        }
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
