package com.shindra.Schedule;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.shindra.OurTrams.OurTramsFragment;
import com.shindra.R;

public class ScheduleActivity extends AppCompatActivity {
    private static final String TAG = "ScheduleActivity";
    private Fragment mFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(TAG, "onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.av_schedule);

        // Get tramLineName
        Intent intent = getIntent();
        String tramLineName = intent.getStringExtra("tramLineName");

        // Create & call our fragment
        mFragment = new ScheduleFragment();
        Bundle bundle = new Bundle();
        bundle.putString("tramLineName", tramLineName);
        mFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.av_schedule_fragmentHolder, mFragment).commit();
    }
}