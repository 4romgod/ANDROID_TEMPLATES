package com.template.users;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

/**
 * <p>Data Access Object<<p/>
 * <p>interface with abstract methods for data access</p>
 *
 */

@Dao
public interface DaoUser {

    @Insert
    void insert(User user);


    /**
     * Method to return a list of all the users
     * @return LiveData List of all Users
     */
    @Query("SELECT * FROM " + User.TABLE_NAME)
    LiveData<List<User>> getUsers();


    /**
     * Method to return a user specified by USER_ID
     * @param id    The ID of the USER
     * @return      Returns a USER object
     */
    @Query("SELECT * FROM USERS WHERE USER_ID = :id")
    User getUserById(Long id);


    @Update
    void update(User user);


    /**
     * Takes a user object and deletes it from the database
     * @param user Takes a USER Object
     */
    @Delete
    void delete(User user);


    /**
     * Delets all the users from the database
     */
    @Query("DELETE FROM " + User.TABLE_NAME)
    void deleteAllUsers();



}       //end class
