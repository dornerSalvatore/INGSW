package com.example.progettoingsw;

import android.content.Intent;
import android.os.AsyncTask;

import android.widget.Toast;



import com.example.progettoingsw.Connection.ConnectionClass;


public class CheckRegistrazioneController extends AsyncTask<String, String, String> {
    public RegistrazioneActivity frame;
    String z = null;


    public CheckRegistrazioneController(RegistrazioneActivity view) {
        frame = view;
    }

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
            z = "On Internet Connection";
        } else {

            z = "Success";
            if (!frame.utente.checkUsername(String.valueOf(frame.username.getText()))) {
                frame.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(frame, "username già presente", Toast.LENGTH_LONG).show();
                        frame.username.setError("username già presente");


                    }
                });


            }
            if (!frame.utente.checkNickname(String.valueOf(frame.nickname.getText()))) {
                frame.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(frame, "nickname già presente", Toast.LENGTH_LONG).show();
                        frame.nickname.setError("nickname gia presente");

                    }
                });


            }
            if (!frame.utente.checkEmail(String.valueOf(frame.email.getText()))) {
                frame.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(frame, "email già presente", Toast.LENGTH_LONG).show();
                        frame.email.setError("email gia presente");

                    }
                });


            }

                if (frame.utente.checkEmail(String.valueOf(frame.email.getText())) && frame.utente.checkNickname(String.valueOf(frame.nickname.getText())) && frame.utente.checkUsername(String.valueOf(frame.username.getText()))) {
                    if (frame.visualizza.isChecked()) {
                        frame.utente.saveUtente(String.valueOf(frame.nome.getText()), String.valueOf(frame.cognome.getText()), String.valueOf(frame.email.getText()), String.valueOf(frame.password.getText()), String.valueOf(frame.username.getText()), String.valueOf(frame.nickname.getText()), 0);
                    } else {
                        frame.utente.saveUtente(String.valueOf(frame.nome.getText()), String.valueOf(frame.cognome.getText()), String.valueOf(frame.email.getText()), String.valueOf(frame.password.getText()), String.valueOf(frame.username.getText()), String.valueOf(frame.nickname.getText()), 1);
                    }
                    frame.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(frame, "registrazione avvenuta con successo", Toast.LENGTH_LONG).show();
                        }
                    });

                    Intent intent = new Intent(frame, MainActivity.class);
                    frame.startActivity(intent);
                    frame.finish();
                }

        }


        return z;
    }

    public Boolean controlPassword() {
        String val = frame.password.getText().toString();
        String passwordVal = "^" +
                "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                //"(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{4,}" +               //at least 4 characters
                "$";

        if (val.isEmpty()) {
            frame.password.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            frame.password.setError("Password must contain at least 1 digit,1 upper case , no white space , at least 4 characters");
            return false;
        } else {
            frame.password.setError(null);
            return true;
        }
    }

    public Boolean controlUsername() {
        String val = frame.username.getText().toString();

        if (val.isEmpty()) {
            frame.username.setError("Field cannot be empty");
            return false;
        } else {
            frame.username.setError(null);
            return true;
        }
    }

    public Boolean controlEmail() {
        String val = frame.email.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            frame.email.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            frame.email.setError("Invalid email address");
            return false;
        } else {
            frame.email.setError(null);
            return true;
        }
    }

    public Boolean controlNickname() {
        String val = frame.nickname.getText().toString();

        if (val.isEmpty()) {
            frame.nickname.setError("Field cannot be empty");
            return false;
        } else {
            frame.nickname.setError(null);
            return true;
        }
    }
}
