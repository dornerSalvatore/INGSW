package com.example.progettoingsw;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import com.example.progettoingsw.Connection.ConnectionClass;
import com.example.progettoingsw.Dao.Struttura;

import java.io.Serializable;
import java.util.ArrayList;



public class RicercaController extends AsyncTask<String, String, String> {
    String z = null;
    RicercaActivity frame;


    public RicercaController(RicercaActivity view){frame=view;}

    @Override
    protected void onPreExecute() {


    }

    @Override
    protected void onPostExecute(String s) {


    }

    @Override
    protected String doInBackground(String... strings) {

        if (ConnectionClass.con == null) {
            frame.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(frame, "Check DBMS Connection", Toast.LENGTH_LONG).show();
                }
            });
        } else {
            frame.struttura1=null;
            if(controlNome() && controlPrezzo())
            { if(frame.testo!="") frame.struttura1=frame.struttura.getListaStruttureByPar(frame.spin.getSelectedItem().toString(),String.valueOf(frame.nome.getText()), String.valueOf(frame.citta.getText()),String.valueOf(frame.provincia.getText()),Integer.parseInt(frame.testo),Integer.parseInt(frame.testo1));
            else frame.struttura1=frame.struttura.getListaStruttureByPar(frame.spin.getSelectedItem().toString(),String.valueOf(frame.nome.getText()), String.valueOf(frame.citta.getText()),String.valueOf(frame.provincia.getText()),Integer.parseInt(frame.testo));
            }
            else {
                if (!controlNome() && !controlPrezzo()) {
                    frame.struttura1=frame.struttura.getListaStruttureByPar(frame.spin.getSelectedItem().toString(), String.valueOf(frame.citta.getText()),String.valueOf(frame.provincia.getText()));
                } else {
                    if (!controlPrezzo()) {
                        frame.struttura1=frame.struttura.getListaStruttureByPar(frame.spin.getSelectedItem().toString(),String.valueOf(frame.nome.getText()), String.valueOf(frame.citta.getText()),String.valueOf(frame.provincia.getText()));
                    } else  {
                        if (frame.testo != "")
                            frame.struttura1=frame.struttura.getListaStruttureByPar(frame.spin.getSelectedItem().toString(), String.valueOf(frame.citta.getText()),String.valueOf(frame.provincia.getText()),Integer.parseInt(frame.testo),Integer.parseInt(frame.testo1));
                        else
                            frame.struttura1=frame.struttura.getListaStruttureByPar(frame.spin.getSelectedItem().toString(), String.valueOf(frame.citta.getText()),String.valueOf(frame.provincia.getText()),Integer.parseInt(frame.testo));
                    }
                }
            }
            ArrayList<String> strutture =new ArrayList<String>();
            if(!frame.struttura1.isEmpty())
            {
                for(Struttura st:frame.struttura1){
                    strutture.add(st.getNome()+":"+st.getIndirizzo());
                 ;

                }




                z = "Success";
                Intent intent = new Intent(frame, ListaStrutture.class);
                Bundle args = new Bundle();
                args.putSerializable("ARRAYLIST",(Serializable)strutture);
                intent.putExtra("strutture",args);
                intent.putExtra("nickname",frame.nickname);
                frame.startActivity(intent);

            }




            else{
                frame.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(frame, "Nessun risultato", Toast.LENGTH_LONG).show();
                    }
                });
            }




        }
        return z;

    }
    public Boolean controlTipologia() {
        String val = frame.spin.getSelectedItem().toString();

        if (val.isEmpty()) {
            Toast.makeText(frame, "Tipologia non selezionata", Toast.LENGTH_LONG).show();
            return false;
        }
        else {
            return true;
        }
    }
    public Boolean controlPrezzo() {
        String val = frame.prezzo.getSelectedItem().toString();

        if (val.isEmpty() | val=="") {
            return false;
        }
        else {
            return true;
        }
    }
    public Boolean controlCitta() {
        String val = frame.citta.getText().toString();

        if (val.isEmpty()) {
            frame.citta.setError("Field cannot be empty");
            return false;
        }
        else {
            frame.citta.setError(null);
            return true;
        }
    }
    public Boolean controlProvincia() {
        String val = frame.provincia.getText().toString();

        if (val.isEmpty()) {
            frame.provincia.setError("Field cannot be empty");
            return false;
        }
        else {
            frame.provincia.setError(null);
            return true;
        }
    }
    public Boolean controlNome() {
        String val = frame.nome.getText().toString();

        if (val.isEmpty()) {
            return false;
        }
        else {
            return true;
        }
    }

}
