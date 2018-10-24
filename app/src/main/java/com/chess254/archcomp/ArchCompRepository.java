package com.chess254.archcomp;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.os.AsyncTask;

import com.chess254.archcomp.Dao.UserDao;
import com.chess254.archcomp.Models.User;

import java.util.List;

/**
 * Created by chess on 10/23/2018.
 */

public class ArchCompRepository {

    private WordDao wordDao;
    private UserDao userDao;
    private LiveData<List<User>> allUsers;
    private LiveData<List<Word>> allWords;

    //constructor that gets a handle to the database and initializes member variables
    ArchCompRepository(Application application){
        ArchCompRoomDatabase db = ArchCompRoomDatabase.getDatabase(application);
        wordDao = db.wordDao();
        userDao = db.userDao();
        allWords = wordDao.getAllWords();
        allUsers = userDao.getAllUsers();

    }

            LiveData<List<Word>> getAllWords(){
                return  allWords;
            }

            LiveData<List<User>> getAllUsers(){
                return allUsers;
            }


    //Add a wrapper for the insert() method. You must call this on a non-UI thread
    // or your app will crash. Room ensures that you don't do any long-running
    // operations on the main thread, blocking the UI.
    public  void insertWord(Word word) {
        new insertAsyncTask(wordDao).execute(word);


    }

    public  void insertUser( User user) {
        new insertUserAsyncTask(userDao).execute(user);

    }

    //Asynctasks
    private static class insertAsyncTask extends AsyncTask<Word, Void, Void>{

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

    private static class insertUserAsyncTask extends AsyncTask<User, Void, Void>{

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


}
