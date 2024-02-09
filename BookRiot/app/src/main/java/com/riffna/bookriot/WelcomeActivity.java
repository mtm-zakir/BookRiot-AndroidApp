package com.riffna.bookriot;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class WelcomeActivity extends AppCompatActivity {

    private Button button;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        getSupportActionBar().hide();

        button= findViewById(R.id.startButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override    public void onClick(View view) {openNext();
            }});
    }
    public void openNext(){
        Intent intent=new Intent(this, LoginActivity.class);
        startActivity(intent);}

}

