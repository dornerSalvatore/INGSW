package com.example.progettoingsw.Connection;

import android.content.Intent;
import android.os.StrictMode;
import android.util.Log;
import android.widget.Toast;

import com.example.progettoingsw.MainActivity;
import com.example.progettoingsw.MainActivity2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class ConnectionClass {
    public static String ip ="database-1.cqcdg0e6vlsg.us-east-2.rds.amazonaws.com"; // SQL Server IP Address
    public static String un= "adminSal"; // SQL Server User name
    public static String pass = "SimSal1920"; // SQL Server Password
    public static String db = "dbprogetto"; // SQL Server Database

    public static String getLogIn(Connection con,String username,String password)
    {
        con =connectionClass(ConnectionClass.un.toString(),ConnectionClass.pass.toString(),ConnectionClass.db.toString(),ConnectionClass.ip.toString());
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
    public static void getLogOut(Connection con,String nickname)
    {
        String data=null;
        con =connectionClass(ConnectionClass.un.toString(),ConnectionClass.pass.toString(),ConnectionClass.db.toString(),ConnectionClass.ip.toString());
        if(con == null){

        }
        else {

            try {
                String sql = "Update Utente set TimeLogout= '" + data +"' where nickname= '" +nickname+ "'";
                Statement stmt = con.createStatement();
                stmt.executeUpdate(sql);

            } catch (Exception e) {

            }
        }

    }
    public  static Connection connectionClass(String user, String password, String database, String server){
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
