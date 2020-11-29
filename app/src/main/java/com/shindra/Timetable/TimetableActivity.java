package com.shindra.Timetable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.shindra.R;

public class TimetableActivity extends AppCompatActivity {
    private FrameLayout fragmentContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_timetable);
        getSupportActionBar().setTitle(getString(R.string.LabelHoraire));

        Intent intentStartActivity = getIntent();
        String Ligne = intentStartActivity.getStringExtra("Ligne");
        Log.i("Info",Ligne + " Info Ligne transféré StartActivity ==> TimetableActivity");

        fragmentContainer = (FrameLayout) findViewById(R.id.fragment_container);
        openFragment();


    }

    public void openFragment(){
        TimetableFragment fragment = TimetableFragment.newInstance();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.addToBackStack(null);
        transaction.add(R.id.fragment_container, fragment, "TimetableFragment").commit();
    }
}