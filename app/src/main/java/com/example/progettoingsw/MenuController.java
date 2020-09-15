package com.example.progettoingsw;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.progettoingsw.Connection.ConnectionClass;
import com.example.progettoingsw.Dao.Recensione;

import java.util.ArrayList;

public class MenuController {
    StrutturaActivity frame;
    public MenuController(StrutturaActivity view)
    {
        frame=view;
    }
    public   MenuController(StrutturaActivity view, Menu menu, MenuInflater menuInflater)
    {
        frame=view;
        if (frame.nickname == null)
            menuInflater.inflate(R.menu.menu_login, menu);
        else {
            menuInflater.inflate(R.menu.menu, menu);

        }



    }
    public   void onOptionItemSelected1(@NonNull MenuItem item,StrutturaActivity frame)
    {
        switch (item.getItemId()) {
            case R.id.login: {

                Dialog d = new Dialog(frame);
                d.setTitle("Login");
                d.setCancelable(false);
                d.setContentView(R.layout.dialog_login);
                d.show();
                ImageButton b = (ImageButton) d.findViewById(R.id.imageButton);
                EditText username;
                EditText password;
                username = (EditText) d.findViewById(R.id.user);
                username.setText(username.getText().toString());
                password = (EditText) d.findViewById(R.id.passw);
                password.setText(password.getText().toString());
                Button bottone1;
                bottone1 = (Button) d.findViewById(R.id.button3);
                b.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        d.dismiss();
                    }
                });
                bottone1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String valore = username.getText().toString();
                        if (valore.isEmpty()) {
                            Toast.makeText(frame, "campo username vuoto", Toast.LENGTH_LONG).show();
                        } else {

                            if (ConnectionClass.con == null) {
                                frame.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(frame, "Check DBMS Connection", Toast.LENGTH_LONG).show();
                                    }
                                });
                            } else {

                                frame.utente.LogIn(String.valueOf(username.getText()), String.valueOf(password.getText()));
                                if (frame.utente.getUtente() != null) {

                                    if (frame.utente.getUtente().getFlagBlacklist() == 0) {
                                        frame.nickname = frame.utente.getUtente().getNickname();
                                        Intent intent = new Intent(frame, StrutturaActivity.class);
                                        intent.putExtra("nickname", frame.nickname);
                                        intent.putExtra("string", frame.informa);
                                        frame.startActivity(intent);
                                    } else if (frame.utente.getUtente().getFlagBlacklist() == 1) {
                                        frame.runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                Toast.makeText(frame, "Account Bloccato", Toast.LENGTH_LONG).show();
                                            }
                                        });
                                    }
                                } else {
                                    frame.runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            Toast.makeText(frame, "Check username or password", Toast.LENGTH_LONG).show();
                                        }
                                    });
                                }
                            }

                        }


                    }

                });

            }
        }
        switch (item.getItemId()) {
            case R.id.registra: {
                Intent openPage1 = new Intent(frame, RegistrazioneActivity.class);
                frame.startActivity(openPage1);



            }

        }
        switch (item.getItemId()) {
            case R.id.logout: {
                frame.utente.setLogOut(frame.nickname);
                Intent openPage1 = new Intent(frame, MainActivity.class);
                frame.startActivity(openPage1);
                frame.finish();


            }
        }
        switch (item.getItemId()) {
            case R.id.menu_filtri: {
                AlertDialog.Builder mBuilder = new AlertDialog.Builder(frame);
                View mView = frame.getLayoutInflater().inflate(R.layout.dialog_filtri, null);
                mBuilder.setTitle("Filtri");
                frame.spin = (Spinner) mView.findViewById(R.id.rating_spinner);
                ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(frame, android.R.layout.simple_spinner_item, frame.getResources().getStringArray(R.array.stelle));
                adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                frame.spin.setAdapter(adapter1);
                mBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (!frame.spin.getSelectedItem().toString().equals("")) {
                            frame.recensioni.clear();
                            ArrayList<Recensione> r = frame.recensione.getRecensioniByIdStelle(String.valueOf(frame.info1.getText()),
                                    Integer.valueOf(frame.spin.getSelectedItem().toString()));
                            for (int j = 0; j < r.size(); j++) {
                                Recensione r1 = r.get(j);
                                frame.u = null;
                                frame.u = r1.getAggiuntaDa();

                                if (frame.u != null) {

                                    if (frame.u.getFlagNickname() != 0) {
                                        frame.recensioni.add(frame.u.getNickname() + " \n" + r1.getCommento() + "\n" + "Voto : " + r1.getStelle());
                                    } else {
                                        frame.recensioni.add(frame.u.getNome() + "   " + frame.u.getCognome() + " \n" + r1.getCommento() + "\n" + "Voto : " + r1.getStelle());
                                    }
                                }
                            }


                            if (frame.recensioni.isEmpty()) {
                                frame.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        frame.mylist.setAdapter(frame.adapter);
                                        Toast.makeText(frame, "Nessuna recensione trovata", Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                            frame.mylist.setAdapter(frame.adapter);




                        } else {
                            frame.recensioni.clear();
                            ArrayList<Recensione> r = frame.recensione.getRecensioniById(String.valueOf(frame.info1.getText()));
                            for (int p = 0; p < r.size(); p++) {
                                Recensione r1 = r.get(p);
                                frame.u = null;
                                frame.u = r1.getAggiuntaDa();

                                if (frame.u != null) {

                                    if (frame.u.getFlagNickname() != 0) {
                                        frame.recensioni.add(frame.u.getNickname() + " \n" + r1.getCommento() + "\n" + "Voto : " + r1.getStelle());
                                    } else {
                                        frame.recensioni.add(frame.u.getNome() + "   " + frame.u.getCognome() + " \n" + r1.getCommento() + "\n" + "Voto : " + r1.getStelle());
                                    }
                                }
                            }

                            if (frame.recensioni.isEmpty()) {
                                frame.runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        frame.mylist.setAdapter(frame.adapter);
                                        Toast.makeText(frame, "Nessuna recensione trovata", Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                            frame.mylist.setAdapter(frame.adapter);




                        }


                        dialogInterface.dismiss();


                    }
                });
                mBuilder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                mBuilder.setView(mView);
                AlertDialog dialog = mBuilder.create();
                dialog.show();


            }
        }

    }
}
