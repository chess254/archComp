package com.chess254.archcomp;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.chess254.archcomp.Models.House;

import java.util.List;

/**
 * Created by chess on 11/8/2018.
 */

public class HouseByUser extends AppCompatActivity {

    private LiveData<List<House>> houseByUserList;
    private HouseByUserViewModel houseByUserViewModel;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_house_by_user);

        final TextView textView = findViewById(R.id.textView2);
        Button button = findViewById(R.id.button2);

        //get a ViewModel from the ViewModelProvider.
        houseByUserViewModel = ViewModelProviders.of(this).get(HouseByUserViewModel.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                houseByUserViewModel.getHouseByUserId(1)
                        .observe(HouseByUser.this,
                                new Observer<List<House>>() {
                                    @Override
                                    public void onChanged(@Nullable List<House> houses) {
                                        textView.setText(houses.get(0).getDescriptionHouse());
                                    }
                                });

            }
        });
    }


}


