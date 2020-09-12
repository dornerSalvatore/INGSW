package com.example.progettoingsw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.progettoingsw.Connection.ConnectionClass;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import static com.example.progettoingsw.Connection.ConnectionClass.connectionClass;
import static com.example.progettoingsw.Connection.ConnectionClass.getTopRecensione;
import static com.example.progettoingsw.Connection.ConnectionClass.pass;

public class RecensioneActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {
     String nickname;
    Connection con;
    EditText tipologiaStruttura;
    EditText citta;
    EditText commento;
    Spinner spin;
    Spinner voto;
    String z = null;
    Boolean isSuccess = false;
    int id,control=0;
    String latitudine;
    String longitudine;
    String indirizzo;
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
        tipologiaStruttura=(EditText)findViewById(R.id.editStruttura);
        citta=(EditText)findViewById(R.id.editCitta);
        spin=(Spinner)findViewById(R.id.spinner4);
        voto=(Spinner)findViewById(R.id.voto);
        commento=(EditText)findViewById(R.id.textCommento);

        ArrayAdapter<String> adapter;
        ArrayList<String> spinnerList;
        spinnerList=new ArrayList<>();
        adapter=new ArrayAdapter<String> (RecensioneActivity.this,android.R.layout.simple_spinner_dropdown_item,spinnerList);
        spin.setAdapter(adapter);
        ArrayAdapter<String> adapter1;
        ArrayList<String> spinnerList1;
        spinnerList1=new ArrayList<>();
        adapter1=new ArrayAdapter<String> (RecensioneActivity.this,android.R.layout.simple_spinner_dropdown_item,spinnerList1);
        voto.setAdapter(adapter1);
        for(int i=0;i<=5;i++)
        {
            spinnerList1.add(String.valueOf(i));
        } adapter1.notifyDataSetChanged();


        bottone1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openPage1 = new Intent(RecensioneActivity.this,MainActivity2.class);
                openPage1.putExtra("nickname",nickname);
                // passo all'attivazione dell'activity Pagina.java
                startActivity(openPage1);
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
                String sql="Select * from Struttura";
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
            id=getTopRecensione();
           /* try {


                String sql1 = "SELECT TOP 1 * FROM Recensioni ORDER BY ID DESC";
                Statement stmt1 = con.createStatement();
                ResultSet rs1 = stmt1.executeQuery(sql1);
                if(rs1.next()){
                    id=rs1.getInt("id");


                }
                id=id+1;


            } catch (Exception c) {
                isSuccess = false;
                Log.e("SQL Error : ", c.getMessage());
            }*/




        }
        Button bottone2=(Button) findViewById(R.id.invia);
        bottone2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // passo all'attivazione dell'activity Pagina.java
                new RecensioneActivity.aggiungiRecensione().execute("");

            }
        });



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
            /*try {


                String sql1 = "SELECT  * FROM Recensioni where nickname= '"+nickname +"' AND Indirizzo ='"+indirizzo+"'";
                Statement stmt1 = con.createStatement();
                ResultSet rs1 = stmt1.executeQuery(sql1);
                if(rs1.next()){
                    control=1;


                }



            } catch (Exception c) {
                isSuccess = false;
                Log.e("SQL Error : ", c.getMessage());
            }*/


               // try {
                    if(!ConnectionClass.checkRecensionePresente(nickname,indirizzo)){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RecensioneActivity.this, "Recensione Aggiunta", Toast.LENGTH_LONG).show();
                            }
                        });
                   /* String sql3 = "INSERT INTO Recensioni (Id,Commento,Stelle,Nickname,Indirizzo) VALUES ('" + id +"','" + commento.getText() + "','" + voto.getSelectedItem() + "','" + nickname + "','" + indirizzo + "')";
                    Statement stmt3 = con.createStatement();
                    stmt3.executeUpdate(sql3);*/
                   ConnectionClass.saveRecensione(id,String.valueOf(commento.getText()), Integer.valueOf(String.valueOf(voto.getSelectedItem())),nickname,indirizzo);
                    z = "Success";

                    Intent intent = new Intent(RecensioneActivity.this, MainActivity2.class);
                    intent.putExtra("nickname", nickname);
                    startActivity(intent);
                    finish();}
                    else{ runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RecensioneActivity.this, "Gia è stata aggiunta una recensione a questa struttura", Toast.LENGTH_LONG).show();
                        }
                    });}


                /*} catch (Exception e) {
                    isSuccess = false;
                    Log.e("SQL Error : ", e.getMessage());
                }*/


            return z;
        }

    }

@Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {

            try {

                String sql = "Select * from Struttura where nome ='" + spin.getSelectedItem().toString() + "'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    String nome = rs.getString("citta");
                    citta.setText(nome);
                    String tipologia=rs.getString("tipologia");
                    tipologiaStruttura.setText(tipologia);
                    latitudine=rs.getString("latitudine");
                    longitudine=rs.getString("longitudine");
                    indirizzo=rs.getString("indirizzo");


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