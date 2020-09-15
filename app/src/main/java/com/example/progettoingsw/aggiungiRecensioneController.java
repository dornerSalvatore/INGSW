package com.example.progettoingsw;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

public class aggiungiRecensioneController extends AsyncTask<String, String, String> {
    public RecensioneActivity frame;
    String z = null;
    Boolean isSuccess = false;

    public aggiungiRecensioneController(RecensioneActivity view)
    {
        frame=view;
    }
    @Override
    protected void onPreExecute() {
    }

    @Override
    protected void onPostExecute(String s) {






    }

    @Override
    protected String doInBackground(String... strings) {

        if(!frame.recensione.checkRecensionePresente(frame.nickname,frame.indirizzo)){
            frame.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(frame, "Recensione Aggiunta", Toast.LENGTH_LONG).show();
                }
            });

            frame.recensione.saveRecensione(frame.id,String.valueOf(frame.commento.getText()), Integer.valueOf(String.valueOf(frame.voto.getSelectedItem())),frame.nickname,frame.indirizzo);
            z = "Success";

            Intent intent = new Intent(frame, MainActivity2.class);
            intent.putExtra("nickname", frame.nickname);
            frame.startActivity(intent);
            frame.finish();}
        else{ frame.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(frame, "Gia è stata aggiunta una recensione a questa struttura", Toast.LENGTH_LONG).show();
            }
        });}





        return z;
    }

}


