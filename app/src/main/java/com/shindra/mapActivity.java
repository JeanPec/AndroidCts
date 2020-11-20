package com.shindra;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;


import com.shindra.arrakis.controls.MapFragment;
import com.shindra.arrakis.controls.Poi;
import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class mapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        loadingWindow window = new loadingWindow(mapActivity.this);

        Intent intentFromStartActivity = getIntent();
        String lineName = intentFromStartActivity.getStringExtra("LINE_NAME") ;
        getSupportActionBar().setTitle("Ligne " + lineName);

        mapFragment frag = new mapFragment();
        getSupportFragmentManager().beginTransaction().add(R.id.map_container,frag).commit();

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lineWithStop(RouteType.TRAM,lineName), new ObservableListener<Line>() {

            @Override
            public void onError(@NotNull Throwable throwable) {

            }

            @Override
            public void onSuccess(Line data) {

                window.dismissLoadingWindow();
                frag.addPoi(data);
            }

            @Override
            public void onLoading() {
                window.displayLoadingWindow();
            }
        });

    }

}