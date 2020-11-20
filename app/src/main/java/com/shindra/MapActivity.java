package com.shindra;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MapActivity extends AppCompatActivity {

    String lineName;
    MyMapFragment mapFragment;

    public MapActivity() {
        super();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.map_container);

        Intent intent = getIntent();
        lineName = getIntent().getStringExtra("lineNameSelected");

        setTitle(getString(R.string.map_name) +" "+ lineName );

        mapFragment = new MyMapFragment();

        Bundle mapBundle = new Bundle();
        mapBundle.putString("lineName",lineName);

        mapFragment.setArguments(mapBundle);

        getSupportFragmentManager().beginTransaction().replace(R.id.frame_MapContainer,mapFragment).commit();
    }
}
