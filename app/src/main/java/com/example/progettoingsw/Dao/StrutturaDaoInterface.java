package com.example.progettoingsw.Dao;

import java.util.ArrayList;

public interface StrutturaDaoInterface {

    public Struttura getStrutturaByInd(String indirizzo);
    public String getLinkImgByInd(String indirizzo);
    public Struttura getStrutturaByNome(String nome);
    public ArrayList<Struttura> getListaStrutture();
    public ArrayList<String> getListTipologia();
    public ArrayList<Struttura> getListaStruttureByPar(String tipologia,String nome,String citta,String provincia,int prezzo1,int prezzo2);
    public ArrayList<Struttura> getListaStruttureByPar(String tipologia,String nome,String citta,String provincia,int prezzo1);
    public ArrayList<Struttura> getListaStruttureByPar(String tipologia,String citta,String provincia);
    public ArrayList<Struttura> getListaStruttureByPar(String tipologia,String nome,String citta,String provincia);
    public ArrayList<Struttura> getListaStruttureByPar(String tipologia,String citta,String provincia,int prezzo1,int prezzo2);
    public ArrayList<Struttura> getListaStruttureByPar(String tipologia,String citta,String provincia,int prezzo1);



}
