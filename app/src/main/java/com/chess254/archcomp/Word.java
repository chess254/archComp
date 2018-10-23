package com.chess254.archcomp;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by chess on 10/23/2018.
 */


@Entity(tableName = "word_table")
public class Word {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "word")
    private String word;



    public Word(@NonNull String word) {
        this.word = word;
    }
    public String getWord() {
        return word;
    }


}
