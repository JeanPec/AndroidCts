package com.shindra.Schedule;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.R;


public class MyScheduleActivity extends AppCompatActivity {

    String lineName;
    RecyclerView recyclerViewSchedule;
    Fragment fragmentSchedule;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_container);
        setTitle(R.string.schedule_name);


        Intent intent = getIntent();
        lineName = getIntent().getStringExtra("lineNameSelected");

        fragmentSchedule = MyScheduleFragment.newInstance(lineName);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragScheduleContainer,fragmentSchedule).commit();

    }
}


