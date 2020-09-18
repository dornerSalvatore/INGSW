package com.example.progettoingsw.Dao;

import android.util.Log;
import com.example.progettoingsw.Connection.ConnectionClass;


import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StrutturaDaoImp implements StrutturaDaoInterface {
    Struttura struttura;
    public Struttura getStrutturaByInd(String indirizzo){
        try {
            String sql = "SELECT * FROM Struttura WHERE indirizzo = '" + indirizzo +  "' ";
            Statement stmt = ConnectionClass.con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);



            if (rs.next()) {
                struttura=new Struttura(rs.getString("nome"),rs.getString("citta"),rs.getString("provincia"),rs.getFloat("prezzo"),
                        rs.getString("tipologia"),rs.getFloat("latitudine"),rs.getFloat("longitudine"),rs.getString("linkImg"),rs.getString("indirizzo"));



            }

        } catch (Exception c) {

            Log.e("SQL Error : ", c.getMessage());
        }

        return struttura;
    }
    public String getLinkImgByInd(String indirizzo)
    {
        try {
            String sql = "SELECT LinkImg FROM Struttura WHERE indirizzo = '" + indirizzo +  "' ";

            Statement stmt = ConnectionClass.con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);



            if (rs.next()) {

                return rs.getString("LinkImg");

            }

        } catch (Exception c) {

            Log.e("SQL Error : ", c.getMessage());
        }
        return null;
    }
    public Struttura getStrutturaByNome(String nome)
    {
        struttura=null;
        try {

            String sql = "Select * from Struttura where nome ='" + nome + "'";
            Statement stmt = ConnectionClass.con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                struttura=new Struttura("nome",rs.getString("citta"),rs.getString("provincia"),rs.getFloat("prezzo"),rs.getString("tipologia"),rs.getFloat("latitudine")
                ,rs.getFloat("longitudine"),rs.getString("LinkImg"),rs.getString("indirizzo"));

            }

        } catch (Exception f) {
            Log.e("SQL Error : ", f.getMessage());
        }
        return struttura;
    }
    public ArrayList<Struttura> getListaStrutture(){
        ArrayList<Struttura> r=new ArrayList<>();
        try{
            String sql="Select * from Struttura";
            Statement stmt = ConnectionClass.con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {

                r.add( new Struttura(rs.getString("nome"),rs.getString("citta"),rs.getString("provincia"),rs.getFloat("prezzo"),rs.getString("tipologia"),rs.getFloat("latitudine")
                        ,rs.getFloat("longitudine"),rs.getString("LinkImg"),rs.getString("indirizzo")));



            }
        }
        catch (Exception f) {

            Log.e("SQL Error : ", f.getMessage());
        }
        return r;
    }
    public ArrayList<String> getListTipologia(){
        ArrayList<String> tipologia=new ArrayList<>();


        try{
            String sql="Select Distinct  Tipologia from Struttura";
            Statement stmt = ConnectionClass.con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                tipologia.add(rs.getString("tipologia"));


            }

        }
        catch (Exception f) {

            Log.e("SQL Error : ", f.getMessage());
        }
        return tipologia;
    }
    public ArrayList<Struttura> getListaStruttureByPar(String tipologia,String nome,String citta,String provincia,int prezzo1,int prezzo2)
    {
        ArrayList<Struttura> r=new ArrayList<>();
        try{
        String sql = "Select * from Struttura where Tipologia = '" + tipologia + "' AND Nome= '" + nome+ "'AND citta='" + citta+ "' AND provincia='" + provincia
                + "'AND prezzo >='" + prezzo1 + "'AND prezzo <'" + prezzo2 + "'";
            Statement stmt = ConnectionClass.con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                r.add( new Struttura(rs.getString("nome"),rs.getString("citta"),rs.getString("provincia"),rs.getFloat("prezzo"),rs.getString("tipologia"),rs.getFloat("latitudine")
                        ,rs.getFloat("longitudine"),rs.getString("LinkImg"),rs.getString("indirizzo")));

            }

        }
        catch (Exception f) {

            Log.e("SQL Error : ", f.getMessage());
        }
        return r;
    }
    public ArrayList<Struttura> getListaStruttureByPar(String tipologia,String nome,String citta,String provincia,int prezzo1)
    {
        ArrayList<Struttura> r=new ArrayList<>();
        try{
            String sql = "Select * from Struttura where Tipologia = '" + tipologia + "' AND Nome= '" + nome+
                    "'AND citta='" + citta + "' AND provincia='" + provincia + "'AND prezzo >='" + prezzo1 +  "'";;
            Statement stmt = ConnectionClass.con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                r.add( new Struttura(rs.getString("nome"),rs.getString("citta"),rs.getString("provincia"),rs.getFloat("prezzo"),rs.getString("tipologia"),rs.getFloat("latitudine")
                        ,rs.getFloat("longitudine"),rs.getString("LinkImg"),rs.getString("indirizzo")));

            }

        }
        catch (Exception f) {

            Log.e("SQL Error : ", f.getMessage());
        }
        return r;
    }
    public ArrayList<Struttura> getListaStruttureByPar(String tipologia,String citta,String provincia,int prezzo1)
    {
        ArrayList<Struttura> r=new ArrayList<>();
        try{
            String sql = "Select * from Struttura where Tipologia = '" + tipologia +
                    "'AND citta='" + citta + "' AND provincia='" + provincia + "'AND prezzo >='" + prezzo1 +  "'";;
            Statement stmt = ConnectionClass.con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                r.add( new Struttura(rs.getString("nome"),rs.getString("citta"),rs.getString("provincia"),rs.getFloat("prezzo"),rs.getString("tipologia"),rs.getFloat("latitudine")
                        ,rs.getFloat("longitudine"),rs.getString("LinkImg"),rs.getString("indirizzo")));

            }

        }
        catch (Exception f) {

            Log.e("SQL Error : ", f.getMessage());
        }
        return r;
    }
    public ArrayList<Struttura> getListaStruttureByPar(String tipologia,String citta,String provincia,int prezzo1,int prezzo2)
    {
        ArrayList<Struttura> r=new ArrayList<>();
        try{
            String sql = "Select * from Struttura where Tipologia = '" + tipologia + "'AND citta='" + citta+ "' AND provincia='" + provincia
                    + "'AND prezzo >='" + prezzo1 + "'AND prezzo <'" + prezzo2 + "'";
            Statement stmt = ConnectionClass.con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                r.add( new Struttura(rs.getString("nome"),rs.getString("citta"),rs.getString("provincia"),rs.getFloat("prezzo"),rs.getString("tipologia"),rs.getFloat("latitudine")
                        ,rs.getFloat("longitudine"),rs.getString("LinkImg"),rs.getString("indirizzo")));

            }

        }
        catch (Exception f) {

            Log.e("SQL Error : ", f.getMessage());
        }
        return r;
    }
    public ArrayList<Struttura> getListaStruttureByPar(String tipologia,String nome,String citta,String provincia)
    {
        ArrayList<Struttura> r=new ArrayList<>();
        try{
            String sql = "Select * from Struttura where Tipologia = '" + tipologia + "' AND Nome= '" + nome+ "'AND citta='" + citta+
                    "' AND provincia='" + provincia + "'";
            Statement stmt = ConnectionClass.con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                r.add( new Struttura(rs.getString("nome"),rs.getString("citta"),rs.getString("provincia"),rs.getFloat("prezzo"),rs.getString("tipologia"),rs.getFloat("latitudine")
                        ,rs.getFloat("longitudine"),rs.getString("LinkImg"),rs.getString("indirizzo")));

            }

        }
        catch (Exception f) {

            Log.e("SQL Error : ", f.getMessage());
        }
        return r;
    }
    public ArrayList<Struttura> getListaStruttureByPar(String tipologia,String citta,String provincia)
    {
        ArrayList<Struttura> r=new ArrayList<>();
        try{
            String sql = "Select * from Struttura where Tipologia = '" + tipologia + "'AND citta='" + citta+
                    "' AND provincia='" + provincia + "'";
            Statement stmt = ConnectionClass.con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next())
            {
                r.add( new Struttura(rs.getString("nome"),rs.getString("citta"),rs.getString("provincia"),rs.getFloat("prezzo"),rs.getString("tipologia"),rs.getFloat("latitudine")
                        ,rs.getFloat("longitudine"),rs.getString("LinkImg"),rs.getString("indirizzo")));

            }

        }
        catch (Exception f) {

            Log.e("SQL Error : ", f.getMessage());
        }
        return r;
    }
    public ArrayList<Struttura> getListaStruttureByLatitudineLongitudine(double latitudine1,double latitudine2,double longitudine1,double longitudine2)
    {
        ArrayList<Struttura> r=new ArrayList<>();
        try{
            String sql = "SELECT * FROM Struttura WHERE  latitudine >='"+(latitudine1-0.03)+"'AND latitudine <= '"+(latitudine2+0.03)+"'and longitudine >='"+(longitudine1-1)+"'AND longitudine <= '"+(longitudine2+1)+"'";
        Statement stmt = ConnectionClass.con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);

        while(rs.next()) {
            r.add( new Struttura(rs.getString("nome"),rs.getString("citta"),rs.getString("provincia"),rs.getFloat("prezzo"),rs.getString("tipologia"),rs.getFloat("latitudine")
                    ,rs.getFloat("longitudine"),rs.getString("LinkImg"),rs.getString("indirizzo")));


        }

    } catch (Exception e) {

        Log.e("SQL Error : ", e.getMessage());
    }
        return r;
    }

}

