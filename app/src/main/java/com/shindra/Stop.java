package com.shindra;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class Stop extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stop);

        Intent intentStartActivity = getIntent();
        String name = intentStartActivity.getStringExtra("LINE_NAME");

        /* Log.d("Stop", "name = " + name); */

        /* Initialize transaction */
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.stop_fragment, new StopFragment());
        ft.commit();

    }
}