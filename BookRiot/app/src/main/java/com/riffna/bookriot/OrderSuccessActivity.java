package com.riffna.bookriot;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.riffna.bookriot.model.BookModel;

public class OrderSuccessActivity extends AppCompatActivity {

    TextView buttonDone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_success);
        getSupportActionBar().hide();

        BookModel bookModel = getIntent().getParcelableExtra("BookModel");

        //Function Done Button
        buttonDone = findViewById(R.id.buttonDone);
        buttonDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMain();
            }
        });
    }

    //Function Start Main Activity
    public void openMain() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}