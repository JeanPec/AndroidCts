package com.shindra;


import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HoursActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String strLineName = getIntent().getStringExtra("lineName");

        setContentView(R.layout.hours_main);
        setTitle(R.string.HoraireText);

        Bundle bundle = new Bundle();
        bundle.putString("lineName", strLineName);
        HoursFragment hoursFragment = new HoursFragment();
        hoursFragment.setArguments(bundle);
        getSupportFragmentManager().beginTransaction().add(R.id.hoursFragment, hoursFragment).commit();

    }
}