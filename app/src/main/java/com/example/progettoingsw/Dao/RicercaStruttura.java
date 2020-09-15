package com.example.progettoingsw.Dao;


import java.sql.Date;

public class RicercaStruttura {
    private Date data;
    private Utente utente;
    private Struttura  struttura;

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Utente getUtente() {
        return utente;
    }

    public void setUtente(Utente utente) {
        this.utente = utente;
    }

    public Struttura getStruttura() {
        return struttura;
    }

    public void setStruttura(Struttura struttura) {
        this.struttura = struttura;
    }
}
