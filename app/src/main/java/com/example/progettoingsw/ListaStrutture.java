package com.example.progettoingsw;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

public class ListaStrutture extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_strutture);
        final ListView mylist = (ListView) findViewById(R.id.listview);
        Intent intent = getIntent();
        Bundle args = intent.getBundleExtra("strutture");
        ArrayList<String> object = (ArrayList<String>) args.getSerializable("ARRAYLIST");


        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this, R.layout.list,object);
        mylist.setAdapter(adapter);
    }
}