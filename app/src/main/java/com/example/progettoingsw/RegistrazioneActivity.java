package com.example.progettoingsw;

import androidx.appcompat.app.AppCompatActivity;
import java.sql.Connection;
import java.sql.Date;
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
    private int mYear,mMonth,mDay;
    Statement stmt3 ;

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
        final Button pickDate = (Button) findViewById(R.id.pick_date);
        final TextView textView= (TextView) findViewById(R.id.date);;


        final Calendar myCalendar = Calendar.getInstance();
        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                // myCalendar.add(Calendar.DATE, 0);
                String myFormat = "yyyy-MM-dd"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
                textView.setText(sdf.format(myCalendar.getTime()));
            }


        };

        pickDate.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                // Launch Date Picker Dialog
                DatePickerDialog dpd = new DatePickerDialog(RegistrazioneActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // Display Selected date in textbox

                                if (year < mYear)
                                    view.updateDate(mYear,mMonth,mDay);

                                if (monthOfYear < mMonth && year == mYear)
                                    view.updateDate(mYear,mMonth,mDay);

                                if (dayOfMonth < mDay && year == mYear && monthOfYear == mMonth)
                                    view.updateDate(mYear,mMonth,mDay);

                                textView.setText(dayOfMonth + "-"
                                        + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                dpd.getDatePicker().setMinDate(01/01/1960);
                dpd.show();

            }
        });
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
                //new RegistrazioneActivity.checkLogin().execute(""); codice per eseguire la connessione e interrogazione al DBMS
                Intent openPage1 = new Intent(RegistrazioneActivity.this,MainActivity.class);
                // passo all'attivazione dell'activity Pagina.java
                startActivity(openPage1);
            }
        });
    }
    public class checkLogin extends AsyncTask<String, String, String> {

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
            con =connectionClass(ConnectionClass.un.toString(),ConnectionClass.pass.toString(),ConnectionClass.db.toString(),ConnectionClass.ip.toString());
            if(con == null){
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(RegistrazioneActivity.this,"Check Internet Connection",Toast.LENGTH_LONG).show();
                    }
                });
                z = "On Internet Connection";
            }
            else {
                try {
                    String sql = "SELECT * FROM Utente WHERE username = '" + username.getText()  + "' ";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    String sql1 = "SELECT * FROM Utente WHERE email = '" + email.getText()  + "' ";
                    Statement stmt1 = con.createStatement();
                    ResultSet rs1 = stmt.executeQuery(sql);
                    String sql2 = "SELECT * FROM Utente WHERE nickname = '" + nickname.getText()  + "' ";
                    Statement stmt2 = con.createStatement();
                    ResultSet rs2 = stmt.executeQuery(sql);

                    if (rs.next() || rs1.next() || rs2.next()) {
                        if(rs.next()) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(RegistrazioneActivity.this, "username già presente", Toast.LENGTH_LONG).show();

                                }
                            });
                            username.setText("");
                        }
                        if(rs1.next()) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {

                                    Toast.makeText(RegistrazioneActivity.this, "email già presente", Toast.LENGTH_LONG).show();

                                }
                            });
                            email.setText("");

                        }
                        if(rs2.next()) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(RegistrazioneActivity.this, "nickname già presente", Toast.LENGTH_LONG).show();

                                }
                            });
                            nickname.setText("");
                        }
                    } else {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String sql = "INSERT INTO register (username,email,password,nickname,nome,cognome) VALUES ('"+username.getText()+"','"+email.getText()+"','"+password.getText()+"','"+nickname.getText()+"','"+nome.getText()+"','"+cognome.getText()+"','"+"')";
                                try {
                                    stmt3 = con.createStatement();
                                    stmt3.executeUpdate(sql);
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }


                                Toast.makeText(RegistrazioneActivity.this, "Registrazione avvenuta con successo", Toast.LENGTH_LONG).show();
                            }
                        });
                        z = "Success";

                        Intent intent = new Intent(RegistrazioneActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();


                    }
                } catch (Exception e) {
                    isSuccess = false;
                    Log.e("SQL Error : ", e.getMessage());
                }
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