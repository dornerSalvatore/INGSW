package com.example.progettoingsw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.progettoingsw.Connection.ConnectionClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Pattern;

public class StrutturaActivity extends AppCompatActivity {
    String informa;
    String[] nomi ;
    Connection con;
    boolean b=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.struttura);
        final ListView mylist = (ListView) findViewById(R.id.lista);
        Bundle e= getIntent().getExtras();
        if(e!= null)
        {
            informa=e.getString("string");
             nomi = informa.split(Pattern.quote(":"));
        }
        TextView info=(TextView) findViewById(R.id.inf);
        TextView info1=(TextView) findViewById(R.id.indirizzo);
        info.setText(nomi[0]);
        info1.setText(nomi[1]);
        androidx.appcompat.widget.Toolbar toolbar= (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        con =connectionClass(ConnectionClass.un.toString(),ConnectionClass.pass.toString(),ConnectionClass.db.toString(),ConnectionClass.ip.toString());
        if(con == null){
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(StrutturaActivity.this,"Check DBMS Connection",Toast.LENGTH_LONG).show();
                }
            });

        }
        else {

            try {
                String sql = "SELECT * FROM Recensioni WHERE indirizzo = '" + info1.getText() +  "' ";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                ArrayList<String> recensioni =new ArrayList<String>();


                if (rs.next() ) {
                    recensioni.add(rs.getString("nickname")+":"+rs.getString("commento")+"\n"+"Voto : "+rs.getString("stelle"));
                    while(rs.next()){
                        recensioni.add(rs.getString("nickname")+":"+rs.getString("commento")+"\n"+"Voto : "+rs.getString("stelle"));

                    }
                    ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.list,recensioni);
                    mylist.setAdapter(adapter);



                } else {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(StrutturaActivity.this, "Check username or password", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            } catch (Exception c) {

                Log.e("SQL Error : ", c.getMessage());
            }
        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        if(b)
            menuInflater.inflate(R.menu.menu_login, menu);
        else
            menuInflater.inflate(R.menu.menu, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.login:{

                Dialog d=new Dialog(this);
                d.setTitle("Login");
                d.setCancelable(false);
                d.setContentView(R.layout.dialog_login);
                d.show();
                ImageButton b=(ImageButton) d.findViewById(R.id.imageButton);
                b.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View arg0)
                    {
                        d.dismiss();
                    }
                });

            }}
        switch(item.getItemId()){
            case R.id.registra:{
                Intent openPage1 = new Intent(StrutturaActivity.this,RegistrazioneActivity.class);
                startActivity(openPage1);


            }
        }

        return super.onOptionsItemSelected(item);
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