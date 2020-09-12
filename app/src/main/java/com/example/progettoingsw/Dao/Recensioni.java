package com.example.progettoingsw.Dao;

public class Recensioni {
   private  int id;
    private String commento;
    private int stelle;
   private  Utente aggiuntaDa;
    private Struttura posseduta;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCommento() {
        return commento;
    }

    public void setCommento(String commento) {
        this.commento = commento;
    }

    public int getStelle() {
        return stelle;
    }

    public void setStelle(int stelle) {
        this.stelle = stelle;
    }

    public Utente getAggiuntaDa() {
        return aggiuntaDa;
    }

    public void setAggiuntaDa(Utente aggiuntaDa) {
        this.aggiuntaDa = aggiuntaDa;
    }

    public Struttura getPosseduta() {
        return posseduta;
    }

    public void setPosseduta(Struttura posseduta) {
        this.posseduta = posseduta;
    }
}
