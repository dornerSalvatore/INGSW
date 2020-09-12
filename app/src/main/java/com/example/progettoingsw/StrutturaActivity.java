package com.example.progettoingsw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.progettoingsw.Connection.ConnectionClass;

import com.example.progettoingsw.Dao.UtenteDaoImp;



import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.regex.Pattern;


import static com.example.progettoingsw.Connection.ConnectionClass.checkRecensionePresente;

import static com.example.progettoingsw.Connection.ConnectionClass.getTopRecensione;
import static com.example.progettoingsw.Connection.ConnectionClass.saveRecensione;


public class StrutturaActivity extends AppCompatActivity {
    String informa;
    String[] nomi ;
    boolean b=true;
    Spinner spin;
    ArrayList<String> recensioni =new ArrayList<String>();
    ArrayAdapter<String> adapter;
    TextView info1;
    ListView mylist;
    String nickname;
    Button aggiungi;
    ImageView image1;
    UtenteDaoImp utente;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.struttura);
         utente=new UtenteDaoImp();
         mylist = (ListView) findViewById(R.id.lista);
        Bundle e= getIntent().getExtras();
        if(e!= null)
        {
            informa=e.getString("string");
             nomi = informa.split(Pattern.quote(":"));
             nickname=e.getString("nickname");
        }
        TextView info=(TextView) findViewById(R.id.inf);
        info1=(TextView) findViewById(R.id.indirizzo);
        info.setText(nomi[0]);
        info1.setText(nomi[1]);
         image1= (ImageView) findViewById(R.id.imageView);
        androidx.appcompat.widget.Toolbar toolbar= (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        androidx.appcompat.widget.Toolbar toolbar1= (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        toolbar1.inflateMenu(R.menu.menu_filtri);
        toolbar1.setOnMenuItemClickListener(this::onOptionsItemSelected);
         adapter=new ArrayAdapter<String>(this, R.layout.list,recensioni);
         aggiungi=(Button) findViewById(R.id.buttonAggiungi);
        aggiungi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nickname != null) {
                    Dialog d = new Dialog(StrutturaActivity.this);
                    d.setTitle("Login");
                    d.setCancelable(false);
                    d.setContentView(R.layout.dialog_aggiungi);
                    d.show();
                    Spinner voto = (Spinner) d.findViewById(R.id.voto);
                    ArrayAdapter<String> adapter1;
                    ArrayList<String> spinnerList1;
                    spinnerList1 = new ArrayList<>();
                    adapter1 = new ArrayAdapter<String>(d.getContext(), android.R.layout.simple_spinner_dropdown_item, spinnerList1);
                    voto.setAdapter(adapter1);
                    for (int i = 0; i <= 5; i++) {
                        spinnerList1.add(String.valueOf(i));
                    }
                    adapter1.notifyDataSetChanged();
                    ImageButton b = (ImageButton) d.findViewById(R.id.imageButton);
                    TextView commento=(TextView)d.findViewById(R.id.textCommento);
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View arg0) {
                            d.dismiss();
                        }
                    });
                    Button b1=(Button) d.findViewById(R.id.invia);
                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View arg0) {
                            int i=getTopRecensione();
                            if(checkRecensionePresente(nickname,String.valueOf(info1.getText()))){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(StrutturaActivity.this,"Recensione gia presente su questa struttura",Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                            else{
                                saveRecensione(i,String.valueOf(commento.getText()),Integer.valueOf(String.valueOf(voto.getSelectedItem())),nickname,String.valueOf(info1.getText()));
                                Toast.makeText(StrutturaActivity.this,"Recensione aggiunta",Toast.LENGTH_LONG).show();
                                d.dismiss();
                                Intent intent = new Intent(StrutturaActivity.this, StrutturaActivity.class);
                                intent.putExtra("nickname", nickname);
                                intent.putExtra("string",informa);
                                startActivity(intent);
                                finish();

                            }
                        }
                    });


                }
                else{
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(StrutturaActivity.this,"Devi esssere loggato",Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });


        ;


        if(ConnectionClass.con == null){
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
                Statement stmt = ConnectionClass.con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);



                while (rs.next()) {
                    String sql2 = "SELECT * FROM Utente WHERE nickname = '" + rs.getString("nickname") +  "' ";
                    Statement stmt2 = ConnectionClass.con.createStatement();
                    ResultSet  rs2 = stmt2.executeQuery(sql2);
                    if(rs2.next()) {
                        if (rs2.getInt("FlagNickname") != 0) {
                            recensioni.add(rs.getString("nickname") + " \n" + rs.getString("commento") + "\n" + "Voto : " + rs.getString("stelle"));
                        } else
                            recensioni.add(rs2.getString("nome") +"   "+ rs2.getString("cognome") +" \n" + rs.getString("commento") + "\n" + "Voto : " + rs.getString("stelle"));
                    }

                }

                    mylist.setAdapter(adapter);


            } catch (Exception c) {

                Log.e("SQL Error : ", c.getMessage());
            }
            try {
                String sql = "SELECT LinkImg FROM Struttura WHERE indirizzo = '" + info1.getText() +  "' ";
                Statement stmt = ConnectionClass.con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);



                if (rs.next()) {


                    Glide.with(this)
                            .load(rs.getString("LinkImg"))
                            .into(image1);

                }

            } catch (Exception c) {

                Log.e("SQL Error : ", c.getMessage());
            }
        }


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        MenuInflater menuInflater = getMenuInflater();
        if(nickname==null)
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
                EditText username;
                EditText password;
                username = (EditText) d.findViewById(R.id.user);
                username.setText(username.getText().toString());
                password = (EditText) d.findViewById(R.id.passw);
                password.setText(password.getText().toString());
                Button bottone1;
                bottone1=(Button) d.findViewById(R.id.button3);
                b.setOnClickListener(new View.OnClickListener()
                {
                    @Override
                    public void onClick(View arg0)
                    {
                        d.dismiss();
                    }
                });
                bottone1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String valore=username.getText().toString();
                        if (valore.isEmpty()) {
                            Toast.makeText(StrutturaActivity.this, "campo username vuoto", Toast.LENGTH_LONG).show();
                        }
                        else {

                            if(ConnectionClass.con==null)
                               { runOnUiThread(new Runnable() {
                              @Override
                                public void run() {
                                  Toast.makeText(StrutturaActivity.this,"Check DBMS Connection",Toast.LENGTH_LONG).show();
                                }
                             });
                               }
                            else{

                                utente.LogIn(String.valueOf(username.getText()),String.valueOf(password.getText()));
                                if(utente.getUtente()!=null) {

                                    if (utente.getUtente().getFlagBlacklist() == 0) {
                                        nickname = utente.getUtente().getNickname();
                                        Intent intent = new Intent(StrutturaActivity.this, StrutturaActivity.class);
                                        intent.putExtra("nickname", nickname);
                                        intent.putExtra("string",informa);
                                        startActivity(intent);
                                        finish();
                                    } else if (utente.getUtente().getFlagBlacklist() == 1) {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(StrutturaActivity.this, "Account Bloccato", Toast.LENGTH_LONG).show();
                                            }
                                        });
                                    }
                                }else {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(StrutturaActivity.this, "Check username or password", Toast.LENGTH_LONG).show();
                                        }
                                    });
                                }
                            }

                        }


                    }

                });

            }
        }
        switch(item.getItemId()){
            case R.id.registra:{
                Intent openPage1 = new Intent(StrutturaActivity.this,RegistrazioneActivity.class);
                startActivity(openPage1);


            }

        }
        switch(item.getItemId()){
            case R.id.logout:{
                utente.setLogOut(nickname);
                Intent openPage1 = new Intent(StrutturaActivity.this,MainActivity.class);
                startActivity(openPage1);


            }
        }
        switch(item.getItemId()){
            case R.id.menu_filtri:{
                AlertDialog.Builder mBuilder=new AlertDialog.Builder(StrutturaActivity.this);
                View mView=getLayoutInflater().inflate(R.layout.dialog_filtri,null);
                mBuilder.setTitle("Filtri");
                spin=(Spinner)mView.findViewById(R.id.rating_spinner);
                ArrayAdapter<String> adapter1=new ArrayAdapter<String>(StrutturaActivity.this,android.R.layout.simple_spinner_item,getResources().getStringArray(R.array.stelle));
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spin.setAdapter(adapter1);
                mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(!spin.getSelectedItem().toString().equals("")) {
                            try {
                                String sql = "SELECT * FROM Recensioni WHERE indirizzo = '" + info1.getText() +"' AND Stelle= '" + spin.getSelectedItem().toString()  + "' ";
                                Statement stmt = ConnectionClass.con.createStatement();
                                ResultSet rs = stmt.executeQuery(sql);


                                recensioni.clear();
                                while (rs.next()) {
                                    String sql2 = "SELECT * FROM Utente WHERE nickname = '" + rs.getString("nickname") +  "' ";
                                    Statement stmt2 = ConnectionClass.con.createStatement();
                                    ResultSet  rs2 = stmt2.executeQuery(sql2);
                                    if(rs2.next()) {
                                        if (rs2.getInt("FlagNickname") != 0) {
                                            recensioni.add(rs.getString("nickname") + " \n" + rs.getString("commento") + "\n" + "Voto : " + rs.getString("stelle"));
                                        } else
                                            recensioni.add(rs2.getString("nome") +"   "+ rs2.getString("cognome") +" \n" + rs.getString("commento") + "\n" + "Voto : " + rs.getString("stelle"));
                                    }

                                }
                                if(recensioni.isEmpty()){
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            mylist.setAdapter(adapter);
                                            Toast.makeText(StrutturaActivity.this,"Nessuna recensione trovata",Toast.LENGTH_LONG).show();
                                        }
                                    });
                                }
                                    mylist.setAdapter(adapter);



                            } catch (Exception c) {

                                Log.e("SQL Error : ", c.getMessage());
                            }
                        }
                        else  {
                            try {
                                String sql = "SELECT * FROM Recensioni WHERE indirizzo = '" + info1.getText() +  "' ";
                                Statement stmt = ConnectionClass.con.createStatement();
                                ResultSet rs = stmt.executeQuery(sql);


                                recensioni.clear();
                                while (rs.next()) {
                                    String sql2 = "SELECT * FROM Utente WHERE nickname = '" + rs.getString("nickname") +  "' ";
                                    Statement stmt2 = ConnectionClass.con.createStatement();
                                    ResultSet  rs2 = stmt2.executeQuery(sql2);
                                    if(rs2.next()) {
                                        if (rs2.getInt("FlagNickname") != 0) {
                                            recensioni.add(rs.getString("nickname") + " \n" + rs.getString("commento") + "\n" + "Voto : " + rs.getString("stelle"));
                                        } else
                                            recensioni.add(rs2.getString("nome") +"   "+ rs2.getString("cognome") +" \n" + rs.getString("commento") + "\n" + "Voto : " + rs.getString("stelle"));
                                    }

                                }
                                if(recensioni.isEmpty()){
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            mylist.setAdapter(adapter);
                                            Toast.makeText(StrutturaActivity.this,"Nessuna recensione trovata",Toast.LENGTH_LONG).show();
                                        }
                                    });
                                }
                                    mylist.setAdapter(adapter);




                            } catch (Exception c) {

                                Log.e("SQL Error : ", c.getMessage());
                            }
                        }




                        dialogInterface.dismiss();




                }
                });
                mBuilder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                mBuilder.setView(mView);
                AlertDialog dialog=mBuilder.create();
                dialog.show();


            }}

        return super.onOptionsItemSelected(item);
    }

}