package com.example.progettoingsw.Connection;

import android.os.StrictMode;
import android.util.Log;

import com.example.progettoingsw.Dao.Utente;

import net.sourceforge.jtds.jdbc.DateTime;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static java.util.Calendar.getInstance;

public class ConnectionClass {
    public static String ip = "database-1.cqcdg0e6vlsg.us-east-2.rds.amazonaws.com"; // SQL Server IP Address
    public static String un = "adminSal"; // SQL Server User name
    public static String pass = "SimSal1920"; // SQL Server Password
    public static String db = "dbprogetto"; // SQL Server Database
    public static boolean controllo = true;
    public static Connection con=connectionClass(un, pass, db,ip);




    public static void setVisitatori() {
        Date data = new Date((System.currentTimeMillis()));
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String strDate = sdf.format(c.getTime());


        if (controllo) {
            if (con == null) {

            } else {

                try {
                    String sql = "Select * from ListaVisitatori where DataVisita= '" + data + "'";
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery(sql);
                    if (rs.next()) {
                        String sql1 = "Update ListaVisitatori set nVisitatori= '" + (rs.getInt("nVisitatori") + 1) + "' where DataVisita= '" + data + "'";
                        Statement stmt1 = con.createStatement();
                        stmt.executeUpdate(sql1);

                    } else {
                        String sql1 = "INSERT INTO ListaVisitatori (DataVisita,nVisitatori) VALUES ('" + data + "','" + 1 + "')";
                        Statement stmt1 = con.createStatement();
                        stmt.executeUpdate(sql1);

                    }

                } catch (Exception e) {

                }
            }
        }
        controllo = false;

    }

    public static Connection connectionClass(String user, String password, String
            database, String server) {
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






    public static int getTopRecensione()
    {
        int id=0;
        try {


            String sql1 = "SELECT TOP 1 * FROM Recensioni ORDER BY ID DESC";
            Statement stmt1 = con.createStatement();
            ResultSet rs1 = stmt1.executeQuery(sql1);
            if(rs1.next()){
                id=rs1.getInt("id");


            }
            id=id+1;


        } catch (Exception c) {

            Log.e("SQL Error : ", c.getMessage());
        }
        return id;

    }
    public  static boolean checkRecensionePresente(String nickname, String indirizzo)
    {
        try {


            String sql1 = "SELECT  * FROM Recensioni where nickname= '"+nickname +"' AND Indirizzo ='"+indirizzo+"'";
            Statement stmt1 = con.createStatement();
            ResultSet rs1 = stmt1.executeQuery(sql1);
            if(rs1.next()){
                return true;


            }



        } catch (Exception c) {
            Log.e("SQL Error : ", c.getMessage());
        }
        return false;
    }
    public static void saveRecensione(int id,String commento,int voto,String nickname,String indirizzo)
    {
        try {
            String sql3 = "INSERT INTO Recensioni (Id,Commento,Stelle,Nickname,Indirizzo) VALUES ('" + id + "','" + commento + "','" + voto + "','" + nickname + "','" + indirizzo + "')";
            Statement stmt3 = con.createStatement();
            stmt3.executeUpdate(sql3);

    } catch (Exception e) {

        Log.e("SQL Error : ", e.getMessage());
    }
    }
}




