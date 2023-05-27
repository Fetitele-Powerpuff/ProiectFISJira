package com.example.fis;

public class DeletedFilms {
    private int id;
    private String name;



    private int id_user;
    public DeletedFilms(int id, String name, String genre, int length, int id_user) {
        this.id = id;
        this.name = name;

    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }




    public int getId_user() {
        return id_user;
    }
}

