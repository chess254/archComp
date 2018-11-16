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

import java.util.UUID;

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
            userDao.deleteAll();
            User userTest = new User(1,"chess","0721542746","lae2006a@gmail.com","kapsoya eldoret", "my image");
            User userTest2 = new User(2,"chess2","phone2","lae2006a@gmail.com"," eldoret", "my image");

            userDao.insert(userTest, userTest2);
            houseDao.deleteAll();
            House houseTest = new House();
            houseTest.setId(1);
            houseTest.setOwnerHouse(1);
            houseTest.setDescriptionHouse("a very nice house");
            houseTest.setTypeHouse("bungalow");
            houseTest.setAreaHouse("200");

            House houseTest2 = new House();
            houseTest.setId(2);
            houseTest.setTypeHouse("Bungalow");
            houseTest.setLocationHouse("Kapsoya, Eldoret");
            houseTest.setRoomsHouse("15");
            houseTest.setOwnerHouse(2);
            houseTest.setAreaHouse("300");
            houseTest.setPriceHouse("30,000");
            houseTest.setDescriptionHouse("This book is dedicated to all the loyal readers of\n" +
                    "antonioleiva.com\n" +
                    ", who made me\n" +
                    "believe that writing about Android development was a powerful tool to help others\n" +
                    "learn about it. I felt that this book was a necessary step forward.\n" +
                    "Special mention goes to\n" +
                    "Luis Herrero\n" +
                    ", who designed the excellent cover of this book,\n" +
                    "and to\n" +
                    "Gautier Mechling\n" +
                    "for helping me so much by reviewing this book. It is thanks\n" +
                    "to him that these pages are not full of typos and mistakes.\n" +
                    "And,ofcourse,thisisspeciallydedicatedtoyou.Withyoursupportandyourhelp,this\n" +
                    "book is growing and becoming a reference. So any suggestions to improve the quality\n" +
                    "ofthisbookwillbewelcomed.Feelfreetowriteanytimeto\n" +
                    "contact@antonioleiva.com\n" +
                    ".");


            House house = new House();
//            house.setTypeHouse(type);
//            house.setLocationHouse(location);
//            house.setRoomsHouse(rooms);
//            house.setOwnerHouse(1);// todo: hardcoded foreign user id it this , check out how to correct asap to get the relations working
//            house.setAreaHouse(area);
//            house.setPriceHouse(price);
//            house.setDescriptionHouse(description);
//            mHouseViewModel.insert(house);



            houseDao.insert(houseTest);
            return null;
        }
    }

}
