package com.chess254.archcomp;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

import com.chess254.archcomp.Dao.HouseDao;
import com.chess254.archcomp.Dao.UserDao;
import com.chess254.archcomp.Models.House;
import com.chess254.archcomp.Models.User;

/**
 * Created by chess on 10/23/2018.
 */

@Database(entities = {Word.class, User.class, House.class}, version = 1)
public abstract class ArchCompRoomDatabase extends RoomDatabase {

    //DAOs that work with the database
    public abstract WordDao wordDao();
    public abstract UserDao userDao();
    public abstract HouseDao houseDao();

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
        private final UserDao userDao;
        private final HouseDao houseDao;


        PopulateDbAsync(ArchCompRoomDatabase db) {
            mDao = db.wordDao();
            userDao = db.userDao();
            houseDao = db.houseDao();
        }

        @Override
        protected Void doInBackground(final Void... params) {
//           // mDao.deleteAll();
//            Word word = new Word("Hell");
//            mDao.insert(word);
//            word = new Word("Worl");
//            mDao.insert(word);

            //populate house
//            houseDao.deleteAll();
//            House house = new House(1, "Bungalow","2500", "200", "4",
//                    "Kapsoya Eldoret", "A very well maintained house with space" +
//                    "A very well maintained house with spaceALorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum. very well maintained house with space",
//                    "available","nice " + "pic", 1, "5 Star", 5);
//            House house2 = new House(2, "Maisonnette","2000", "300", "6",
//                    "milimani webuye", "A very well maintained house with space" +
//                    "A very well maintained house with spaceA very well maintained house with space",
//                    "available","nice " + "pic", 1, "5 Star", 5);
//            House house3 = new House(3, "Duplex","60,000", "2000", "3",
//                    "upperhill nairobi", "A very well maintained house with space" +
//                    "A very well maintained house with spaceA very well maintained house with space",
//                    "available","nice " + "pic", 1, "5 Star", 5);
//            House house4 = new House(4, "Studio","8,520", "255", "9",
//                    "Nakuru 58", "A very well maintained house with space" +
//                    "A very well maintained house with spaceA very well maintained house with space",
//                    "available","nice " + "pic", 1, "5 Star", 5);
//            House house5 = new House(5, "Flat","23,235", "9589", "15",
//                    "Huruma Eldoret", "A very well maintained house with space" +
//                    "A very well maintained house with spaceA very well maintained house with space",
//                    "available","nice " + "pic", 1, "5 Star", 5);
//            houseDao.insert(house,house2, house3, house4, house5);
            return null;
        }
    }

}
