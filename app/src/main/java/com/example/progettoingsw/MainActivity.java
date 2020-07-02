package com.example.progettoingsw;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import java.lang.Object;

import android.app.Activity;
import android.content.Intent;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText myEditText = (EditText) findViewById(R.id.editTextTextPersonName);
        myEditText.setText(myEditText.getText().toString());
        Button bottone1=(Button) findViewById(R.id.bottone1);
        bottone1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("ERRORE");
                dialog.setMessage("username non valido");
                dialog.show();*/
                Dialog dialog ;
                //dialog = new Dialog ();
                /*dialog.setContentView(R.layout.dialog1);


                Button bottone2 = (Button) findViewById(R.id.button);
                bottone2.setOnClickListener(new View.OnClickListener() {
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                dialog.show();*/
              Button bottone2=(Button) findViewById(R.id.bottone1);
                      bottone2.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View view) {
                              Intent openPage1 = new Intent(MainActivity.this,MainActivity2.class);
                              // passo all'attivazione dell'activity Pagina.java
                              startActivity(openPage1);
                          }
                      });
            }
        });



        Button bottone3=(Button) findViewById(R.id.bottone3);
        bottone3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openPage1 = new Intent(MainActivity.this,RicercaActivity.class);
                // passo all'attivazione dell'activity Pagina.java
                startActivityForResult(openPage1, 0);
            }
        });

        Button bottone2=(Button) findViewById(R.id.bottone2);
        bottone2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openPage1 = new Intent(MainActivity.this,RegistrazioneActivity.class);
                // passo all'attivazione dell'activity Pagina.java
                startActivity(openPage1);
            }
        });

        };
        }
