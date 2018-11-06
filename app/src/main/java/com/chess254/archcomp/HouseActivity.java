package com.chess254.archcomp;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.chess254.archcomp.Dao.HouseDao;
import com.chess254.archcomp.Models.House;

import java.util.List;

/**
 * Created by chess on 10/24/2018.
 */

public class HouseActivity extends AppCompatActivity {

    private static final int NEW_USER_ACTIVITY_REQUEST_CODE = 2 ;

    private HouseViewModel mHouseViewModel;






    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_houses);

        RecyclerView recyclerView = findViewById(R.id.houserecyclerview);
        final HouseListAdapter adapter = new HouseListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Toolbar toolbar = findViewById(R.id.toolbar_house);
        setSupportActionBar(toolbar);


        //recyclerview click listener
        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onLongClick(View child, int childPosition) {

            }

            @Override
            public void onClick(View child, int childPosition) {


//                Intent intent = new Intent(HouseActivity.this, HouseDetailActivity.class);
//                startActivity(intent);

                createIntent(childPosition);

            }
        }));


        //get a ViewModel from the ViewModelProvider.
        mHouseViewModel = ViewModelProviders.of(this).get(HouseViewModel.class);

        mHouseViewModel.getAllHouses().observe(this, new Observer<List<House>>() {
            @Override
            public void onChanged(@Nullable List<House> houses) {
                adapter.setmHouses(houses);
            }
        });

        FloatingActionButton fab = findViewById(R.id.fabhouse);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }

    public void createIntent(int childPosition)
    {
        LiveData<List<House>> houses = mHouseViewModel.getAllHouses();
        House Sample =  houses.getValue().get(childPosition);
        Intent intent = new Intent(HouseActivity.this, HouseDetailActivity.class);
//        intent.putExtra("house", (Parcelable) houses.getValue().get(childPosition));
        intent.putExtra("house", Sample);
        startActivity(intent);
    }
}
