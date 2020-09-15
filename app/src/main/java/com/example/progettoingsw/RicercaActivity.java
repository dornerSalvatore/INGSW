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


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Bundle e= getIntent().getExtras();
        if(e!= null)
        {
            nickname=e.getString("nickname");
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ricerca);
        ArrayList<String> prezziDefault=new ArrayList<String>();
        nome=(EditText)findViewById(R.id.editText1);
        citta=(EditText)findViewById(R.id.editText2);
        provincia=(EditText)findViewById(R.id.editText3);
        spin=(Spinner)findViewById(R.id.spinner);
        prezzo=(Spinner)findViewById(R.id.spinner2);
        struttura=new StrutturaDaoImp();
        ArrayAdapter<String> adapter;
        ArrayList<String> spinnerList;
        spinnerList=new ArrayList<>();
        utente=new UtenteDaoImp();
        adapter=new ArrayAdapter<String> (RicercaActivity.this,android.R.layout.simple_spinner_dropdown_item,spinnerList);
        spin.setAdapter(adapter);
        spinnerList1=new ArrayList<>();
        adapter1=new ArrayAdapter<String> (RicercaActivity.this,android.R.layout.simple_spinner_dropdown_item,spinnerList1);
        prezzo.setAdapter(adapter1);
        spinnerList.add("");
        con = connectionClass(ConnectionClass.un.toString(), pass.toString(), ConnectionClass.db.toString(), ConnectionClass.ip.toString());
        if (con == null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(RicercaActivity.this, "Check DBMS Connection", Toast.LENGTH_LONG).show();
                }
            });}
        else{
            ArrayList<String> tipologia=struttura.getListTipologia();
            for(String s: tipologia)
            {
                spinnerList.add(s);
            }
           /* try{
                String sql="Select Distinct  Tipologia from Struttura";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next())
                {
                    String nome=rs.getString("tipologia");
                    spinnerList.add(nome);

                }*/
                adapter.notifyDataSetChanged();
                int c=500;
                spinnerList1.add("");
                for(int i=0;i<=10;i++) {
                    if (i == 0)
                        spinnerList1.add(String.valueOf(i * 10) + "£" + "-" + String.valueOf(i + 5 * 10) + "£");
                    else if (i > 0 && i < 5)
                        spinnerList1.add(String.valueOf(i * 100) + "£" + "-" + ((i + 1) * 100) + "£");

                    else if (i > 5 && i < 10) {
                        int b = c + 500;
                        spinnerList1.add(String.valueOf((c) + "£" + "-" + (b)) + "£");
                        c = b;
                    } else if (i == 10) spinnerList1.add(">2500£");
                }
                adapter1.notifyDataSetChanged();

                spin.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);
                prezzo.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

            /*}
            catch (Exception f) {

                Log.e("SQL Error : ", f.getMessage());
            }*/




        }

         Button bottone1=(Button) findViewById(R.id.invia);
         bottone1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if(!controlTipologia() | !controlCitta() | !controlProvincia()){return;}
                 else
                 new RicercaActivity.Ricerca().execute("");


             }
         });
        Button bottone2=(Button) findViewById(R.id.button1);
                 bottone2.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {

                         Intent intent2 = new Intent(RicercaActivity.this, MapsActivity.class);
                         intent2.putExtra("nickname",nickname);
                         startActivity(intent2);
                         finish();
                     }
                 });


    };
    public class Ricerca extends AsyncTask<String, String, String> {
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
                        Toast.makeText(RicercaActivity.this, "Check DBMS Connection", Toast.LENGTH_LONG).show();
                    }
                });
            } else {
                 struttura1=null;
                if(controlNome() && controlPrezzo())
                { if(testo!="") struttura1=struttura.getListaStruttureByPar(spin.getSelectedItem().toString(),String.valueOf(nome.getText()), String.valueOf(citta.getText()),String.valueOf(provincia.getText()),Integer.parseInt(testo),Integer.parseInt(testo1));
                else struttura1=struttura.getListaStruttureByPar(spin.getSelectedItem().toString(),String.valueOf(nome.getText()), String.valueOf(citta.getText()),String.valueOf(provincia.getText()),Integer.parseInt(testo));
                }
                else {
                    if (!controlNome() && !controlPrezzo()) {
                        struttura1=struttura.getListaStruttureByPar(spin.getSelectedItem().toString(), String.valueOf(citta.getText()),String.valueOf(provincia.getText()));
                    } else {
                        if (!controlPrezzo()) {
                            struttura1=struttura.getListaStruttureByPar(spin.getSelectedItem().toString(),String.valueOf(nome.getText()), String.valueOf(citta.getText()),String.valueOf(provincia.getText()));
                        } else  {
                            if (testo != "")
                                struttura1=struttura.getListaStruttureByPar(spin.getSelectedItem().toString(), String.valueOf(citta.getText()),String.valueOf(provincia.getText()),Integer.parseInt(testo),Integer.parseInt(testo1));
                            else
                                struttura1=struttura.getListaStruttureByPar(spin.getSelectedItem().toString(), String.valueOf(citta.getText()),String.valueOf(provincia.getText()),Integer.parseInt(testo));
                        }
                    }
                }
                ArrayList<String> strutture =new ArrayList<String>();
                if(!struttura1.isEmpty())
                {
                for(Struttura st:struttura1){
                    strutture.add(st.getNome()+":"+st.getIndirizzo());
                    //strutture.add(rs.getString("indirizzo"));
                    //strutture.add(rs.getString("prezzo")+"£");

                }




                        z = "Success";
                        Intent intent = new Intent(RicercaActivity.this, ListaStrutture.class);
                        Bundle args = new Bundle();
                        args.putSerializable("ARRAYLIST",(Serializable)strutture);
                        intent.putExtra("strutture",args);
                        intent.putExtra("nickname",nickname);
                        startActivity(intent);
                    }




                    else{
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RicercaActivity.this, "Nessun risultato", Toast.LENGTH_LONG).show();
                            }
                        });
                    }




            }
            return z;

        }
    }


   private Boolean controlTipologia() {
       String val = spin.getSelectedItem().toString();

       if (val.isEmpty()) {
           Toast.makeText(RicercaActivity.this, "Tipologia non selezionata", Toast.LENGTH_LONG).show();
           return false;
       }
       else {
           return true;
       }
   }
    private Boolean controlPrezzo() {
        String val = prezzo.getSelectedItem().toString();

        if (val.isEmpty() | val=="") {
            return false;
        }
        else {
            return true;
        }
    }
    private Boolean controlCitta() {
        String val = citta.getText().toString();

        if (val.isEmpty()) {
            citta.setError("Field cannot be empty");
            return false;
        }
        else {
            citta.setError(null);
            return true;
        }
    }
    private Boolean controlProvincia() {
        String val = provincia.getText().toString();

        if (val.isEmpty()) {
            provincia.setError("Field cannot be empty");
            return false;
        }
        else {
            provincia.setError(null);
            return true;
        }
    }
    private Boolean controlNome() {
        String val = nome.getText().toString();

        if (val.isEmpty()) {
            return false;
        }
        else {
            return true;
        }
    }
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {

        boolean flag = true;
        if (prezzo.getSelectedItem().toString() != "") {
            testo="";
            testo1="";
            String stringa = prezzo.getSelectedItem().toString();
            for (int i = 0; i < stringa.length() ; i++) {
                if (Character.isDigit(stringa.charAt(i))&&flag)
                    testo += stringa.charAt(i);
                else {
                    if (Character.isDigit(stringa.charAt(i)))
                    testo1 += stringa.charAt(i);
                    flag = false;
                }


            }
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