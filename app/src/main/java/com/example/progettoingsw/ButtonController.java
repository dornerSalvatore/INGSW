package com.example.progettoingsw;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.progettoingsw.Dao.UtenteDaoImp;

import java.util.ArrayList;

import static com.example.progettoingsw.Connection.ConnectionClass.getTopRecensione;

public class ButtonController {
    public MainActivity frame;
    public MainActivity2 frame1;
    public RecensioneActivity frame2;
    public RegistrazioneActivity frame3;
    public RicercaActivity frame4;
    public StrutturaActivity frame5;

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
    public ButtonController(RicercaActivity view)
    {
        frame4=view;
        RicercaController c=new  RicercaController(frame4);
        frame4.bottone1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!c.controlTipologia() | !c.controlCitta() | !c.controlProvincia()){return;}
                else
                    new RicercaController(frame4).execute("");


            }
        });

        frame4.bottone2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent2 = new Intent(frame4, MapsActivity.class);
                intent2.putExtra("nickname",frame4.nickname);
                frame4.startActivity(intent2);
                frame4.finish();
            }
        });

    }
    public ButtonController(StrutturaActivity view)
    {
        frame5=view;
        frame5.aggiungi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (frame5.nickname != null) {
                    Dialog d = new Dialog(frame5);
                    d.setTitle("Login");
                    d.setCancelable(false);
                    d.setContentView(R.layout.dialog_aggiungi);
                    d.show();
                    Spinner voto = (Spinner) d.findViewById(R.id.voto);
                    ArrayAdapter<String> adapter1;
                    ArrayList<String> spinnerList1;
                    spinnerList1 = new ArrayList<>();
                    adapter1 = new ArrayAdapter<String>(d.getContext(), android.R.layout.simple_spinner_dropdown_item, spinnerList1);
                    voto.setAdapter(adapter1);
                    for (int i = 0; i <= 5; i++) {
                        spinnerList1.add(String.valueOf(i));
                    }
                    adapter1.notifyDataSetChanged();
                    ImageButton b = (ImageButton) d.findViewById(R.id.imageButton);
                    TextView commento = (TextView) d.findViewById(R.id.textCommento);
                    b.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View arg0) {
                            d.dismiss();
                        }
                    });
                    Button b1 = (Button) d.findViewById(R.id.invia);
                    b1.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View arg0) {
                            int i = getTopRecensione();
                            if (frame5.recensione.checkRecensionePresente(frame5.nickname, String.valueOf(frame5.info1.getText()))) {
                                frame5.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(frame5, "Recensione gia presente su questa struttura", Toast.LENGTH_LONG).show();
                                    }
                                });
                            } else {
                                frame5.recensione.saveRecensione(i, String.valueOf(commento.getText()), Integer.valueOf(String.valueOf(voto.getSelectedItem())), frame5.nickname, String.valueOf(frame5.info1.getText()));
                                Toast.makeText(frame5, "Recensione aggiunta", Toast.LENGTH_LONG).show();
                                d.dismiss();
                                Intent intent = new Intent(frame5, StrutturaActivity.class);
                                intent.putExtra("nickname", frame5.nickname);
                                intent.putExtra("string", frame5.informa);
                                frame5.startActivity(intent);
                                frame5.finish();

                            }
                        }
                    });


                } else {
                    frame5.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(frame5, "Devi esssere loggato", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }
        });



    }



}



