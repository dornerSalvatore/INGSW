package com.example.progettoingsw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.progettoingsw.Dao.UtenteDaoImp;

import java.util.ArrayList;

public class ListaStrutture extends AppCompatActivity {
    String nickname;
    UtenteDaoImp utente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_strutture);
        final ListView mylist = (ListView) findViewById(R.id.listview);
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("strutture");
        ArrayList<String> object = (ArrayList<String>) args.getSerializable("ARRAYLIST");
        Bundle e= intent.getExtras();
        utente=new UtenteDaoImp();
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
                Intent openPage1 = new Intent(ListaStrutture.this,StrutturaActivity.class);
                openPage1.putExtra("string",av.getItemAtPosition(pos).toString());
                openPage1.putExtra("nickname",nickname);
                // passo all'attivazione dell'activity Pagina.java
                startActivity(openPage1);
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