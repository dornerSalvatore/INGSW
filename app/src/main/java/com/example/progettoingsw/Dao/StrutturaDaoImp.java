package com.example.progettoingsw.Dao;

import android.util.Log;

import com.bumptech.glide.Glide;
import com.example.progettoingsw.Connection.ConnectionClass;

import java.sql.ResultSet;
import java.sql.Statement;

public class StrutturaDaoImp {
    Struttura struttura;
    public Struttura getStrutturaByInd(String indirizzo){
        try {
            String sql = "SELECT LinkImg FROM Struttura WHERE indirizzo = '" + indirizzo +  "' ";
            Statement stmt = ConnectionClass.con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);



            if (rs.next()) {
                struttura=new Struttura(rs.getString("nome"),rs.getString("citta"),rs.getString("provincia"),rs.getFloat("prezzo"),
                        rs.getString("tipologia"),rs.getFloat("latitudine"),rs.getFloat("longitudine"),rs.getString("linkImg"));



            }

        } catch (Exception c) {

            Log.e("SQL Error : ", c.getMessage());
        }

        return struttura;
    }
}
