package com.chess254.archcomp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.chess254.archcomp.Models.House;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import android.app.AlertDialog;

import dmax.dialog.SpotsDialog;

/**
 * Created by chess on 11/16/2018.
 */

public class ActivityLogin extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

    public static  final int PERMISSION_SIGN_IN = 6329; //any integer value
    GoogleApiClient mGoogleApiClient;
    SignInButton googleSignInButton;
    Button loginWithEmailAndPassword;
    FirebaseAuth firebaseAuth;

    EditText mEmail;
    EditText mPassword;

    AlertDialog waiting_dialog;

    TextView optionRegister;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        configureGoogleSignIn();

        firebaseAuth = FirebaseAuth.getInstance();

        loginWithEmailAndPassword = findViewById(R.id.btn_login_with_email_and_password);
        loginWithEmailAndPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                waiting_dialog.show();
                loginWithEmailAndPassword();
            }
        });

        googleSignInButton = findViewById(R.id.google_sign_in);
        waiting_dialog = new SpotsDialog.Builder().setContext(this)
                .setMessage("Processing...")
                .setCancelable(false)
                .build();
        googleSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                waiting_dialog.show();

                signIn();
            }
        });

        optionRegister = findViewById(R.id.register);

        optionRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityLogin.this, ActivityRegister.class);
                startActivity(intent);
            }
        });


    }

    private void loginWithEmailAndPassword() {
        mEmail = findViewById(R.id.input_email);
        mPassword = findViewById(R.id.input_password);
        String email = mEmail.getText().toString();
        String password = mPassword.getText().toString();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(ActivityLogin.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d("KODESHA :", "signInWithEmail:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();

                            Intent intent = new Intent(ActivityLogin.this, HouseActivity.class);
                            intent.putExtra("email", user.getEmail());
                            waiting_dialog.dismiss();
                            startActivity(intent);
                            finish();

                        } else {

                            waiting_dialog.dismiss();
                            Log.w("KODESHA :", "signInWithEmail:failure", task.getException());
                            Toast.makeText(ActivityLogin.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }
                    }
                });

    }


    @Override
    protected void onStart() {
        super.onStart();

        //firebaseauth check if user is signed in and update ui

        FirebaseUser currentuser = firebaseAuth.getCurrentUser();


    }

    private void signIn() {

        Intent intent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        startActivityForResult(intent, PERMISSION_SIGN_IN);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == PERMISSION_SIGN_IN){

            waiting_dialog.show();

            GoogleSignInResult result =  Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()){



                GoogleSignInAccount account = result.getSignInAccount();
                String idToken = account.getIdToken();

                AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
                firebaseAuthWithGoogle(credential);



            } else {

                waiting_dialog.dismiss();

                Log.e("ARCH_COMP_ERROR", "login failed");
                Log.e("ARCH_COMP_ERROR", result.getStatus().getStatusMessage());

            }
        }
    }



    private void firebaseAuthWithGoogle(AuthCredential credential) {
        firebaseAuth.signInWithCredential(credential)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        //start new activity and pass email to it
                        Intent intent = new Intent(ActivityLogin.this, HouseActivity.class);
                        intent.putExtra("email", authResult.getUser().getEmail());
                        intent.putExtra("url_pic", authResult.getUser().getPhotoUrl());
                        waiting_dialog.dismiss();
                        startActivity(intent);
                        finish();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(ActivityLogin.this, ""+e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void configureGoogleSignIn() {
        GoogleSignInOptions options = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleApiClient =new GoogleApiClient.Builder(this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API,options)
                .build();

        mGoogleApiClient.connect();
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        Toast.makeText(this, ""+connectionResult.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }


}
