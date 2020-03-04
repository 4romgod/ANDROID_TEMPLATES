package com.template.users;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

/**
 * <p>User Entity/Object</p>
 * <p>Uses Room to create a table of the Object</p>
 */

@Entity(tableName = "USERS")
public class User {

    public static final String TABLE_NAME = "USERS";

    @PrimaryKey @NonNull @ColumnInfo(name = "USER_ID")
    private Long id;

    @ColumnInfo(name = "FIRST_NAME")
    String name;

    @ColumnInfo(name = "SURNAME")
    String surname;

    @ColumnInfo(name = "BIO")
    String bio;

    @ColumnInfo(name = "PROFILE_PICTURE")
    int proPic;


    public User(String name, String surname, String bio, int proPic) {
        this.name = name;
        this.surname = surname;
        this.bio = bio;
        this.proPic = proPic;
    }

    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public int getProPic() {
        return proPic;
    }

    public void setProPic(int proPic) {
        this.proPic = proPic;
    }


}       //end class
