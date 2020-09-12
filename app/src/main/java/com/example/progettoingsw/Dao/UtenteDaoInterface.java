package com.example.progettoingsw.Dao;

import java.sql.Connection;

public interface UtenteDaoInterface {
    public  void LogIn(String username, String password);
    public  void saveUtente(String nome, String cognome, String email, String passwd, String username, String nickname,  int FlagNickname);
    public  boolean checkNickname(String nickname);
    public  boolean checkUsername(String username);
    public  boolean checkEmail(String email);
    public  void setLogOut( String nickname);



}
