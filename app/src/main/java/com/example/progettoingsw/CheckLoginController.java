package com.example.progettoingsw;

import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.progettoingsw.Connection.ConnectionClass;
import com.example.progettoingsw.Dao.UtenteDaoImp1;

    public class CheckLoginController extends AsyncTask<String, String, String> {
        public MainActivity frame;

        String z = null;
        public CheckLoginController(MainActivity view)
        {
            frame=view;
        }


        @Override
        protected void onPreExecute() {


        }

        @Override
        protected void onPostExecute(String s) {
            frame.username.setText("");
            frame.password.setText("");

        }

        @Override
        protected String doInBackground(String... strings) {


            if(ConnectionClass.con == null){
                frame.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(frame,"Check DBMS Connection",Toast.LENGTH_LONG).show();
                    }
                });
                z = "On Internet Connection";
            }
            else {
                UtenteDaoImp1 u=new UtenteDaoImp1();
                u.LogIn(String.valueOf(frame.username.getText()),String.valueOf(frame.password.getText()));
                if(u.getUtente()!=null) {
                    z = "Success";
                    if (u.getUtente().getFlagBlacklist() == 0) {
                        frame.nickname = u.getUtente().getNickname();
                        Intent intent = new Intent(frame, MainActivity2.class);
                        intent.putExtra("nickname", frame.nickname);
                        frame.startActivity(intent);
                        frame.finish();
                    } else if (u.getUtente().getFlagBlacklist() == 1) {
                        frame.runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Toast.makeText(frame, "Account Bloccato", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                }else {
                    frame.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(frame, "Check username or password", Toast.LENGTH_LONG).show();
                        }
                    });
                }
            }






            return z;
        }
    }

