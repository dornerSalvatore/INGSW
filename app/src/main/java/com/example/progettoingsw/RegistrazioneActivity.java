package com.example.progettoingsw;

import androidx.appcompat.app.AppCompatActivity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.lang.Object;
import	java.text.Format;
import java.text.DateFormat;
import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.progettoingsw.Connection.ConnectionClass;

import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class RegistrazioneActivity extends AppCompatActivity {

    Connection con;
    EditText username;
    EditText password;
    EditText email;
    EditText nickname;
    EditText nome;
    EditText cognome;
    RadioButton visualizza;
    int c =0;

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









        Button bottone1=(Button) findViewById(R.id.button1);
        bottone1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openPage1 = new Intent(RegistrazioneActivity.this,MainActivity.class);
                // passo all'attivazione dell'activity Pagina.java
                startActivity(openPage1);
            }
        });
        Button bottone2=(Button) findViewById(R.id.button2);
        bottone2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new RegistrazioneActivity.checkRegistrazione().execute(""); //codice per eseguire la connessione e interrogazione al DBMS
                //Intent openPage1 = new Intent(RegistrazioneActivity.this,MainActivity.class);
                // passo all'attivazione dell'activity Pagina.java
                //startActivity(openPage1);
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

        @Override
        protected String doInBackground(String... strings) {

            con = connectionClass(ConnectionClass.un.toString(), ConnectionClass.pass.toString(), ConnectionClass.db.toString(), ConnectionClass.ip.toString());
            if (con == null) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(RegistrazioneActivity.this, "Check Internet Connection", Toast.LENGTH_LONG).show();
                    }
                });
                z = "On Internet Connection";
            } else {
                try {
                    String sql = "SELECT * FROM Utente WHERE username = '" + username.getText()  + "' ";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);

                        if(rs.next()) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(RegistrazioneActivity.this, "username già presente", Toast.LENGTH_LONG).show();

                                }
                            });
                            c=1;
                            username.setText("");
                        }

                } catch (Exception e) {
                    isSuccess = false;
                    Log.e("SQL Error : ", e.getMessage());
                }
            }
            try {


                    String sql1 = "SELECT * FROM Utente WHERE email = '" + email.getText()  + "' ";
                    Statement stmt1 = con.createStatement();
                    ResultSet rs1 = stmt1.executeQuery(sql1);
                    if(rs1.next()) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(RegistrazioneActivity.this, "email già presente", Toast.LENGTH_LONG).show();

                            }
                        });
                        c=1;
                        email.setText("");
                    }




            } catch (Exception e) {
                isSuccess = false;
                Log.e("SQL Error : ", e.getMessage());
            }
            try {


                String sql1 = "SELECT * FROM Utente WHERE nickname = '" + nickname.getText()  + "' ";
                Statement stmt1 = con.createStatement();
                ResultSet rs1 = stmt1.executeQuery(sql1);
                if(rs1.next()) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(RegistrazioneActivity.this, "nickname già presente", Toast.LENGTH_LONG).show();

                        }
                    });
                    c=1;
                    nickname.setText("");
                }




            } catch (Exception e) {
                isSuccess = false;
                Log.e("SQL Error : ", e.getMessage());
            }

                try {

                    if(c==0) {
                        String sql3 = "INSERT INTO Utente (Nome,Cognome,Email,Username,Passwd,Nickname) VALUES ('" + nome.getText() + "','" + cognome.getText() + "','" + email.getText() + "','" + username.getText() + "','" + password.getText() + "','" + nickname.getText() + "')";
                        Statement stmt3 = con.createStatement();
                        stmt3.executeUpdate(sql3);
                        z = "Success";

                        Intent intent = new Intent(RegistrazioneActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();


                    }
                } catch (Exception e) {
                    isSuccess = false;
                    Log.e("SQL Error : ", e.getMessage());
                }



            return z;
        }
    }
    @SuppressLint("NewApi")
    public Connection connectionClass(String user, String password, String database, String server){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String connectionURL = null;
        try{
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connectionURL = "jdbc:jtds:sqlserver://" + server+"/" + database + ";user=" + user + ";password=" + password + ";";
            connection = DriverManager.getConnection(connectionURL);
        }catch (Exception e){
            Log.e("SQL Connection Error : ", e.getMessage());
        }

        return connection;
    }
}