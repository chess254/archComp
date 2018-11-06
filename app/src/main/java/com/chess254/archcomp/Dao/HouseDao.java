package com.chess254.archcomp.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.chess254.archcomp.Models.House;


import java.util.List;

/**
 * Created by chess on 10/24/2018.
 */
@Dao
public interface HouseDao {

    @Insert
    Long[] insert (House...house);

    @Query("SELECT * FROM house_table ORDER BY house_id ASC")
    LiveData<List<House>> getAllHouses();
    @Query("DELETE FROM house_table")
    void deleteAll();

//    @Query("SELECT * FROM house_table WHERE id=:id")
//    LiveData<House> findById(String id);

}
