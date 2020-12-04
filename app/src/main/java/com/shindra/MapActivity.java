package com.shindra;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class MapActivity extends AppCompatActivity {

    public String strLineName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.maplayout);
        strLineName = getIntent().getStringExtra("lineName");
        setTitle("Ligne " + strLineName);

        LigneMapFragment fragment = LigneMapFragment.newInstance(strLineName);
        getSupportFragmentManager().beginTransaction().add(R.id.carte, fragment).commit();
    }

}
