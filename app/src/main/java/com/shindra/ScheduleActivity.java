package com.shindra;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;


public class ScheduleActivity extends AppCompatActivity {
    String lineName;

    RecyclerView recyclerViewStops;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.schedule_menu);

        Intent intent = getIntent();
        lineName = getIntent().getStringExtra("lineNameSelected");


    }


}
