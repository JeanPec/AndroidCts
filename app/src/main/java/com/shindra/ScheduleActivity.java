package com.shindra;

import android.os.Bundle;
import com.shindra.Misc.LoadingDialog;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentTransaction;


public class ScheduleActivity extends AppCompatActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);
        String passedLineName = getIntent().getStringExtra("lineName");
        LoadingDialog loadingDialog = new LoadingDialog(this);

        ScheduleFragment scheduleFragment = new ScheduleFragment(passedLineName, loadingDialog);

        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container, scheduleFragment).commit();
        getSupportActionBar().setTitle(R.string.schedule_view_name);


    }
}

