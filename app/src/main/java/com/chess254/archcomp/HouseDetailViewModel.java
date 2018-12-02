package com.chess254.archcomp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.chess254.archcomp.Models.House;
import com.chess254.archcomp.Models.User;

import java.util.List;

/**
 * Created by chess on 10/24/2018.
 */

public class HouseDetailViewModel extends AndroidViewModel {

    //a private member variable to hold a reference to the repository.
    private ArchCompRepository mRepository;

    private int mUser_Id;

    //a private LiveData member variable to cache the list of houses.
    private LiveData<House> mHouse;

    //a constructor that gets a reference to the repository and gets the list of houses from the repository.
    public HouseDetailViewModel(@NonNull Application application) {
        super(application);

        mRepository = new ArchCompRepository(application);
//        mHouse = mRepository.getHouse();
    }
    //a "getter" method for all the words. This completely hides the implementation from the UI.
    LiveData<House> getHouse(){
        return mHouse;
    }

    //a wrapper insert() method that calls the Repository's insert() method.
    // implementation of insert() is completely hidden from the UI.
    public void insert(House house){
        mRepository.insertHouse(house);
    }

    LiveData<User> userById;

    LiveData<User> getUserById(String user_id){
//        mUser_Id = user_id;
//        return mRepository.getHouseByUserID(mUser_id);
        userById = mRepository.getUserById(user_id);
        return userById;

    }
}
