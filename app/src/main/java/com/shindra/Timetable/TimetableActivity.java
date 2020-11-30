package com.shindra.Timetable;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import com.shindra.R;
import com.shindra.ctslibrary.bo.Stop;

import java.util.ArrayList;

public class TimetableActivity extends AppCompatActivity {

   // private FrameLayout fragmentContainer;
    private String Ligne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.timetable_activity);
        getSupportActionBar().setTitle(getString(R.string.LabelHoraire));

        Intent intentStartActivity = getIntent();
        Ligne = intentStartActivity.getStringExtra("Ligne");
        Log.i("Info",Ligne + " Info Ligne transféré StartActivity ==> TimetableActivity");

        //fragmentContainer = (FrameLayout) findViewById(R.id.fragment_horaire);
        openFragment(Ligne);
    }

    public void openFragment(String Ligne){
        TimetableFragment fragment = TimetableFragment.newInstance(Ligne);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.addToBackStack(null);
        transaction.add(R.id.fragment_horaire, fragment, "TimetableFragment").commit();
    }
}