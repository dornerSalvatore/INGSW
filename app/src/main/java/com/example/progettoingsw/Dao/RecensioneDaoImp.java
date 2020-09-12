package com.example.progettoingsw.Dao;

import android.util.Log;

import com.example.progettoingsw.Connection.ConnectionClass;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class RecensioneDaoImp {
    Recensione recensione;
    UtenteDaoImp utente;
    StrutturaDaoImp struttura;
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

    public Recensione getRecensione() {
        return recensione;
    }

    public ArrayList<Recensione> getRecensioniById(String indirizzo) {
        ArrayList<Recensione> r=new ArrayList<>();

        try {
            String sql = "SELECT * FROM Recensioni WHERE indirizzo = '" + indirizzo + "' ";
            Statement stmt = ConnectionClass.con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                utente=new UtenteDaoImp();
                struttura=new StrutturaDaoImp();
                recensione=new Recensione(rs.getInt("id"),rs.getString("commento"),Integer.parseInt(rs.getString("stelle")),utente.getUtenteByNickname(rs.getString("nickname")),
                        struttura.getStrutturaByInd(rs.getString("indirizzo")));
                r.add(recensione);
            }
        } catch (Exception c) {

            Log.e("SQL Error : ", c.getMessage());
        }
        return r;
    }
    public ArrayList<Recensione> getRecensioniByIdStelle(String indirizzo,int stelle) {
        ArrayList<Recensione> r=new ArrayList<>();

        try {
            String sql = "SELECT * FROM Recensioni WHERE indirizzo = '" + indirizzo +"' AND Stelle= '" + stelle + "' ";
            Statement stmt = ConnectionClass.con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                utente=new UtenteDaoImp();
                struttura=new StrutturaDaoImp();
                recensione=new Recensione(rs.getInt("id"),rs.getString("commento"),Integer.parseInt(rs.getString("stelle")),utente.getUtenteByNickname(rs.getString("nickname")),
                        struttura.getStrutturaByInd(rs.getString("indirizzo")));
                r.add(recensione);
            }
        } catch (Exception c) {

            Log.e("SQL Error : ", c.getMessage());
        }
        return r;
    }

    public void setRecensione(Recensione recensione) {
        this.recensione = recensione;
    }
}
