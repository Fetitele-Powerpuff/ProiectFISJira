package com.example.fis;

public class DataClass {
    private String title;
    private Integer startT;
    private Integer finish;
    private String dateT;
    private String roomT;

    private int id_reservation;

    private int id_user;


    public DataClass(String title, Integer startT, Integer finish, String dateT, String roomT,int id_user, int id_reservation) {
        this.title = title;
        this.startT = startT;
        this.finish = finish;
        this.dateT = dateT;
        this.roomT = roomT;
        this.id_user=id_user;
        this.id_reservation=id_reservation;
    }

    public String getTitle() {
        return title;
    }

    public Integer getStartT() {
        return startT;
    }

    public Integer getFinish() {
        return finish;
    }

    public String getDateT() {
        return dateT;
    }

    public String getRoomT() {
        return roomT;
    }



    public int getId_user() {
        return id_user;
    }

    public int getId_reservation() {
        return id_reservation;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setStartT(Integer startT) {
        this.startT = startT;
    }

    public void setFinish(Integer finish) {
        this.finish = finish;
    }

    public void setDateT(String dateT) {
        this.dateT = dateT;
    }

    public void setRoomT(String roomT) {
        this.roomT = roomT;
    }

    public void setId_reservation(int id_reservation) {
        this.id_reservation = id_reservation;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }
}
