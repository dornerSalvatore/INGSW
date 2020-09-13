package com.example.progettoingsw.Dao;

import java.util.ArrayList;
import java.util.List;

public interface RecensioneDaoInterface {
    public  void saveRecensione(int id,String commento,int voto,String nickname,String indirizzo);
    public   boolean checkRecensionePresente(String nickname, String indirizzo);
    public ArrayList<Recensione> getRecensioniByIdStelle(String indirizzo, int stelle);;
    public ArrayList<Recensione> getRecensioniById(String indirizzo);


}
