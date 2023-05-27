package com.example.fis;


public class MovieData {
    private String name;
    private String genre;
    private Integer length;
    private String image;
    private Integer start;
    private Integer stop;
    private String type_room;
    private String date;

    public MovieData(String name, String genre, Integer length, String image, Integer start, Integer stop, String type_room, String date) {
        this.name = name;
        this.genre = genre;
        this.length = length;
        this.image = image;
        this.start = start;
        this.stop = stop;
        this.type_room = type_room;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public String getGenre() {
        return genre;
    }

    public Integer getLength() {
        return length;
    }

    public String getImage() {
        return image;
    }

    public Integer getStart() {
        return start;
    }

    public Integer getStop() {
        return stop;
    }

    public String getType_room() {
        return type_room;
    }

    public String getDate() {
        return date;
    }
}
