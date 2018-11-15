package com.chess254.archcomp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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




        final Button button = findViewById(R.id.button_save_house);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (TextUtils.isEmpty(mDescription.getText())) {
                    setResult(RESULT_CANCELED, replyIntent);
                } else {


//                    mHouse = new House(1,editName.getText().toString(),editPhone.getText().toString(), editEmail.getText().toString(), editAddress.getText().toString(), editImage.getText().toString());

                    String type = mType.getText().toString();
                    String location = mLocation.getText().toString();
                    String rooms = mRooms.getText().toString();
                    String area = mArea.getText().toString();
                    String price = mPrice.getText().toString();
                    String description = mDescription.getText().toString();


                    replyIntent.putExtra("type", type);
                    replyIntent.putExtra("location", location);
                    replyIntent.putExtra("rooms", rooms);
                    replyIntent.putExtra("area", area);
                    replyIntent.putExtra("price", price);
                    replyIntent.putExtra("description", description);

                    setResult(RESULT_OK, replyIntent);
                }
                finish();
            }
        });
    }
}
