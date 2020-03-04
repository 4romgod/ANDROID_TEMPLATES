package com.template.users;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

/**
 * <p>Actual Database with a list of all Entities as tables</p>
 */
@Database(entities =  {User.class}, version = 1, exportSchema = false)
public abstract class DatabaseUsers extends RoomDatabase {

    public static DatabaseUsers dbInstance;

    public abstract DaoUser getDaoUser();

    /**
     * <p>Create a database instance</p>
     * <p>Get a database instance</p>
     * <p>Synchronize means only one instance will access database at a time</p>
     * @return Return the database instance
      */
    public static synchronized DatabaseUsers getDatabaseInstance(Context context) {
        if (dbInstance == null) {
            dbInstance = Room.databaseBuilder(context.getApplicationContext(), DatabaseUsers.class, User.TABLE_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .addCallback(roomCallback)
                    .build();
        }

        return dbInstance;

    }       //end getDatabaseInstance()


    /**
     * Callback to populate database when the database is first created
     */
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(dbInstance).execute();
        }
    };


    /**
     * Populate the database in the background using AsyncTask
     */
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private DaoUser daoUser;

        private PopulateDbAsyncTask(DatabaseUsers db){
            daoUser = db.getDaoUser();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            User user1 = new User("Ebenezer", "Mathebula", "I love boxing too much", R.drawable.smile);
            User user2 = new User("Nsuku", "Mathebula", "I training", R.drawable.serious);
            User user3 = new User("Clarah", "Mathebula", "I love farming", R.drawable.pretty);
            User user4 = new User("Bonny", "Mathebula", "I love the law", R.drawable.shorts);
            User user5 = new User("Ebenezer", "Mathebula", "I love boxing too much", R.drawable.smile);
            User user6 = new User("Nsuku", "Mathebula", "I training", R.drawable.serious);

            daoUser.insert(user1);
            daoUser.insert(user2);
            daoUser.insert(user3);
            daoUser.insert(user4);
            daoUser.insert(user5);
            daoUser.insert(user6);

            return null;
        }
    }       //end innerClass


}       //end class
