package com.chess254.archcomp;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.chess254.archcomp.Models.User;

import java.util.List;

import static com.chess254.archcomp.NewUserActivity.EXTRA_PHONE;
import static com.chess254.archcomp.NewUserActivity.EXTRA_REPLY;

public class MainActivity extends AppCompatActivity {

    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    private static final int NEW_USER_ACTIVITY_REQUEST_CODE = 2 ;
    //    private WordViewModel mWordViewModel;
    private UserViewModel mUserViewModel;
//    public static String EXTRA_PHONE = "PHONE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerview_user);
        final UserListAdapter adapter = new UserListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
//
        //added recyclerview touch listener to wrong recyclerview
//        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(this, recyclerView, new RecyclerTouchListener.ClickListener() {
//            @Override
//            public void onLongClick(View child, int childPosition) {
//
//            }
//
//            @Override
//            public void onClick(View child, int childPosition) {
//
//                Intent intent = new Intent(MainActivity.this, HouseDetailActivity.class);
//                startActivity(intent);
//
//            }
//        }));


        //ViewModelProviders to associate your ViewModel with your UI controller.
        // When your app first starts, the ViewModelProviders will create the ViewModel.
        // When the activity is destroyed, for example through a configuration change,
        // the ViewModel persists. When the activity is re-created, the ViewModelProviders
        // return the existing ViewModel.

        //get a ViewModel from the ViewModelProvider.
        mUserViewModel = ViewModelProviders.of(this).get(UserViewModel.class);

        // an observer for the LiveData returned by getAllWords().
        //The onChanged() method fires when the observed data changes and the activity is in the foreground.

//
        mUserViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable final List<User> users) {
                // Update the cached copy of the words in the adapter.
                adapter.setUsers(users);
            }
        });



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();

                Intent intent = new Intent(MainActivity.this, NewUserActivity.class);
                startActivityForResult(intent, NEW_USER_ACTIVITY_REQUEST_CODE);
//                startActivity(intent);
            }
        });

        FloatingActionButton houseButton = findViewById(R.id.button_houses);
        houseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, HouseActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    //If the activity returns with RESULT_OK, insert the returned word into the
    // database by calling the insert() method of the WordViewModel
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_USER_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {

            String name = data.getExtras().getString("name");
            String phone = data.getExtras().getString("phone");;
            String image = data.getExtras().getString("image");
            String address= data.getExtras().getString("address");;
            String email= data.getExtras().getString("email");;


//            image = getIntent().getStringExtra("image");
//            address = getIntent().getStringExtra("address");
//            email = getIntent().getStringExtra("email");


            User user = new User( name, email, phone, address, image );
            mUserViewModel.insert(user);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    "User not saved, fill all fields",
                    Toast.LENGTH_LONG).show();
        }
    }
}

//summary of the app at https://codelabs.developers.google.com/codelabs/android-room-with-a-view/index.html?index=..%2F..%2Findex#14