package com.template.recyclerview;

public class User {

    String firstName, surname, statusMessage;

    int image;

    public User(String firstName, String surname, String statusMessage, int image) {
        this.firstName = firstName;
        this.surname = surname;
        this.statusMessage = statusMessage;
        this.image = image;
    }

    public User() {
        this.firstName = "";
        this.surname = "";
        this.statusMessage = "";
        this.image = 0;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getStatusMessage() {
        return statusMessage;
    }

    public void setStatusMessage(String statusMessage) {
        this.statusMessage = statusMessage;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }


}
