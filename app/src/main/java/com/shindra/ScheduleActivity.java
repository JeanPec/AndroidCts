package com.shindra;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentTransaction;


public class ScheduleActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        String passedLineName = getIntent().getStringExtra("lineName");

        ScheduleFragment scheduleFragment = new ScheduleFragment(passedLineName);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, scheduleFragment).commit();
        getSupportActionBar().setTitle(R.string.schedule_view_name);


    }
}

