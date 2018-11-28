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

public class HouseViewModel extends AndroidViewModel {

    //a private member variable to hold a reference to the repository.
    private ArchCompRepository mRepository;

    //a private LiveData member variable to cache the list of houses.
    private LiveData<List<House>> mAllHouses;

    private LiveData<List<House>> mAllHousesByUser;
//    private List<House> mAllHousesByUser;


//    private int id;
//
    //a constructor that gets a reference to the repository and gets the list of houses from the repository.
    public HouseViewModel(@NonNull Application application) {
        super(application);

        mRepository = new ArchCompRepository(application);
        mAllHouses = mRepository.getAllHouses();

//       mAllHousesByUser = ArchCompRoomDatabase.getDatabase(application).houseDao().findHouseCreatedById(id);
    }
    //a "getter" method for all the words. This completely hides the implementation from the UI.
    LiveData<List<House>> getAllHouses(){
        return mAllHouses;
    }

    LiveData<List<House>> getHouseByUserId(String user_id){
        return mRepository.getHouseByUserID(user_id);

    }
//    List<House> findAllHousesByUser(final int id){
//    return mAllHousesByUser;
//}

    //a wrapper insert() method that calls the Repository's insert() method.
    // implementation of insert() is completely hidden from the UI.
    public void insert(House house){
        mRepository.insertHouse(house);
    }
}
