package com.chess254.archcomp;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by chess on 10/23/2018.
 */

@Dao
public interface WordDao {

    @Insert
    void insert(Word word);

    @Query("SELECT * FROM word_table ORDER BY word ASC")
    LiveData<List<Word>> getAllWords();
    @Query("DELETE FROM word_table")
    void deleteAll();
}
