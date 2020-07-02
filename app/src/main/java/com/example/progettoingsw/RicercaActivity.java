package com.example.progettoingsw;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import androidx.fragment.app.DialogFragment;
import java.lang.Object;
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

public class RicercaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ricerca);
         Button bottone1=(Button) findViewById(R.id.button2);
         bottone1.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent intent = new Intent(RicercaActivity.this,MapsActivity.class);
                 startActivity(intent);

             }
         });
        Button bottone2=(Button) findViewById(R.id.button1);
                 bottone2.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View view) {
                         Intent intent2 = new Intent();
                         // passo all'attivazione dell'activity Pagina.java
                         setResult(RESULT_OK, intent2);
                         finish();
                     }
                 });


    };


}