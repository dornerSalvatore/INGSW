package com.example.progettoingsw.Dao;

import android.util.Log;

import com.example.progettoingsw.Connection.ConnectionClass;

import java.sql.ResultSet;
import java.sql.Statement;

public class RecensioniDaoImp {
    Recensioni recensioni;
    public   boolean checkRecensionePresente(String nickname, String indirizzo)
    {

        try {


            String sql1 = "SELECT  * FROM Recensioni where nickname= '"+nickname +"' AND Indirizzo ='"+indirizzo+"'";
            Statement stmt1 = ConnectionClass.con.createStatement();
            ResultSet rs1 = stmt1.executeQuery(sql1);
            if(rs1.next()){
                return true;


            }



        } catch (Exception c) {
            Log.e("SQL Error : ", c.getMessage());
        }
        return false;
    }
    public  void saveRecensione(int id,String commento,int voto,String nickname,String indirizzo)
    {
        try {
            String sql3 = "INSERT INTO Recensioni (Id,Commento,Stelle,Nickname,Indirizzo) VALUES ('" + id + "','" + commento + "','" + voto + "','" + nickname + "','" + indirizzo + "')";
            Statement stmt3 = ConnectionClass.con.createStatement();
            stmt3.executeUpdate(sql3);

        } catch (Exception e) {

            Log.e("SQL Error : ", e.getMessage());
        }
    }

    public Recensioni getRecensioni() {
        return recensioni;
    }

    public void setRecensioni(Recensioni recensioni) {
        this.recensioni = recensioni;
    }
}
