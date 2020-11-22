package com.shindra.Schedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.shindra.R;

public class ScheduleActivity extends AppCompatActivity {

    // Attributes
    private static final String TAG = "ScheduleActivity";
    private Fragment mFragment;

    // Methods
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.av_schedule);

        // Retrieve intent data
        Intent intent = getIntent();
        String tramLineLetter = intent.getStringExtra("tramLineLetter");

        // Create, pass args & call fragment
        mFragment = ScheduleFragment.onInstance(tramLineLetter);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.av_schedule_fragmentHolder, mFragment).commit();
    }
}