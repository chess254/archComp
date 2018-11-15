package com.chess254.archcomp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by chess on 10/23/2018.
 */


//NB Never pass context into ViewModel instances. Do not store Activity, Fragment, or View instances
// or their Context in the ViewModel.
//For example, an Activity can be destroyed and created many times during the lifecycle of a
// ViewModel as the device is rotated. If you store a reference to the Activity in the ViewModel,
// you end up with references that point to the destroyed Activity. This is a memory leak.


public class WordViewModel extends AndroidViewModel {

    //a private member variable to hold a reference to the repository.
    private ArchCompRepository mRepository;

    //a private LiveData member variable to cache the list of words.
    private LiveData<List<Word>> mAllWords;

    //a constructor that gets a reference to the repository and gets the list of words from the repository.
    public WordViewModel(Application application) {
        super(application);

        mRepository = new ArchCompRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    //a "getter" method for all the words. This completely hides the implementation from the UI.
    LiveData<List<Word>> getAllWords(){
        return mAllWords;
    }

    //a wrapper insert() method that calls the Repository's insert() method.
    // implementation of insert() is completely hidden from the UI.
    public void insert(Word word){
        mRepository.insertWord(word);
    }
}
