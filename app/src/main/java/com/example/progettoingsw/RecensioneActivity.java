package com.example.progettoingsw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.example.progettoingsw.Connection.ConnectionClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static com.example.progettoingsw.Connection.ConnectionClass.pass;

public class RecensioneActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {
     String nickname;
    Connection con;
    EditText nomeStruttura;
    EditText citta;

    Spinner spin;
    String z = null;
    Boolean isSuccess = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle e= getIntent().getExtras();
        if(e!= null)
        {
            nickname=e.getString("nickname");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recensione);
        Button bottone1=(Button) findViewById(R.id.button1);
        nomeStruttura=(EditText)findViewById(R.id.editNome);
        citta=(EditText)findViewById(R.id.editCitta);
        spin=(Spinner)findViewById(R.id.spinner4);

        ArrayAdapter<String> adapter;
        ArrayList<String> spinnerList;
        spinnerList=new ArrayList<>();
        adapter=new ArrayAdapter<String> (RecensioneActivity.this,android.R.layout.simple_spinner_dropdown_item,spinnerList);
        spin.setAdapter(adapter);


        bottone1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openPage1 = new Intent(RecensioneActivity.this,MainActivity2.class);
                // passo all'attivazione dell'activity Pagina.java
                startActivity(openPage1);
            }
        });
        Button bottone2=(Button) findViewById(R.id.button2);
        bottone2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // passo all'attivazione dell'activity Pagina.java
                new RecensioneActivity.aggiungiRecensione().execute("");

            }
        });
        con = connectionClass(ConnectionClass.un.toString(), pass.toString(), ConnectionClass.db.toString(), ConnectionClass.ip.toString());
        if (con == null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(RecensioneActivity.this, "Check DBMS Connection", Toast.LENGTH_LONG).show();
                }
            });}
        else{
            try{
                String sql="Select Nome from Struttura";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next())
                {
                    String nome=rs.getString("nome");
                    spinnerList.add(nome);



                }
                adapter.notifyDataSetChanged();
                spin.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);






            }
            catch (Exception f) {
                isSuccess = false;
                Log.e("SQL Error : ", f.getMessage());
            }




        }



    }




    public class aggiungiRecensione extends AsyncTask<String, String, String> {
        String z = null;
        Boolean isSuccess = false;


        @Override
        protected void onPreExecute() {


        }

        @Override
        protected void onPostExecute(String s) {




        }

        @Override
        protected String doInBackground(String... strings) {

            con = connectionClass(ConnectionClass.un.toString(), pass.toString(), ConnectionClass.db.toString(), ConnectionClass.ip.toString());
            if (con == null) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(RecensioneActivity.this, "Check DBMS Connection", Toast.LENGTH_LONG).show();
                    }
                });
                z = "On Internet Connection";
            } else {

                try {
                    String sql = "SELECT * FROM Struttura WHERE nome = '" + spin.getSelectedItem().toString() + "' AND citta= '" + citta.getText() + "' ";
                    ;
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);


                    if (rs.next()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RecensioneActivity.this, "Recensione Aggiunta", Toast.LENGTH_LONG).show();

                            }
                        });

                    }else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RecensioneActivity.this, "Struttura non presente", Toast.LENGTH_LONG).show();
                            }
                        });

                        nomeStruttura.setText("");
                        citta.setText("");
                    }

                } catch (Exception e) {
                    isSuccess = false;
                    Log.e("SQL Error : ", e.getMessage());
                }
            }

         return z;
        }
    }
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
@Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {

            try {

                String sql = "Select citta from Struttura where nome ='" + spin.getSelectedItem().toString() + "'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    String nome = rs.getString("citta");
                    citta.setText(nome);


                }

            } catch (Exception f) {
                isSuccess = false;
                Log.e("SQL Error : ", f.getMessage());
            }

        }

@Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }




}