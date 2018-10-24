package com.chess254.archcomp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.chess254.archcomp.Models.User;

import java.util.List;

/**
 * Created by chess on 10/23/2018.
 */

public class UserViewModel extends AndroidViewModel {

    //a private member variable to hold a reference to the repository.
    private ArchCompRepository mRepository;

    //a private LiveData member variable to cache the list of words.
    private LiveData<List<User>> mAllUsers;

    //a constructor that gets a reference to the repository and gets the list of words from the repository.
    public UserViewModel(Application application) {
        super(application);

        mRepository = new ArchCompRepository(application);
        mAllUsers = mRepository.getAllUsers();
    }

    //a "getter" method for all the words. This completely hides the implementation from the UI.
    LiveData<List<User>> getAllUsers(){
        return mAllUsers;
    }

    //a wrapper insert() method that calls the Repository's insert() method.
    // implementation of insert() is completely hidden from the UI.
    public void insert(User user){
        mRepository.insertUser(user);
    }
}
