package com.example.progettoingsw.Dao;

import android.util.Log;

import com.example.progettoingsw.Connection.ConnectionClass;

import java.sql.ResultSet;
import java.sql.Statement;

public interface RecensioniDaoInterface {
    public  void saveRecensione(int id,String commento,int voto,String nickname,String indirizzo);
    public   boolean checkRecensionePresente(String nickname, String indirizzo);


}
