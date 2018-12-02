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
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.nio.charset.Charset;
import java.util.List;
import java.util.Random;

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

        FirebaseAuth firebaseAuth;
        FirebaseUser firebaseUser;


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
            int electricity = data.getExtras().getInt("electricity");
            int water = data.getExtras().getInt("water");
            int school = data.getExtras().getInt("school");
            int hospital = data.getExtras().getInt("hospital");
            int transportation = data.getExtras().getInt("transportation");
            int livingRoom = data.getIntExtra("living room", 0);
            int bath = data.getExtras().getInt("bath");
            int toilet = data.getExtras().getInt("toilet");
            int bedroom = data.getExtras().getInt("bedroom");
            int kitchen = data.getExtras().getInt("kitchen");
            int balcony = data.getExtras().getInt("balcony");



//              removed this to let empty constructor autogenerate primary key
//            House house = new House(21,type, price, area, rooms, location, description,
//                    "available", "image", 1, "5", 5  );


            //generate random string for house id, temporary soln
            byte[] array = new byte[7]; // length is bounded by 7
            new Random().nextBytes(array);
            String randomHouseIdTest = new String(array, Charset.forName("UTF-8"));

            House house = new House();
            house.setId(randomHouseIdTest);
            house.setTypeHouse(type);
            house.setLocationHouse(location);
            house.setRoomsHouse(rooms);
            //house.setOwnerHouse("Chess");// todo: hardcoded foreign user id it this , check out how to correct asap to get the relations working

            //try to use firebase to get logged in user and assign id to newly created house
            house.setOwnerHouse(FirebaseAuth.getInstance().getCurrentUser().getUid());

            house.setAreaHouse(area);
            house.setPriceHouse(price);
            house.setDescriptionHouse(description);
            house.setElectricity(electricity);
            house.setWater(water);
            house.setNumberLivingRoom(livingRoom);
            house.setNumberBath(bath);
            house.setNumberToilet(toilet);
            house.setNumberBedrooms(bedroom);
            house.setNumberKitchen(kitchen);
            house.setNumberBalcony(balcony);

            mHouseViewModel.insert(house);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "Listing not saved, fill all fields",
                    Toast.LENGTH_LONG).show();
        }
    }
}
