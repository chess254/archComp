package com.chess254.archcomp;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.chess254.archcomp.Models.House;

import java.util.List;

/**
 * Created by chess on 10/24/2018.
 */

public class HouseActivity extends AppCompatActivity {

    private static final int NEW_HOUSE_ACTIVITY_REQUEST_CODE = 3;
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


        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
            @Override
            public void onLongClick(View child, int childPosition) {

            }

            @Override
            public void onClick(View child, int childPosition) {

                LiveData<List<House>> houses = mHouseViewModel.getAllHouses();
                House clickedHouse = houses.getValue().get(childPosition);
                Intent intent = new Intent(HouseActivity.this, HouseDetailActivity.class);
                intent.putExtra("house", clickedHouse);
                startActivity(intent);

//                Intent intent = new Intent(HouseActivity.this, HouseDetailActivity.class);
//                startActivity(intent);

//                createIntent(childPosition);

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

                Intent intent = new Intent(HouseActivity.this, NewHouseActivity.class);
                startActivityForResult(intent, NEW_HOUSE_ACTIVITY_REQUEST_CODE);

            }
        });


    }

//    public void createIntent(int childPosition)
//    {
//        LiveData<List<House>> houses = mHouseViewModel.getAllHouses();
//        House clickedHouse =  houses.getValue().get(childPosition);
//        Intent intent = new Intent(HouseActivity.this, HouseDetailActivity.class);
////        intent.putExtra("house", (Parcelable) houses.getValue().get(childPosition));
//        intent.putExtra("house", clickedHouse);
//        startActivity(intent);
//    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_HOUSE_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            String type = data.getExtras().getString("type");
            String location = data.getExtras().getString("location");
            String rooms = data.getExtras().getString("rooms");
            String area = data.getExtras().getString("area");
            String price = data.getExtras().getString("price");
            String description = data.getExtras().getString("description");


//              removed this to let empty constructor autogenerate primary key
//            House house = new House(21,type, price, area, rooms, location, description,
//                    "available", "image", 1, "5", 5  );


            House house = new House();
            house.setTypeHouse(type);
            house.setLocationHouse(location);
            house.setRoomsHouse(rooms);
            house.setOwnerHouse(1);// todo: hardcoded foreign user id it this , check out how to correct asap to get the relations working
            house.setAreaHouse(area);
            house.setPriceHouse(price);
            house.setDescriptionHouse(description);
            mHouseViewModel.insert(house);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Listing not saved, fill all fields",
                    Toast.LENGTH_LONG).show();
        }
    }
}
