package com.example.progettoingsw.Dao;

import java.util.List;

public interface RecensioneDaoInterface {
    public  void saveRecensione(int id,String commento,int voto,String nickname,String indirizzo);
    public   boolean checkRecensionePresente(String nickname, String indirizzo);


}
