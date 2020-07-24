package com.example.progettoingsw;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity2 extends Activity {
    String nickname;
    TextView nick;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button bottone1=(Button) findViewById(R.id.bottone1);
        nick=(TextView) findViewById(R.id.nickname);
        Bundle e= getIntent().getExtras();
        if(e!= null)
        {
            nickname=e.getString("nickname");
        }
        nick.setText("Benvenuto"+ '\n'+nickname);
        bottone1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openPage1 = new Intent(MainActivity2.this,RicercaActivity.class);
                // passo all'attivazione dell'activity Pagina.java
                startActivity(openPage1);
            }
        });
        Button bottone2 =(Button) findViewById(R.id.bottone2);
        bottone2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openPage1 = new Intent(MainActivity2.this,RecensioneActivity.class);
                openPage1.putExtra("nickname",nickname);
                // passo all'attivazione dell'activity Pagina.java
                startActivity(openPage1);
            }
        });
        Button bottone3=(Button) findViewById(R.id.bottone3);
        bottone3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openPage1 = new Intent(MainActivity2.this,MainActivity.class);
                // passo all'attivazione dell'activity Pagina.java
                startActivity(openPage1);
            }
        });
    }
}