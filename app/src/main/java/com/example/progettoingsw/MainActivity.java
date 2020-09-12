package com.example.progettoingsw;



import android.content.Intent;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;



import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;


import com.example.progettoingsw.Connection.ConnectionClass;
import com.example.progettoingsw.Dao.UtenteDaoImp;

import static com.example.progettoingsw.Connection.ConnectionClass.connectionClass;
import static com.example.progettoingsw.Connection.ConnectionClass.setVisitatori;


public class MainActivity extends AppCompatActivity {



    EditText username;
    EditText password;
    Button bottone1;
    Button bottone3;
    Button bottone2;
    boolean b=true;
    String nickname;


    String valore;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setVisitatori();









         username = (EditText) findViewById(R.id.editTextTextPersonName);
        username.setText(username.getText().toString());
        password = (EditText) findViewById(R.id.editTextTextPassword);
        password.setText(password.getText().toString());
        bottone1=(Button) findViewById(R.id.bottone1);




        bottone1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valore=username.getText().toString();
                if (valore.isEmpty()) {
                    Toast.makeText(MainActivity.this, "campo username vuoto", Toast.LENGTH_LONG).show();
                } else {


                    new MainActivity.checkLogin().execute("");


                }
            }
        });




        bottone3=(Button) findViewById(R.id.bottone3);
        bottone3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openPage1 = new Intent(MainActivity.this,RicercaActivity.class);
                // passo all'attivazione dell'activity Pagina.java
                startActivityForResult(openPage1, 0);
            }
        });

         bottone2=(Button) findViewById(R.id.bottone2);
        bottone2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openPage1 = new Intent(MainActivity.this,RegistrazioneActivity.class);
                // passo all'attivazione dell'activity Pagina.java
                startActivity(openPage1);

            }
        });


        };




    public class checkLogin extends AsyncTask<String, String, String> {

        String z = null;


        @Override
        protected void onPreExecute() {


        }

        @Override
        protected void onPostExecute(String s) {
            username.setText("");
            password.setText("");

        }

        @Override
        protected String doInBackground(String... strings) {


            if(ConnectionClass.con == null){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this,"Check DBMS Connection",Toast.LENGTH_LONG).show();
                    }
                });
                z = "On Internet Connection";
            }
            else {
                        UtenteDaoImp u=new UtenteDaoImp();
                         u.LogIn(String.valueOf(username.getText()),String.valueOf(password.getText()));
                        if(u.getUtente()!=null) {
                            z = "Success";
                            if (u.getUtente().getFlagBlacklist() == 0) {
                                nickname = u.getUtente().getNickname();
                                Intent intent = new Intent(MainActivity.this, MainActivity2.class);
                                intent.putExtra("nickname", nickname);
                                startActivity(intent);
                                finish();
                            } else if (u.getUtente().getFlagBlacklist() == 1) {
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(MainActivity.this, "Account Bloccato", Toast.LENGTH_LONG).show();
                                    }
                                });
                            }
                        }else {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(MainActivity.this, "Check username or password", Toast.LENGTH_LONG).show();
                                }
                            });
                        }
                        }






            return z;
        }
    }



        }
