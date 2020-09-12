package com.example.progettoingsw.Dao;

import net.sourceforge.jtds.jdbc.DateTime;

public class Utente {
   private String nome;
     private String cognome;
    private String email;
    private String passwd;
     private String username;
   private String nickname;
     private  int FlagBlacklist;
    private int FlagNickname;
    private DateTime TimeLogout;

    public Utente() {
        this.nome = null;
        this.cognome = null;
        this.email = null;
        this.passwd = null;
        this.username = null;
        this.nickname = null;
        this.FlagBlacklist = 0;
        this.FlagNickname = 0;
        TimeLogout=null;

    }

    public Utente(String nome, String cognome, String email, String passwd, String username, String nickname, int flagBlacklist, int flagNickname, DateTime timeLogout) {
        this.nome = nome;
        this.cognome = cognome;
        this.email = email;
        this.passwd = passwd;
        this.username = username;
        this.nickname = nickname;
        this.FlagBlacklist = flagBlacklist;
        this.FlagNickname = flagNickname;
        this.TimeLogout = timeLogout;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getFlagBlacklist() {
        return FlagBlacklist;
    }

    public void setFlagBlacklist(int flagBlacklist) {
        FlagBlacklist = flagBlacklist;
    }

    public int getFlagNickname() {
        return FlagNickname;
    }

    public void setFlagNickname(int flagNickname) {
        FlagNickname = flagNickname;
    }

    public DateTime getTimeLogout() {
        return TimeLogout;
    }

    public void setTimeLogout(DateTime timeLogout) {
        TimeLogout = timeLogout;
    }



}
