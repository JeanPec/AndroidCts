package com.shindra;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class StopActivity extends AppCompatActivity {

    public StopFragment stopFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stopactivity);

        Intent intentStartActivity = getIntent();
        String lineName = intentStartActivity.getStringExtra("LINE_NAME");

        stopFragment = new StopFragment(lineName);
        /* Initialize transaction */
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.FrameLayout, stopFragment);
        ft.commit();

    }

}