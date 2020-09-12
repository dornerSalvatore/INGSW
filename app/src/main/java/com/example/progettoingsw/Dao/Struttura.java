package com.example.progettoingsw.Dao;

public class Struttura {
    private String nome;
    private String citta;
    private String provincia;
    private float prezzo;
    private String tipologia;
    private float latitudine;
    private float longitudine;
    private String LinkImg;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public float getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(float prezzo) {
        this.prezzo = prezzo;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    public float getLatitudine() {
        return latitudine;
    }

    public void setLatitudine(float latitudine) {
        this.latitudine = latitudine;
    }

    public float getLongitudine() {
        return longitudine;
    }

    public void setLongitudine(float longitudine) {
        this.longitudine = longitudine;
    }

    public String getLinkImg() {
        return LinkImg;
    }

    public void setLinkImg(String linkImg) {
        LinkImg = linkImg;
    }

    public Struttura() {

    }

    public Struttura(String nome, String citta, String provincia, float prezzo, String tipologia, float latitudine, float longitudine, String linkImg) {
        this.nome = nome;
        this.citta = citta;
        this.provincia = provincia;
        this.prezzo = prezzo;
        this.tipologia = tipologia;
        this.latitudine = latitudine;
        this.longitudine = longitudine;
        LinkImg = linkImg;
    }
}
