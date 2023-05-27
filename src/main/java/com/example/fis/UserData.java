package com.example.fis;

public class UserData {
    private String fName;
    private String lName;
    private String email;
    private String username;
    private String cardNumber;
    private String phoneNumber;

    public UserData(String fName, String lName, String email, String username, String cardNumber, String phoneNumber) {
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.username = username;
        this.cardNumber = cardNumber;
        this.phoneNumber = phoneNumber;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCardNumber() {
        return cardNumber;
    }


    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }


}
