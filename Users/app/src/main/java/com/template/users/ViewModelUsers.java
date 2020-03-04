package com.template.users;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ViewModelUsers extends AndroidViewModel {

    private RepositoryUser repositoryUser;
    private LiveData<List<User>> listUsers;


    public ViewModelUsers(@NonNull Application application) {
        super(application);

        repositoryUser = new RepositoryUser(application);
        listUsers = repositoryUser.getListUsers();
    }

    public void insert(User user){
        repositoryUser.insert(user);
    }

    public void update(User user){
        repositoryUser.update(user);
    }

    public void delete(User user){
        repositoryUser.delete(user);
    }

    public void deleteAllUsers(){
        repositoryUser.deleteAllUsers();
    }

    public LiveData<List<User>> getListUsers(){
        return listUsers;
    }

}       //end class
