package com.example.progettoingsw;

import androidx.appcompat.app.AppCompatActivity;



import androidx.appcompat.app.AppCompatActivity;



import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.progettoingsw.Dao.UtenteDaoImp1;

public class RegistrazioneActivityT extends AppCompatActivity{
    EditText username;
    EditText password;
            EditText email;
            EditText nickname;
            EditText nome;
            EditText cognome;
            RadioButton visualizza;
            Button bottone1;
            Button bottone2;
            UtenteDaoImp1 utente;




@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrazione);

        username = (EditText) findViewById(R.id.textusr);
        password = (EditText) findViewById(R.id.textpwd);
        email = (EditText) findViewById(R.id.textemail);
        nickname = (EditText) findViewById(R.id.textnick);
        nome = (EditText) findViewById(R.id.textnome);
        cognome = (EditText) findViewById(R.id.textcognome);
        visualizza = (RadioButton) findViewById(R.id.visualizzaNomeCognome);
        bottone1 = (Button) findViewById(R.id.button1);
        bottone2 = (Button) findViewById(R.id.invia);
        utente = new UtenteDaoImp1();
        new ButtonControllerT(RegistrazioneActivityT.this);
        }
}
