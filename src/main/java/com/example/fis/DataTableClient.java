package com.example.fis;

public class DataTableClient {

    private String title;
    private Integer startT;
    private Integer finish;
    private String dateT;
    private String roomT;

    private int id_reservation;

    public DataTableClient(String title, Integer startT, Integer finish, String dateT, String roomT) {
        this.title = title;
        this.startT = startT;
        this.finish = finish;
        this.dateT = dateT;
        this.roomT = roomT;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getStartT() {
        return startT;
    }

    public void setStartT(Integer startT) {
        this.startT = startT;
    }

    public Integer getFinish() {
        return finish;
    }

    public void setFinish(Integer finish) {
        this.finish = finish;
    }

    public String getDateT() {
        return dateT;
    }

    public void setDateT(String dateT) {
        this.dateT = dateT;
    }

    public String getRoomT() {
        return roomT;
    }

    public void setRoomT(String roomT) {
        this.roomT = roomT;
    }
}
