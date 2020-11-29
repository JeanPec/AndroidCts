package com.shindra.Timetable;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.shindra.R;

public class TimetableActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        getSupportActionBar().setTitle(getString(R.string.LabelNosTrams));
    }
}