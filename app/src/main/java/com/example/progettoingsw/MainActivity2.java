package com.example.progettoingsw;



import android.app.Activity;
import android.os.Bundle;

import android.widget.Button;
import android.widget.TextView;

import com.example.progettoingsw.Connection.ConnectionClass;
import com.example.progettoingsw.Dao.UtenteDaoImp;

import java.sql.Connection;

import static com.example.progettoingsw.Connection.ConnectionClass.connectionClass;

public class MainActivity2 extends Activity {

    String nickname;
    TextView nick;
    Connection con;
    UtenteDaoImp utente;
    Button bottone1;
    Button bottone2;
    Button bottone3;
    ButtonController c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
         bottone1=(Button) findViewById(R.id.bottone1);
         bottone2 =(Button) findViewById(R.id.bottone2);
         bottone3=(Button) findViewById(R.id.bottone3);
        nick=(TextView) findViewById(R.id.nickname);
        con =connectionClass(ConnectionClass.un.toString(),ConnectionClass.pass.toString(),ConnectionClass.db.toString(),ConnectionClass.ip.toString());
        Bundle e= getIntent().getExtras();
        utente=new UtenteDaoImp();
        if(e!= null)
        {
            nickname=e.getString("nickname");
        }
        nick.setText("Benvenuto"+ '\n'+nickname);
        c=new ButtonController(MainActivity2.this);

    }
    public void onBackPressed() {
        c.onBackPressed(MainActivity2.this);
    }

    @Override
    public void onDestroy() {
        // RUN SUPER | REGISTER ACTIVITY AS NULL IN APP CLASS
        if(nickname!=null)
            utente.setLogOut(nickname);
        super.onDestroy();

    }
}