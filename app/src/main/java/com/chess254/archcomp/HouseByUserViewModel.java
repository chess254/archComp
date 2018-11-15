package com.chess254.archcomp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.chess254.archcomp.Models.House;

import java.util.List;

/**
 * Created by chess on 11/9/2018.
 */

public class HouseByUserViewModel extends AndroidViewModel {

    private ArchCompRepository mRepository;

    private int mUser_id;

    //a private LiveData member variable to cache the list of houses.
    private LiveData<List<House>> houseByUserId;

    public HouseByUserViewModel(@NonNull Application application) {
        super(application);

        mRepository = new ArchCompRepository(application);

    }

    LiveData<List<House>> getHouseByUserId(int user_id){
        mUser_id = user_id;
//        return mRepository.getHouseByUserID(mUser_id);
        houseByUserId = mRepository.getHouseByUserID(mUser_id);
        return houseByUserId;

    }
}
