package com.template.users;

import android.app.Application;
import android.os.AsyncTask;
import androidx.lifecycle.LiveData;
import java.util.List;


/**
 * Abstraction layer that gets data rom sources and sending it to viewModel
 */
public class RepositoryUser {

    private DaoUser daoUser;
    private LiveData<List<User>> listUsers;


    public RepositoryUser(Application application) {
        DatabaseUsers databaseUsers = DatabaseUsers.getDatabaseInstance(application);

        daoUser = databaseUsers.getDaoUser();
        listUsers = daoUser.getUsers();
    }


    /**
     * Methods that insert data to the database in the background using Async
     * @param user
     */
    public void insert(User user){
        new InsertUserAsyncTask(daoUser).execute(user);
    }

    /**
     * Methods that updates data in the database in the background using Async
     * @param user
     */
    public void update(User user){
        new UpdateUserAsyncTask(daoUser).execute(user);
    }

    /**
     * Methods that deletes a user from the database in the background using Async
     * @param user
     */
    public void delete(User user){
        new DeleteUserAsyncTask(daoUser).execute(user);
    }

    /**
     * Methods that deletes all data from the database in the background using Async
     *
     */
    public void deleteAllUsers(){
        new DeleteAllUserAsyncTask(daoUser).execute();
    }

    /**
     * Methods that gets all data from the database in the background using Async
     */
    public LiveData<List<User>> getListUsers(){
        return listUsers;
    }



    private static class InsertUserAsyncTask extends AsyncTask<User, Void, Void> {

        private DaoUser daoUser;

        public InsertUserAsyncTask(DaoUser daoUser) {
            this.daoUser = daoUser;
        }

        @Override
        protected Void doInBackground(User... users) {
            daoUser.insert(users[0]);
            return null;
        }

    }       //end innerClass


    private static class UpdateUserAsyncTask extends AsyncTask<User, Void, Void> {

        private DaoUser daoUser;

        public UpdateUserAsyncTask(DaoUser daoUser) {
            this.daoUser = daoUser;
        }

        @Override
        protected Void doInBackground(User... users) {
            daoUser.update(users[0]);
            return null;
        }

    }       //end innerClass

    private static class DeleteUserAsyncTask extends AsyncTask<User, Void, Void> {

        private DaoUser daoUser;

        public DeleteUserAsyncTask(DaoUser daoUser) {
            this.daoUser = daoUser;
        }

        @Override
        protected Void doInBackground(User... users) {
            daoUser.delete(users[0]);
            return null;
        }

    }       //end innerClass


    private static class DeleteAllUserAsyncTask extends AsyncTask<Void, Void, Void> {

        private DaoUser daoUser;

        public DeleteAllUserAsyncTask(DaoUser daoUser) {
            this.daoUser = daoUser;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            daoUser.deleteAllUsers();
            return null;
        }

    }       //end innerClass


}
