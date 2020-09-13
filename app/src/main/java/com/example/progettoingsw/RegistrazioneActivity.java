package com.example.progettoingsw;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.progettoingsw.Connection.ConnectionClass;
import com.example.progettoingsw.Dao.UtenteDaoImp;


public class RegistrazioneActivity extends AppCompatActivity {


    EditText username;
    EditText password;
    EditText email;
    EditText nickname;
    EditText nome;
    EditText cognome;
    RadioButton visualizza;
    int c =0;
    UtenteDaoImp utente;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione);
        username=(EditText) findViewById(R.id.textusr);
        password=(EditText) findViewById(R.id.textpwd);
        email=(EditText) findViewById(R.id.textemail);
        nickname=(EditText) findViewById(R.id.textnick);
        nome=(EditText) findViewById(R.id.textnome);
        cognome=(EditText) findViewById(R.id.textcognome);
        visualizza=(RadioButton) findViewById(R.id.visualizzaNomeCognome);
       utente=new UtenteDaoImp();








        Button bottone1=(Button) findViewById(R.id.button1);
        bottone1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openPage1 = new Intent(RegistrazioneActivity.this,MainActivity.class);
                // passo all'attivazione dell'activity Pagina.java
                startActivity(openPage1);
            }
        });
        Button bottone2=(Button) findViewById(R.id.invia);
        bottone2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!controlEmail() | !controlNickname() | !controlPassword() | !controlUsername()) {
                    return;
                } else {
                    new RegistrazioneActivity.checkRegistrazione().execute(""); //codice per eseguire la connessione e interrogazione al DBMS
                }
            }
        });
    }
    public class checkRegistrazione extends AsyncTask<String, String, String> {
        String z = null;
        Boolean isSuccess = false;


        @Override
        protected void onPreExecute() {

        }

        @Override
        protected void onPostExecute(String s) {





        }

        @RequiresApi(api = Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1)
        @Override
        protected String doInBackground(String... strings) {

            if (ConnectionClass.con == null) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(RegistrazioneActivity.this, "Check DBMS Connection", Toast.LENGTH_LONG).show();
                    }
                });
                z = "On Internet Connection";
            } else {
                z = "Success";
              if (! utente.checkUsername(String.valueOf(username.getText()))) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RegistrazioneActivity.this, "username già presente", Toast.LENGTH_LONG).show();
                            c=1;

                            username.setText("");
                        }
                    });


                }
               if (!utente.checkNickname(String.valueOf(nickname.getText()))) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RegistrazioneActivity.this, "nickname già presente", Toast.LENGTH_LONG).show();
                            c=1;
                            nickname.setText("");
                        }
                    });



                }
                if (!utente.checkEmail(String.valueOf(email.getText()))) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RegistrazioneActivity.this, "email già presente", Toast.LENGTH_LONG).show();
                            c=1;
                            email.setText("");
                        }
                    });


                }
                if(c==0) {
                    if (utente.checkEmail(String.valueOf(email.getText())) && utente.checkNickname(String.valueOf(nickname.getText())) && utente.checkUsername(String.valueOf(username.getText()))) {
                        if (visualizza.isChecked()) {
                            utente.saveUtente(String.valueOf(nome.getText()), String.valueOf(cognome.getText()), String.valueOf(email.getText()), String.valueOf(password.getText()), String.valueOf(username.getText()), String.valueOf(nickname.getText()), 0);
                        } else {
                            utente.saveUtente(String.valueOf(nome.getText()), String.valueOf(cognome.getText()), String.valueOf(email.getText()), String.valueOf(password.getText()), String.valueOf(username.getText()), String.valueOf(nickname.getText()), 1);
                        }
                        Intent intent = new Intent(RegistrazioneActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }





            return z;
        }
    }


    private Boolean controlPassword() {
        String val = password.getText().toString();
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
            password.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(passwordVal)) {
            password.setError("Password must contain at least 1 digit,1 upper case , no white space , at least 4 characters");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }

    private Boolean controlUsername() {
        String val = username.getText().toString();

        if (val.isEmpty()) {
            username.setError("Field cannot be empty");
            return false;
        }
        else {
            username.setError(null);
            return true;
        }
    }

    private Boolean controlEmail() {
        String val = email.getText().toString();
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (val.isEmpty()) {
            email.setError("Field cannot be empty");
            return false;
        } else if (!val.matches(emailPattern)) {
            email.setError("Invalid email address");
            return false;
        } else {
            email.setError(null);
            return true;
        }
    }

    private Boolean controlNickname() {
        String val = nickname.getText().toString();

        if (val.isEmpty()) {
            nickname.setError("Field cannot be empty");
            return false;
        }
        else {
            nickname.setError(null);
            return true;
        }
    }
}