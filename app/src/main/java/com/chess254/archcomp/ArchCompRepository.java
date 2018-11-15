package com.chess254.archcomp;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.chess254.archcomp.Dao.HouseDao;
import com.chess254.archcomp.Dao.UserDao;
import com.chess254.archcomp.Models.House;
import com.chess254.archcomp.Models.User;

import java.util.List;

/**
 * Created by chess on 10/23/2018.
 */

public class ArchCompRepository {

    private WordDao wordDao;
    private UserDao userDao;
    private HouseDao houseDao;
    private LiveData<List<User>> allUsers;
    private LiveData<List<Word>> allWords;
    private LiveData<List<House>> allHouses;
//    private LiveData<House> getHouse;
//    private LiveData<List<House>> allHousesByUser;
    private LiveData<List<House>> houseByUserId;
    private int mUser_id;

//    private int id;

    //constructor that gets a handle to the database and initializes member variables
    ArchCompRepository(Application application) {
        ArchCompRoomDatabase db = ArchCompRoomDatabase.getDatabase(application);
        wordDao = db.wordDao();
        userDao = db.userDao();
        houseDao = db.houseDao();
        allWords = wordDao.getAllWords();
        allUsers = userDao.getAllUsers();
        allHouses = houseDao.getAllHouses();
        houseByUserId = getHouseByUserID(mUser_id);



//        getHouse = houseDao.findById("id");

    }

    LiveData<List<Word>> getAllWords() {
        return allWords;
    }

    LiveData<List<User>> getAllUsers() {
        return allUsers;
    }

    LiveData<List<House>> getAllHouses() {
        return allHouses;
    }

//    LiveData<House> getHouse(){
//        return getHouse;
//    }
//    LiveData<List<House>> findHouseCreatedById(int user_id){
//        return houseDao.findHouseCreatedById(user_id);
//       }
//    List<House> findHouseCreatedBy(int id){ return allHousesByUser;}

     LiveData<List<House>> getHouseByUserID(int user_id){
//        mUser_id = user_id;
        return houseDao.houseByUserId(user_id);
    }


    //Add a wrapper for the insert() method. You must call this on a non-UI thread
    // or your app will crash. Room ensures that you don't do any long-running
    // operations on the main thread, blocking the UI.
    public void insertWord(Word word) {
        new insertAsyncTask(wordDao).execute(word);


    }

    public void insertUser(User user) {
        new insertUserAsyncTask(userDao).execute(user);

    }

    public void insertHouse(House house) {
        new insertHouseAsyncTask(houseDao).execute(house);
    }

    //Asynctasks
    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... words) {
            mAsyncTaskDao.insert(words[0]);
            return null;
        }
    }

    private static class insertUserAsyncTask extends AsyncTask<User, Void, Void> {

        private UserDao mUserAsyncTaskDao;

        insertUserAsyncTask(UserDao dao) {
            mUserAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final User... users) {
            mUserAsyncTaskDao.insert(users[0]);
            return null;
        }
    }

    private  static class insertHouseAsyncTask extends AsyncTask<House,Void, Void> {

        private HouseDao mHouseAsyncTaskDao;

        insertHouseAsyncTask(HouseDao dao){
            mHouseAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final House... houses) {
            mHouseAsyncTaskDao.insert(houses[0]);
            return null;
        }
    }


}
