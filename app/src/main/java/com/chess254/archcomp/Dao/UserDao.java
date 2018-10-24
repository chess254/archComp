package com.chess254.archcomp.Dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.chess254.archcomp.Models.User;
import com.chess254.archcomp.Word;

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

}
