package com.example.progettoingsw;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.progettoingsw.Connection.ConnectionClass;
import com.example.progettoingsw.Dao.UtenteDaoImp;

import java.sql.Connection;

import static com.example.progettoingsw.Connection.ConnectionClass.connectionClass;

public class MainActivity2 extends Activity {
    String nickname;
    TextView nick;
    Connection con;
    UtenteDaoImp utente;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Button bottone1=(Button) findViewById(R.id.bottone1);
        nick=(TextView) findViewById(R.id.nickname);
        con =connectionClass(ConnectionClass.un.toString(),ConnectionClass.pass.toString(),ConnectionClass.db.toString(),ConnectionClass.ip.toString());
        Bundle e= getIntent().getExtras();
        utente=new UtenteDaoImp();
        if(e!= null)
        {
            nickname=e.getString("nickname");
        }
        nick.setText("Benvenuto"+ '\n'+nickname);
        bottone1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openPage1 = new Intent(MainActivity2.this,RicercaActivity.class);
                openPage1.putExtra("nickname",nickname);
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
                UtenteDaoImp utente=new UtenteDaoImp();
                utente.setLogOut(nickname);
                // passo all'attivazione dell'activity Pagina.java
                startActivity(openPage1);
                finish();
            }
        });
    }
    public void onBackPressed() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Exit Page?");
        alertDialogBuilder
                .setMessage("Click yes to exit!")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                moveTaskToBack(true);
                                utente.setLogOut(nickname);


                                Intent intent=new Intent(MainActivity2.this,MainActivity.class);
                                startActivity(intent);


                                finish();


                            }
                        })

                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    @Override
    public void onDestroy() {
        // RUN SUPER | REGISTER ACTIVITY AS NULL IN APP CLASS
        if(nickname!=null)
            utente.setLogOut(nickname);
        super.onDestroy();

    }
}