package com.example.progettoingsw;


import android.content.Intent;
import android.view.View;

import android.widget.Toast;



public class ButtonControllerT{
        private MainActivityT frame;
        private RegistrazioneActivityT frame3;


        public ButtonControllerT(MainActivityT view) {
            frame = view;
            frame.bottone1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    frame.valore=frame.username.getText().toString();
                    if (frame.valore.isEmpty()) {
                        Toast.makeText(frame, "campo username vuoto", Toast.LENGTH_LONG).show();
                    } else {


                        Toast.makeText(frame, "funzione Test non disponibile", Toast.LENGTH_LONG).show();


                    }
                }
            });
            frame.bottone3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(frame, "funzione Test non disponibile", Toast.LENGTH_LONG).show();
                }

            });


            frame.bottone2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent openPage1 = new Intent(frame,RegistrazioneActivityT.class);
                    // passo all'attivazione dell'activity Pagina.java
                    frame.startActivity(openPage1);

                }
            });
        }



        public ButtonControllerT(RegistrazioneActivityT view)
        {
            frame3=view;
            CheckRegistrazioneControllerT c=new CheckRegistrazioneControllerT(frame3);



            frame3.bottone1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent openPage1 = new Intent(frame3,MainActivity.class);
                    // passo all'attivazione dell'activity Pagina.java
                    frame3.startActivity(openPage1);
                }
            });
            frame3.bottone2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (!c.controlEmail() | !c.controlNickname() | ! c.controlPassword() | !c.controlUsername()) {
                        return;
                    } else {
                        new CheckRegistrazioneControllerT(frame3).execute(""); //codice per eseguire la connessione e interrogazione al DBMS
                    }
                }
            });
        }

}
