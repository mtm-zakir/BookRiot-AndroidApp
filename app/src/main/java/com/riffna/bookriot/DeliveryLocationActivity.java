package com.riffna.bookriot;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.SupportMapFragment;

public class DeliveryLocationActivity extends AppCompatActivity {

    TextView placeOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delivery_location);
        getSupportActionBar().hide();

        //Function Place Order
        placeOrder =findViewById(R.id.placeOrderButton);
        placeOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DeliveryLocationActivity.this, OrderSuccessActivity.class);
                startActivity(intent);
            }
        });

        Fragment fragment=new MapsFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.mapLayout,fragment).commit();
    }



}