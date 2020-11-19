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

    public ArrayList<Stop> stopsWithDepartureTime;
    public StopFragment stopFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stopactivity);

        /* ArrayList containing subway's stops and their departure time received with network */
        stopsWithDepartureTime = new ArrayList<Stop>();

        Intent intentStartActivity = getIntent();
        String lineName = intentStartActivity.getStringExtra("LINE_NAME");

        stopFragment = new StopFragment(lineName);
        /* Initialize transaction */
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.FrameLayout, stopFragment);
        ft.commit();

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);

        ObservableExtensionKt.observe(model.lineWithEstimatedTimeTable(RouteType.TRAM, stopFragment.getLineName(), 0), new ObservableListener<Line>() {
            @Override
            public void onLoading() {
                Log.i("Stop", "Connexion in progress");
            }

            @Override
            public void onSuccess(Line data) {
                Log.i("Stop", "Connexion established");
                //Log.i("Stop", "data = " + data);

                for(Stop item : data.getStops())
                {
                    if(item.getEstimatedDepartureTime() != null)
                        stopsWithDepartureTime.add(item);
                }
                Log.i("Stop", "stops = " + stopsWithDepartureTime);
                stopFragment.updateWidget(stopsWithDepartureTime);


            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                Log.i("Stop", "Network Error !");
            }
        });

    }

}