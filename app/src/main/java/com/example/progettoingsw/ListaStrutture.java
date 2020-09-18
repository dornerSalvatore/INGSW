package com.example.progettoingsw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.progettoingsw.Dao.UtenteDaoImp1;

import java.util.ArrayList;

public class ListaStrutture extends AppCompatActivity {
    String nickname;
    UtenteDaoImp1 utente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_strutture);
        final ListView mylist = (ListView) findViewById(R.id.listview);
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("strutture");
        ArrayList<String> object = (ArrayList<String>) args.getSerializable("ARRAYLIST");
        Bundle e= intent.getExtras();
        utente=new UtenteDaoImp1();
        if(e!= null)
        {
            nickname=e.getString("nickname");
        }

        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.list,object);
        mylist.setAdapter(adapter);
        mylist.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> av, View v, int pos, long id)
            {
                new ArrayAdapterController(ListaStrutture.this).onItemClick(av,v,pos,id);
            }
        });

    }
    @Override
    public void onDestroy() {
        // RUN SUPER | REGISTER ACTIVITY AS NULL IN APP CLASS
        if(nickname!=null)
            utente.setLogOut(nickname);
        super.onDestroy();

    }
}