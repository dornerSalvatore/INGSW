package com.example.progettoingsw;

import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import static com.example.progettoingsw.Connection.ConnectionClass.setVisitatori;

public class MainActivityT extends AppCompatActivity {



        EditText username;
        EditText password;
        Button bottone1;
        Button bottone3;
        Button bottone2;
        String valore;



        @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);
            setVisitatori();
            username = (EditText) findViewById(R.id.editTextTextPersonName);
            username.setText(username.getText().toString());
            password = (EditText) findViewById(R.id.editTextTextPassword);
            password.setText(password.getText().toString());
            bottone1=(Button) findViewById(R.id.bottone1);
            bottone3=(Button) findViewById(R.id.bottone3);
            bottone2=(Button) findViewById(R.id.bottone2);

            new ButtonControllerT(MainActivityT.this);

        };
    }

