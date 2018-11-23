package com.chess254.archcomp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import com.chess254.archcomp.Models.House;
import com.chess254.archcomp.Models.User;

/**
 * Created by chess on 11/7/2018.
 */

public class NewHouseActivity extends AppCompatActivity {


    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
    public static String EXTRA_HOUSE = "HOUSE";

    House mHouse;

    private EditText mType;
    private EditText mLocation;
    private EditText mRooms;
    private EditText mArea;
    private EditText mPrice;
    private EditText mDescription;

    private Switch mElectricity;
    private Switch mWater;
    private Switch mSchool;
    private Switch mHospital;
    private Switch mTransportion;

    private EditText mLivingRoom;
    private EditText mBath;
    private EditText mToilet;
    private EditText mBedroom;
    private EditText mKitchen;
    private EditText mBalcony;






    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_house);

        mType = findViewById(R.id.edit_type);
        mLocation = findViewById(R.id.edit_location);
        mRooms = findViewById(R.id.edit_rooms);
        mArea = findViewById(R.id.edit_area);
        mPrice = findViewById(R.id.edit_price);
        mDescription = findViewById(R.id.edit_description);

        //Switches
        mElectricity = findViewById(R.id.switchElectricity);
        mWater = findViewById(R.id.switchWater);
        mSchool = findViewById(R.id.switchSchool);
        mHospital = findViewById(R.id.switchHospital);
        mTransportion = findViewById(R.id.switchTransportation);

        //details
        mLivingRoom = findViewById(R.id.edit_living_room);
        mBath = findViewById(R.id.edit_bath);
        mToilet = findViewById(R.id.edit_toilet);
        mBedroom = findViewById(R.id.edit_bedroom);
        mKitchen = findViewById(R.id.edit_kitchen);
        mBalcony = findViewById(R.id.edit_balcony);





        final Button button = findViewById(R.id.button_save_house);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mDescription.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {


//                    mHouse = new House(1,editName.getText().toString(),editPhone.getText().toString(), editEmail.getText().toString(), editAddress.getText().toString(), editImage.getText().toString());
                        //overview
                    String type = mType.getText().toString();
                    String location = mLocation.getText().toString();
                    String rooms = mRooms.getText().toString();
                    String area = mArea.getText().toString();
                    String price = mPrice.getText().toString();
                    String description = mDescription.getText().toString();

                    //switches
                    boolean electricity = mElectricity.isChecked();
                    boolean water = mWater.isChecked();
                    boolean school = mSchool.isChecked();
                    boolean hospital = mHospital.isChecked();
                    boolean transportation = mTransportion.isChecked();

                    int Electricity = ( electricity ) ? 1 : 0;
                    int Water = ( water ) ? 1 : 0;
                    int School = ( school ) ? 1 : 0;
                    int Hospital = ( hospital ) ? 1 : 0;
                    int Transportation = ( transportation ) ? 1 : 0;

                    //details
                    int livingRoom = Integer.parseInt(mLivingRoom.getText().toString());
                    int bath = Integer.parseInt(mBath.getText().toString());
                    int toilet = Integer.parseInt(mToilet.getText().toString());
                    int bedroom = Integer.parseInt(mBedroom.getText().toString());
                    int kitchen = Integer.parseInt(mKitchen.getText().toString());
                    int balcony = Integer.parseInt(mBalcony.getText().toString());



                    replyIntent.putExtra("type", type);
                    replyIntent.putExtra("location", location);
                    replyIntent.putExtra("rooms", rooms);
                    replyIntent.putExtra("area", area);
                    replyIntent.putExtra("price", price);
                    replyIntent.putExtra("description", description);

                    replyIntent.putExtra("electricity",Electricity);
                    replyIntent.putExtra("water",Water);
                    replyIntent.putExtra("school",School);
                    replyIntent.putExtra("hospital",Hospital);
                    replyIntent.putExtra("transportation",Transportation);

                    replyIntent.putExtra("living room",livingRoom);
                    replyIntent.putExtra("bath",bath);
                    replyIntent.putExtra("toilet",toilet);
                    replyIntent.putExtra("bedroom",bedroom);
                    replyIntent.putExtra("kitchen",kitchen);
                    replyIntent.putExtra("balcony",balcony);



                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
