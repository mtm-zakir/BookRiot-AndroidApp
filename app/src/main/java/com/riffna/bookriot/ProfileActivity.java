package com.riffna.bookriot;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.riffna.bookriot.model.UserModel;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    String userDetails;
    Button signOutButton;
    TextView Name,Email,Phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Profile");

        //Receive Pushed Database Data
        userDetails=getIntent().getStringExtra("user_details");

        //Parse Database Data
        Name = findViewById(R.id.userNameText);
        Email = findViewById(R.id.userEmailText);
        Phone = findViewById(R.id.userPhoneText);

        signOutButton = findViewById(R.id.signOutButton);
        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openSignOut();
            }
        });

        getUserDetails();
    }
    //Function Start Login Activity
    public void openSignOut() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    //Function Get Current User Details
    public void getUserDetails(){
        DBHelper databaseHelper = new DBHelper(this);
        ArrayList<UserModel> aList = databaseHelper.getUserDetails(userDetails);
        UserModel userModel = aList.get(0);
        Name.setText(userModel.getUsed_name());
        Email.setText(userModel.getUsed_email());
        Phone.setText(userModel.getUsed_phone());
    }

    //Function Back Press
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("user_details",userDetails);  //User Data Push
        startActivity(intent);
    }
}