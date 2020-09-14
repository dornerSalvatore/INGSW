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
import com.example.progettoingsw.Dao.Recensione;
import com.example.progettoingsw.Dao.RecensioneDaoImp;
import com.example.progettoingsw.Dao.Struttura;
import com.example.progettoingsw.Dao.StrutturaDaoImp;
import com.example.progettoingsw.Dao.UtenteDaoImp;

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
    int id;
    float latitudine;
   float  longitudine;
    String indirizzo;
    RecensioneDaoImp recensione;
    StrutturaDaoImp struttura;
    UtenteDaoImp utente;
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
        utente=new UtenteDaoImp();
        recensione=new RecensioneDaoImp();
        struttura=new StrutturaDaoImp();
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
            ArrayList<Struttura> r= struttura.getListaStrutture();
            for(Struttura s: r)
            {


                spinnerList.add(s.getNome());
            }
            adapter.notifyDataSetChanged();
            spin.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);


            id=getTopRecensione();





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

                    if(!recensione.checkRecensionePresente(nickname,indirizzo)){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RecensioneActivity.this, "Recensione Aggiunta", Toast.LENGTH_LONG).show();
                            }
                        });

                   recensione.saveRecensione(id,String.valueOf(commento.getText()), Integer.valueOf(String.valueOf(voto.getSelectedItem())),nickname,indirizzo);
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





            return z;
        }

    }

@Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {
    Struttura struttura1=struttura.getStrutturaByNome(spin.getSelectedItem().toString());
    if(struttura1!=null)
    {
        String nome = struttura1.getCitta();
        citta.setText(nome);
        String tipologia=struttura1.getTipologia();
        tipologiaStruttura.setText(tipologia);
        latitudine=struttura1.getLatitudine();
        longitudine=struttura1.getLongitudine();
        indirizzo=struttura1.getIndirizzo();
    }


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




