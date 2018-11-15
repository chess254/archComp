package com.chess254.archcomp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.chess254.archcomp.Models.User;

/**
 * Created by chess on 10/23/2018.
 */

public class NewUserActivity extends AppCompatActivity {


    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
    public static String EXTRA_PHONE = "PHONE";

    User user;

    private EditText editName;
    private EditText editImage;
    private EditText editEmail;
    private EditText editPhone;
    private EditText editAddress;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
        editName = findViewById(R.id.edit_name);
        editImage = findViewById(R.id.edit_image);
        editEmail = findViewById(R.id.edit_email);
        editPhone = findViewById(R.id.edit_phone);
        editAddress = findViewById(R.id.edit_address);



        final Button button = findViewById(R.id.button_save_user);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
//                if (TextUtils.isEmpty(editName.getText())) {
//                    setResult(RESULT_CANCELED, replyIntent);
//                } else {


//                    user = new House(1,editName.getText().toString(),editPhone.getText().toString(), editEmail.getText().toString(), editAddress.getText().toString(), editImage.getText().toString());

                String name = editName.getText().toString();
               String image = editImage.getText().toString();
              String email = editEmail.getText().toString();
                String phone = editPhone.getText().toString();
                String address = editAddress.getText().toString();

                    replyIntent.putExtra("name", name);
                    replyIntent.putExtra("image", image);
                    replyIntent.putExtra("email", email);

                    replyIntent.putExtra("phone", phone);
                    replyIntent.putExtra("address", address);

                    setResult(RESULT_OK, replyIntent);
//                }
                finish();
            }
        });
    }
}
