package com.chess254.archcomp;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

/**
 * Created by chess on 10/23/2018.
 */

@Database(entities = {Word.class}, version = 1)
public abstract class ArchCompRoomDatabase extends RoomDatabase {

    //DAOs that work with the database
    public abstract WordDao wordDao();

    //make the archCompRoon\database a singleton to prevent multiple instances
    private  static  volatile ArchCompRoomDatabase INSTANCE;
    static ArchCompRoomDatabase getDatabase(final Context context){
        if(INSTANCE ==null) {
            synchronized(ArchCompRoomDatabase.class){

                if(INSTANCE == null){

                    //create DB
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), ArchCompRoomDatabase.class, "arch_comp_database")
                            .addCallback(sRoomDatabaseCallback) //added callback to room db to populate db with dummy data
                            .build();
                }

            }
        }

        return INSTANCE;
    }


    //added to populate db with dummy data
    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };

    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final WordDao mDao;

        PopulateDbAsync(ArchCompRoomDatabase db) {
            mDao = db.wordDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
//           // mDao.deleteAll();
//            Word word = new Word("Hell");
//            mDao.insert(word);
//            word = new Word("Worl");
//            mDao.insert(word);
            return null;
        }
    }

}
