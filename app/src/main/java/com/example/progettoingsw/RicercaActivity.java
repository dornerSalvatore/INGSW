package com.example.progettoingsw;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import java.lang.Object;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import android.content.Intent;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.progettoingsw.Connection.ConnectionClass;

import static com.example.progettoingsw.Connection.ConnectionClass.pass;

public class RicercaActivity extends Activity implements AdapterView.OnItemSelectedListener {
    Spinner spin;
    String nickname;
    Connection con;
    Spinner prezzo;
    ArrayList<String> spinnerList1;
    ArrayAdapter<String> adapter1;
    ArrayList<String> prezziDefault;
    EditText nome;
    EditText citta;
    EditText provincia;
    String testo;
    String testo1;

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
        ArrayAdapter<String> adapter;
        ArrayList<String> spinnerList;
        spinnerList=new ArrayList<>();
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
            try{
                String sql="Select * from Struttura";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                while(rs.next())
                {
                    String nome=rs.getString("tipologia");
                    spinnerList.add(nome);

                }
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

            }
            catch (Exception f) {

                Log.e("SQL Error : ", f.getMessage());
            }




        }

         Button bottone1=(Button) findViewById(R.id.button2);
         bottone1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if(!controlTipologia() | !controlCitta() | !controlProvincia()){return;}
                 else
                 new RicercaActivity.Ricerca().execute("");
                 //Intent intent = new Intent(RicercaActivity.this,MapsActivity.class);
                 //startActivity(intent);

             }
         });
        Button bottone2=(Button) findViewById(R.id.button1);
                 bottone2.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         Intent intent2 = new Intent();
                         // passo all'attivazione dell'activity Pagina.java
                         setResult(RESULT_OK, intent2);
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

                try {
                    String sql;
                    if(controlNome() && controlPrezzo())
                    { if(testo!="") sql = "Select * from Struttura where Tipologia = '" + spin.getSelectedItem().toString() + "' AND Nome= '" + nome.getText() + "'AND citta='" + citta.getText() + "' AND provincia='" + provincia.getText() + "'AND prezzo >='" + Integer.parseInt(testo) + "'AND prezzo <'" + Integer.parseInt(testo1) + "'";
                      else sql = "Select * from Struttura where Tipologia = '" + spin.getSelectedItem().toString() + "' AND Nome= '" + nome.getText() + "'AND citta='" + citta.getText() + "' AND provincia='" + provincia.getText() + "'AND prezzo >='" + Integer.parseInt(testo1) +  "'";
                    }
                    else {
                        if (!controlNome() && !controlPrezzo()) {
                            sql = "Select * from Struttura where Tipologia = '" + spin.getSelectedItem().toString() + "'AND citta='" + citta.getText() + "' AND provincia='" + provincia.getText() + "'";
                        } else {
                            if (!controlPrezzo()) {
                                sql = "Select * from Struttura where Tipologia = '" + spin.getSelectedItem().toString() + "' AND Nome= '" + nome.getText() + "'AND citta='" + citta.getText() + "' AND provincia='" + provincia.getText() + "'";
                            } else  {
                                if (testo != "")
                                    sql = "Select * from Struttura where Tipologia = '" + spin.getSelectedItem().toString() + "'AND citta='" + citta.getText() + "' AND provincia='" + provincia.getText() + "'AND prezzo >='" + Integer.parseInt(testo) + "'AND prezzo <'" + Integer.parseInt(testo1) + "'";
                                else
                                    sql = "Select * from Struttura where Tipologia = '" + spin.getSelectedItem().toString() + "'AND citta='" + citta.getText() + "' AND provincia='" + provincia.getText() + "'AND prezzo >='" + Integer.parseInt(testo1) + "'";
                            }
                        }
                    }

                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    if (rs.next()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                //Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_LONG);
                            }
                        });
                        z = "Success";

                        Intent intent = new Intent(RicercaActivity.this, MapsActivity.class);
                        startActivity(intent);
                        finish();


                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RicercaActivity.this, "Nessun risultato", Toast.LENGTH_LONG).show();
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
   /* @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id) {


        try {
            if(spin.getSelectedItem().toString()!="") {

                String sql = "Select * from Struttura where tipologia ='" + spin.getSelectedItem().toString() + "'";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                spinnerList1.clear();
                spinnerList1.add("");
                if (rs.next()) {
                    String nome = rs.getString("prezzo");
                    spinnerList1.add(nome);
                    ;


                }
                adapter1.notifyDataSetChanged();
            }
            else{
                spinnerList1.clear();
                spinnerList1.add("");
                int c=500;
                for(int i=0;i<=10;i++)
                    if(i==0)
                spinnerList1.add(String.valueOf(i*10)+"£"+"-"+String.valueOf(i+5*10)+"£");
                    else
                        if(i>0&&i<5)spinnerList1.add(String.valueOf(i*100)+"£"+"-"+((i+1)*100)+"£");

                    else
                        if(i>5&&i<10) {
                            int b=c+500;
                            spinnerList1.add(String.valueOf((c)+"£"+"-"+(b))+"£");
                            c=b;
                        }
                        else
                            if (i==10) spinnerList1.add(">2500£");
                adapter1.notifyDataSetChanged();
            }


        } catch (Exception f) {
            Log.e("SQL Error : ", f.getMessage());
        }

    }*/
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


}