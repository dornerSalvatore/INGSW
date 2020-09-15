package com.example.progettoingsw;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.progettoingsw.Dao.UtenteDaoImp;

public class ButtonController {
    public MainActivity frame;
    public MainActivity2 frame1;
    public RecensioneActivity frame2;
    public RegistrazioneActivity frame3;

    public ButtonController(MainActivity view) {
        frame = view;
        frame.bottone1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frame.valore=frame.username.getText().toString();
                if (frame.valore.isEmpty()) {
                    Toast.makeText(frame, "campo username vuoto", Toast.LENGTH_LONG).show();
                } else {


                    new CheckLoginController(frame).execute("");


                }
            }
        });
        frame.bottone3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openPage1 = new Intent(frame,RicercaActivity.class);
                // passo all'attivazione dell'activity Pagina.java
                frame.startActivityForResult(openPage1, 0);
            }
        });


        frame.bottone2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openPage1 = new Intent(frame,RegistrazioneActivity.class);
                // passo all'attivazione dell'activity Pagina.java
                frame.startActivity(openPage1);

            }
        });
    }

        public ButtonController(MainActivity2 view) {
            frame1 = view;
            frame1.bottone1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent openPage1 = new Intent(frame1,RicercaActivity.class);
                    openPage1.putExtra("nickname",frame1.nickname);
                    // passo all'attivazione dell'activity Pagina.java
                    frame1.startActivity(openPage1);
                }
            });
            frame1.bottone2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent openPage1 = new Intent(frame1,RecensioneActivity.class);
                    openPage1.putExtra("nickname",frame1.nickname);
                    // passo all'attivazione dell'activity Pagina.java
                    frame1.startActivity(openPage1);
                }
            });
            frame1.bottone3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent openPage1 = new Intent(frame1,MainActivity.class);
                    UtenteDaoImp utente=new UtenteDaoImp();
                    utente.setLogOut(frame1.nickname);
                    // passo all'attivazione dell'activity Pagina.java
                    frame1.startActivity(openPage1);
                    frame1.finish();
                }
            });


        }
    public void onBackPressed(MainActivity2 frame) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(frame);
        alertDialogBuilder.setTitle("Exit Page?");
        alertDialogBuilder
                .setMessage("Click yes to exit!")
                .setCancelable(false)
                .setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                frame.moveTaskToBack(true);
                                frame.utente.setLogOut(frame.nickname);


                                Intent intent=new Intent(frame,MainActivity.class);
                                frame.startActivity(intent);


                                frame.finish();


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
    public ButtonController(RecensioneActivity view){
        frame2 = view;
        frame2.bottone1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openPage1 = new Intent(frame2,MainActivity2.class);
                openPage1.putExtra("nickname",frame2.nickname);
                // passo all'attivazione dell'activity Pagina.java
                frame2.startActivity(openPage1);
            }
        });
        frame2.bottone2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // passo all'attivazione dell'activity Pagina.java
                new aggiungiRecensioneController(frame2).execute("");

            }
        });


    }
    public ButtonController(RegistrazioneActivity view)
    {
        frame3=view;
         checkRegistrazioneController c=new  checkRegistrazioneController(frame3);



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
                    new  checkRegistrazioneController(frame3).execute(""); //codice per eseguire la connessione e interrogazione al DBMS
                }
            }
        });
    }



}



