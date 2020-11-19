package com.shindra;

import androidx.appcompat.app.AppCompatActivity;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;


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
        mapFragment frag = new mapFragment();


        Intent intentFromStartActivity = getIntent();
        String lineName = intentFromStartActivity.getStringExtra("LINE_NAME") ;

        loadingWindow window = new loadingWindow(mapActivity.this);

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lineWithStop(RouteType.TRAM,lineName), new ObservableListener<Line>() {


            @Override
            public void onError(@NotNull Throwable throwable) {

            }

            @Override
            public void onSuccess(Line data) {

                window.dismissLoadingWindow();
                ArrayList<Poi> listOfPoi = new ArrayList<Poi>() ;

                Log.i("Main", "TAILLE DE DATA : " + data.getStops().size());
                for (Stop stop:data.getStops()) {

                    listOfPoi.add(new Poi(R.drawable.icon_maps_place_24px,R.color.ligneA,stop.component5().getLatitude(),stop.component5().getLongitude()));
                }

                frag.addPoi(listOfPoi);

            }

            @Override
            public void onLoading() {
                window.displayLoadingWindow();
                getSupportActionBar().setTitle("Ligne " + lineName);
            }
        });

    }

}