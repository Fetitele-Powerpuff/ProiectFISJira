package com.example.fis;

import java.sql.Connection;
import java.sql.DriverManager;
public class DatabaseConnection {
    public Connection dataBaseLink;

    public Connection geConnection(){

        String url="jdbc:sqlite:identifier.sqlite";

        try{
            dataBaseLink=DriverManager.getConnection(url);
        }catch (Exception e){
            e.printStackTrace();
        }
        return dataBaseLink;
    }
}
