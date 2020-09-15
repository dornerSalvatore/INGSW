package com.example.progettoingsw;

import android.app.Activity;
import android.app.Dialog;
import android.os.AsyncTask;
import android.os.Bundle;


import java.io.Serializable;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


import android.content.Intent;

import android.util.Log;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.progettoingsw.Connection.ConnectionClass;
import com.example.progettoingsw.Dao.Struttura;
import com.example.progettoingsw.Dao.StrutturaDaoImp;
import com.example.progettoingsw.Dao.UtenteDaoImp;

import static com.example.progettoingsw.Connection.ConnectionClass.connectionClass;
import static com.example.progettoingsw.Connection.ConnectionClass.pass;

public class RicercaActivity extends Activity implements AdapterView.OnItemSelectedListener {
    Spinner spin;
    String nickname;
    Connection con;
    Spinner prezzo;
    ArrayList<String> spinnerList1;
    ArrayAdapter<String> adapter1;
    StrutturaDaoImp struttura;
    EditText nome;
    EditText citta;
    EditText provincia;
    String testo;
    String testo1;
    ArrayList<Struttura> struttura1;
    UtenteDaoImp utente;
    Button bottone2;
    Button bottone1;
    ArrayAdapter<String> adapter;
    ArrayList<String> spinnerList;
    SpinnerController c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Bundle e = getIntent().getExtras();
        if (e != null) {
            nickname = e.getString("nickname");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ricerca);
        ArrayList<String> prezziDefault = new ArrayList<String>();
        nome = (EditText) findViewById(R.id.editText1);
        citta = (EditText) findViewById(R.id.editText2);
        provincia = (EditText) findViewById(R.id.editText3);
        spin = (Spinner) findViewById(R.id.spinner);
        prezzo = (Spinner) findViewById(R.id.spinner2);
        struttura = new StrutturaDaoImp();
        spinnerList = new ArrayList<>();
        utente = new UtenteDaoImp();
        adapter = new ArrayAdapter<String>(RicercaActivity.this, android.R.layout.simple_spinner_dropdown_item, spinnerList);
        spin.setAdapter(adapter);
        spinnerList1 = new ArrayList<>();
        adapter1 = new ArrayAdapter<String>(RicercaActivity.this, android.R.layout.simple_spinner_dropdown_item, spinnerList1);
        prezzo.setAdapter(adapter1);
        spinnerList.add("");
        bottone1 = (Button) findViewById(R.id.invia);
        bottone2 = (Button) findViewById(R.id.button1);
        if (ConnectionClass.con == null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(RicercaActivity.this, "Check DBMS Connection", Toast.LENGTH_LONG).show();
                }
            });
        } else {
          c= new SpinnerController(RicercaActivity.this);
            new ButtonController(RicercaActivity.this);



        }

    }
        public void onItemSelected (AdapterView < ? > parent, View view,
        int pos, long id){

         c.onItemSelected1();
        }
        @Override
        public void onNothingSelected (AdapterView < ? > parent){
            // Another interface callback
        }
        @Override
        public void onDestroy () {
            // RUN SUPER | REGISTER ACTIVITY AS NULL IN APP CLASS
            if (nickname != null)
                utente.setLogOut(nickname);
            super.onDestroy();

        }

    }