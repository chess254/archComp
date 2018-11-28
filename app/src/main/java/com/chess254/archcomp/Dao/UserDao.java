package com.chess254.archcomp.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.chess254.archcomp.Models.User;
import com.chess254.archcomp.Word;
import com.chess254.archcomp.Models.House;

import java.util.List;

/**
 * Created by chess on 10/23/2018.
 */
@Dao
public interface UserDao {

    @Insert
    Long[] insert (User ...user);

    @Query("SELECT * FROM user_table ORDER BY user_id ASC")
    LiveData<List<User>> getAllUsers();
    @Query("DELETE FROM word_table")
    void deleteAll();
    @Query ("SELECT * FROM house_table WHERE user_id = :user_id")
    LiveData<List<House>> houseByUserId(int user_id);

    @Query("SELECT * FROM user_table WHERE user_id = :user_id")
    LiveData<User> getUserById(String user_id);

}
