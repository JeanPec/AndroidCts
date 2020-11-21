package com.shindra.Schedule;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.R;


public class ScheduleActivity extends AppCompatActivity {

    String lineName;
    RecyclerView recyclerViewSchedule;
    Fragment fragmentSchedule;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_container);
        setTitle(R.string.schedule_name);

//recupere le intent de StartActivity + recuperation de la ligne sélectionné
        Intent intent = getIntent();
        lineName = getIntent().getStringExtra("lineNameSelected");

        fragmentSchedule = ScheduleFragment.newInstance(lineName);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragScheduleContainer,fragmentSchedule).commit();

    }
}



