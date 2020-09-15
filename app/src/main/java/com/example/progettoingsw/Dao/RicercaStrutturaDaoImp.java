package com.example.progettoingsw.Dao;

import android.util.Log;

import com.example.progettoingsw.Connection.ConnectionClass;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class RicercaStrutturaDaoImp implements RicercaStrutturaDaoInterface {
    RicercaStruttura s;



    public void ricercaStruttura(String nickname, String indirizzo) {
        if (nickname != null) {
            s = new RicercaStruttura();
            UtenteDaoImp u = new UtenteDaoImp();
            StrutturaDaoImp p = new StrutturaDaoImp();
            s.setUtente(u.getUtenteByNickname(nickname));
            s.setStruttura(p.getStrutturaByInd(indirizzo));
           Date data = new Date((System.currentTimeMillis()));
            try {
                String sql = "Select * from RicercaStruttura where nickname= '" + nickname + "' AND indirizzo= '" + indirizzo + "'";
                Statement stmt = ConnectionClass.con.createStatement();
                ResultSet rs = stmt.executeQuery(sql);
                if (rs.next()) {
                    String sql1 = "Update RicercaStruttura set DataRicerca= '" + data + "' where nickname= '" + nickname + "' AND indirizzo= '" + indirizzo + "'";
                    Statement stmt1 = ConnectionClass.con.createStatement();
                    stmt1.executeUpdate(sql1);

                } else {


                    String sql3 = "INSERT RicercaStruttura (DataRicerca,nickname,indirizzo) VALUES ('" + data + "','" + nickname + "','" + indirizzo + "')";
                    Statement stmt3 = ConnectionClass.con.createStatement();
                    stmt3.executeUpdate(sql3);
                }


            } catch (Exception e) {

                Log.e("SQL Error : ", e.getMessage());
            }


        }
    }

}
