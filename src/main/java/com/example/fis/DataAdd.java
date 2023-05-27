package com.example.fis;

public class DataAdd {
    private int id_film;
    private int start;
    private int stop;
    private String type_room;
    private String date;

    private String name;

    private String genre;
    private String length;


    public DataAdd(int id_film, int start, int stop, String type_room, String date,String name,String genre, String length) {
        this.id_film = id_film;
        this.start = start;
        this.stop = stop;
        this.type_room = type_room;
        this.date = date;
        this.genre=genre;
        this.name=name;
        this.length=length;
    }

    public int getId_film() {
        return id_film;
    }

    public int getStart() {
        return start;
    }

    public int getStop() {
        return stop;
    }

    public String getType_room() {
        return type_room;
    }

    public String getDate() {
        return date;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public String getLength() {
        return length;
    }
}
