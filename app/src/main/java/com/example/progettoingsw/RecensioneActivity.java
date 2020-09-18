package com.example.progettoingsw;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.progettoingsw.Dao.RecensioneDaoImp;
import com.example.progettoingsw.Dao.StrutturaDaoImp;
import com.example.progettoingsw.Dao.UtenteDaoImp1;

import java.util.ArrayList;

public class RecensioneActivity extends AppCompatActivity  implements AdapterView.OnItemSelectedListener {
     String nickname;
    EditText tipologiaStruttura;
    EditText citta;
    EditText commento;
    Spinner spin;
    Spinner voto;
    int id;
    float latitudine;
   float  longitudine;
    String indirizzo;
    RecensioneDaoImp recensione;
    StrutturaDaoImp struttura;
    UtenteDaoImp1 utente;
    Button bottone1;
    Button bottone2;
    ArrayAdapter<String> adapter;
    ArrayList<String> spinnerList;
    ArrayAdapter<String> adapter1;
    ArrayList<String> spinnerList1;
    SpinnerController c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle e= getIntent().getExtras();
        if(e!= null)
        {
            nickname=e.getString("nickname");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recensione);
        bottone1=(Button) findViewById(R.id.button1);
        bottone2=(Button) findViewById(R.id.invia);
        tipologiaStruttura=(EditText)findViewById(R.id.editStruttura);
        citta=(EditText)findViewById(R.id.editCitta);
        spin=(Spinner)findViewById(R.id.spinner4);
        voto=(Spinner)findViewById(R.id.voto);
        commento=(EditText)findViewById(R.id.textCommento);
        utente=new UtenteDaoImp1();
        recensione=new RecensioneDaoImp();
        struttura=new StrutturaDaoImp();
        spinnerList=new ArrayList<>();
        adapter=new ArrayAdapter<String> (RecensioneActivity.this,android.R.layout.simple_spinner_dropdown_item,spinnerList);
        spin.setAdapter(adapter);
        spinnerList1=new ArrayList<>();
        adapter1=new ArrayAdapter<String> (RecensioneActivity.this,android.R.layout.simple_spinner_dropdown_item,spinnerList1);
        voto.setAdapter(adapter1);
        new ButtonController(RecensioneActivity.this);
        c=new SpinnerController(RecensioneActivity.this);


    }



@Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {

      c.onItemSelected();

        }

@Override
    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }
    @Override
    public void onDestroy() {
        // RUN SUPER | REGISTER ACTIVITY AS NULL IN APP CLASS
        if(nickname!=null)
            utente.setLogOut(nickname);
        super.onDestroy();

    }

    }




