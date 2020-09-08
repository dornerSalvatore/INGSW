package com.example.progettoingsw.Connection;

import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.example.progettoingsw.MainActivity;
import com.example.progettoingsw.MainActivity2;

import net.sourceforge.jtds.jdbc.DateTime;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;

import static java.util.Calendar.DATE;
import static java.util.Calendar.getInstance;

public class ConnectionClass {
    public static String ip ="database-1.cqcdg0e6vlsg.us-east-2.rds.amazonaws.com"; // SQL Server IP Address
    public static String un= "adminSal"; // SQL Server User name
    public static String pass = "SimSal1920"; // SQL Server Password
    public static String db = "dbprogetto"; // SQL Server Database
    public static boolean controllo=true;

    public static String getLogIn(Connection con,String username,String password)
    {

        if(con == null){
           return null;
        }
        else {

            try {
                String sql = "SELECT * FROM Utente WHERE username = '" + username + "' AND passwd= '" + password+ "' ";
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);


                if (rs.next() ) {

                    String nickname = rs.getString("nickname");
                    int flag=rs.getInt("FlagBlacklist");
                    if(flag==0) {

                        return nickname;
                    }
                    else{return "Account Bloccato";}

                } else {
                    return "Check username or password";
                }
            } catch (Exception e) {

                return null;
            }
        }

    }

    public static void setLogOut(Connection con, String nickname)  {
        Date data=new Date((System.currentTimeMillis()));
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(c.getTime());



        if(con == null){

        }
        else {

            try {
                String sql = "Update Utente set TimeLogout= '" + strDate +"' where nickname= '" +nickname+ "'";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);

            } catch (Exception e) {

            }
        }

    }
    public static void setVisitatori(Connection con, String nickname) {
        Date data = new Date((System.currentTimeMillis()));
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(c.getTime());


        if (controllo) {
            if (con == null) {

            } else {

                try {
                    String sql = "Select * from ListaVisitatori where data= '" + data + "'";//+"' and  nickname= '" +nickname+ "'";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    if (rs.next()) {
                        String sql1 = "Update ListaVisitatori set nVisitatori= '" + (rs.getInt("nVisitatori") + 1) + "' where data= '" + data + "'";
                        Statement stmt1 = con.createStatement();
                        stmt.executeUpdate(sql1);

                    } else {
                        String sql1 = "INSERT INTO ListaVisitatori (data,nVisitatori) VALUES ('" + data + "','" + 1 + "')";
                        Statement stmt1 = con.createStatement();
                        stmt.executeUpdate(sql1);

                    }

                } catch (Exception e) {

                }
            }
        }
        controllo=false;

        }
        public static Connection connectionClass (String user, String password, String
        database, String server){
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            Connection connection = null;
            String connectionURL = null;
            try {
                Class.forName("net.sourceforge.jtds.jdbc.Driver");
                connectionURL = "jdbc:jtds:sqlserver://" + server + "/" + database + ";user=" + user + ";password=" + password + ";";
                connection = DriverManager.getConnection(connectionURL);
            } catch (Exception e) {
                Log.e("SQL Connection Error : ", e.getMessage());
            }

            return connection;
        }


}
