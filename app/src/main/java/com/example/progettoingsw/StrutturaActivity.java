package com.example.progettoingsw;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;


import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.widget.ArrayAdapter;
import android.widget.Button;

import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.progettoingsw.Connection.ConnectionClass;

import com.example.progettoingsw.Dao.Recensione;
import com.example.progettoingsw.Dao.RecensioneDaoImp;
import com.example.progettoingsw.Dao.RicercaStrutturaDaoImp;
import com.example.progettoingsw.Dao.StrutturaDaoImp;
import com.example.progettoingsw.Dao.Utente;
import com.example.progettoingsw.Dao.UtenteDaoImp1;



import java.util.ArrayList;
import java.util.regex.Pattern;




public class StrutturaActivity extends AppCompatActivity {
    String informa;
    String[] nomi;
    boolean b = true;
    Spinner spin;
    ArrayList<String> recensioni = new ArrayList<String>();
    ArrayAdapter<String> adapter;
    TextView info1;
    ListView mylist;
    String nickname;
    Button aggiungi;
    ImageView image1;
    UtenteDaoImp1 utente;
    RecensioneDaoImp recensione;
    StrutturaDaoImp struttura;
    Utente u;
    MenuController c;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.struttura);
        utente = new UtenteDaoImp1();
        recensione = new RecensioneDaoImp();
        struttura = new StrutturaDaoImp();
        mylist = (ListView) findViewById(R.id.lista);
        Bundle e = getIntent().getExtras();
        if (e != null) {
            informa = e.getString("string");
            nomi = informa.split(Pattern.quote(":"));
            nickname = e.getString("nickname");
        }
        TextView info = (TextView) findViewById(R.id.inf);
        info1 = (TextView) findViewById(R.id.indirizzo);
        info.setText(nomi[0]);
        info1.setText(nomi[1]);
        image1 = (ImageView) findViewById(R.id.imageView);
        androidx.appcompat.widget.Toolbar toolbar = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar);
        androidx.appcompat.widget.Toolbar toolbar1 = (androidx.appcompat.widget.Toolbar) findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar);
        toolbar1.inflateMenu(R.menu.menu_filtri);
        toolbar1.setOnMenuItemClickListener(this::onOptionsItemSelected);
        adapter = new ArrayAdapter<String>(this, R.layout.list, recensioni);
        aggiungi = (Button) findViewById(R.id.buttonAggiungi);
        c= new MenuController(StrutturaActivity.this);
        if(nickname!=null)  new RicercaStrutturaDaoImp().ricercaStruttura(nickname,String.valueOf(info1.getText()));
        new ButtonController(StrutturaActivity.this);





        if (ConnectionClass.con == null) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(StrutturaActivity.this, "Check DBMS Connection", Toast.LENGTH_LONG).show();
                }
            });

        }
        ArrayList<Recensione> r = recensione.getRecensioniById(String.valueOf(info1.getText()));
        for (int i = 0; i < r.size(); i++) {
            Recensione r1 = r.get(i);
            u = null;
            u = r1.getAggiuntaDa();

            if (u != null) {

                if (u.getFlagNickname() != 0) {
                    recensioni.add(u.getNickname() + " \n" + r1.getCommento() + "\n" + "Voto : " + r1.getStelle());
                } else {
                    recensioni.add(u.getNome() + "   " + u.getCognome() + " \n" + r1.getCommento() + "\n" + "Voto : " + r1.getStelle());
                }
            }
        }
        mylist.setAdapter(adapter);
        String imm = struttura.getLinkImgByInd(String.valueOf(info1.getText()));

        if (imm != null) {


            Glide.with(this)
                    .load(imm)
                    .into(image1);
        }



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {


        MenuInflater menuInflater = getMenuInflater();
        c=new MenuController(StrutturaActivity.this,menu,menuInflater);


        return true;
    }


    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
         c.onOptionItemSelected1(item,StrutturaActivity.this);
         return true;

    }




    @Override
    public void onDestroy() {
        // RUN SUPER | REGISTER ACTIVITY AS NULL IN APP CLASS
        if(nickname!=null)
            utente.setLogOut(nickname);
        super.onDestroy();

    }
}