package com.example.progettoingsw.Dao;

import android.util.Log;

import com.example.progettoingsw.Connection.ConnectionClass;

import net.sourceforge.jtds.jdbc.DateTime;



import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;




public class UtenteDaoImp implements UtenteDaoInterface{
    private Utente utente;

    public   void setLogOut( String nickname) {
        Date data = new Date((System.currentTimeMillis()));
        Calendar c = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String strDate = sdf.format(c.getTime());


        if (ConnectionClass.con == null) {

        } else {

            try {
                String sql = "Update Utente set TimeLogout= '" + strDate + "' where nickname= '" + nickname + "'";
                Statement stmt = ConnectionClass.con.createStatement();
                stmt.executeUpdate(sql);

            } catch (Exception e) {

            }
        }

    }
    public  void saveUtente(String nome, String cognome, String email, String passwd, String username, String nickname,  int FlagNickname) {


        try {

           String  sql3 = "INSERT INTO Utente (Nome,Cognome,Email,Username,Passwd,Nickname,FlagBlacklist,FlagNickname) VALUES ('" + nome + "','" + cognome + "','" + email + "','" + username + "','" + passwd + "','" + nickname + "','" + 0  + "','"+ FlagNickname + "')";
            Statement stmt3 = ConnectionClass.con.createStatement();
            stmt3.executeUpdate(sql3);


        } catch (Exception e) {

            Log.e("SQL Error : ", e.getMessage());
        }
    }
    public  boolean checkNickname(String nickname) {


        try {


            String sql1 = "SELECT * FROM Utente WHERE nickname = '" + nickname + "' ";
            Statement stmt1 = ConnectionClass.con.createStatement();
            ResultSet rs1 = stmt1.executeQuery(sql1);
            if (rs1.next()) {
                return false;
            }



        } catch (Exception e) {
            Log.e("SQL Error : ", e.getMessage());
        }
        return true;
    }

    public  boolean checkUsername(String username) {

        try {


            String sql1 = "SELECT * FROM Utente WHERE username = '" + username + "' ";
            Statement stmt1 = ConnectionClass.con.createStatement();
            ResultSet rs1 = stmt1.executeQuery(sql1);
            if (rs1.next()) {
                return false;
            }


        } catch (Exception e) {
            Log.e("SQL Error : ", e.getMessage());
        }

        return true;
    }
    public  boolean checkEmail(String email) {


        try {


            String sql1 = "SELECT * FROM Utente WHERE email = '" + email + "' ";
            Statement stmt1 = ConnectionClass.con.createStatement();
            ResultSet rs1 = stmt1.executeQuery(sql1);
            if (rs1.next()) {
                return false;
            }


        } catch (Exception e) {
            Log.e("SQL Error : ", e.getMessage());
        }

        return true;
    }
    public  void LogIn(String username, String password) {


        try {
            String sql = "SELECT * FROM Utente WHERE username = '" + username + "' AND passwd= '" + password + "' ";
            Statement stmt = ConnectionClass.con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);


            if (rs.next()) {

                utente = new Utente();
                utente.setNickname(rs.getString("nickname"));
                utente.setFlagBlacklist(rs.getInt("FlagBlacklist"));
                utente.setCognome(rs.getString("cognome"));
                utente.setEmail(rs.getString("email"));
                utente.setFlagNickname(rs.getInt("FlagNickname"));
                utente.setNome(rs.getString("nome"));
                utente.setUsername(rs.getString("username"));
                utente.setTimeLogout((DateTime) rs.getObject("TimeLogout"));
                utente.setPasswd(rs.getString("passwd"));


            } else utente = null;
        } catch (Exception e) {

            Log.e("SQL Error : ", e.getMessage());
        }
    }

    public Utente getUtente() {
        return utente;
    }
    public   Utente getUtenteByNickname(String nickname) {
        utente=null;
        try {
        String sql2 = "SELECT * FROM Utente WHERE nickname = '" + nickname +  "' ";
        Statement stmt2 = ConnectionClass.con.createStatement();
        ResultSet  rs2 = stmt2.executeQuery(sql2);
        if(rs2.next()) {
            utente= new Utente(rs2.getString("nome"),rs2.getString("cognome"),rs2.getString("email"),rs2.getString("passwd"),rs2.getString("username"),
                    rs2.getString("nickname"),rs2.getInt("FlagBlacklist"),rs2.getInt("FlagNickname"));
            DateTime d= (DateTime)(rs2.getObject("TimeLogout"));
            utente.setTimeLogout(d);
        }

      }  catch (Exception c) {

        Log.e("SQL Error : ", c.getMessage());
        }

        return utente;
    }
    public void  setUtente(Utente u){
        utente=u;
    }
}
