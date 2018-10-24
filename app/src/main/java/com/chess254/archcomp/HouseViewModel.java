package com.chess254.archcomp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.chess254.archcomp.Models.House;


import java.util.List;

/**
 * Created by chess on 10/24/2018.
 */

public class HouseViewModel extends AndroidViewModel {

    //a private member variable to hold a reference to the repository.
    private ArchCompRepository mRepository;

    //a private LiveData member variable to cache the list of words.
    private LiveData<List<House>> mAllHouses;

    //a constructor that gets a reference to the repository and gets the list of words from the repository.
    public HouseViewModel(@NonNull Application application) {
        super(application);

        mRepository = new ArchCompRepository(application);
        mAllHouses = mRepository.getAllHouses();
    }
    //a "getter" method for all the words. This completely hides the implementation from the UI.
    LiveData<List<House>> getAllHouses(){
        return mAllHouses;
    }

    //a wrapper insert() method that calls the Repository's insert() method.
    // implementation of insert() is completely hidden from the UI.
    public void insert(House house){
        mRepository.insertHouse(house);
    }
}
