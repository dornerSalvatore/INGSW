package com.example.progettoingsw.Dao;

import com.example.progettoingsw.Connection.ConnectionClass;

import java.sql.Connection;
import java.sql.Date;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public interface UtenteDaoInterface {
    public  void LogIn(String username, String password);
    public  void saveUtente(String nome, String cognome, String email, String passwd, String username, String nickname,  int FlagNickname);
    public  boolean checkNickname(String nickname);
    public  boolean checkUsername(String username);
    public  boolean checkEmail(String email);
    public   void   setLogOut( String nickname);



}
