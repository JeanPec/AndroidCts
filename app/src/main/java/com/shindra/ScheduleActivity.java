package com.shindra;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ScheduleActivity extends AppCompatActivity {

    private RecyclerView ScheduleRecyclerView;
    private RecyclerView.Adapter ScheduleAdapter;
    private RecyclerView.LayoutManager ScheduleLayoutManager;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        String lineName = intent.getStringExtra("LINE");
        String lineID;
        System.out.println("OUT " + lineName);

        if ("Parc des Sports - Illkirch Graffenstaden".equals(lineName)){
            lineID = "A";
        }
        else if ("Lingolsheim Tiergaertel - Hoenheim Gare".equals(lineName)) {
            lineID = "B";
        }
        else if ("Gare Centrale - Neuhof Rodolphe Reuss".equals(lineName)) {
            lineID = "C";
        }
        else if ("Poteries - Port du Rhin / Kehl Rathaus".equals(lineName)) {
            lineID = "D";
        }
        else if ("Robertsau l'Escale - Campus d'Illkirch".equals(lineName)) {
            lineID = "E";
        }
        else if ("Comtes - Place d'Islande".equals(lineName)) {
            lineID = "F";
        }
        else{
            lineID = "";
        }

        setContentView(R.layout.fragment_schedule);

        ArrayList<Stop> stops = new ArrayList<Stop>();

        setTitle("Ligne " + lineID);

        /*Intent intentTest = new Intent(StartActivity.this, ScheduleActivity.class);
        StartActivity.this.startActivity(intentTest);*/

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);

        ObservableExtensionKt.observe(model.lineWithEstimatedTimeTable(RouteType.TRAM, lineID , 0), new ObservableListener<Line>() {
            @Override
            public void onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
            }

            @Override
            public void onSuccess(Line data) {

                ArrayList<Stop> stops = new ArrayList<Stop>();

                for( Stop stop : data.getStops() ) {
                    stops.add(stop);
                    //ScheduleRecyclerView.setAdapter(ScheduleAdapter);
                }


                ScheduleAdapter = new ScheduleRecyclerViewAdapter(stops, lineID);
                ScheduleRecyclerView.setLayoutManager(ScheduleLayoutManager);
                ScheduleRecyclerView.setAdapter(ScheduleAdapter);
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                //call if the network call has responded with an error
            }
        });

        ScheduleRecyclerView = findViewById(R.id.cardListSchedule);
        ScheduleRecyclerView.setHasFixedSize(true);
        ScheduleLayoutManager = new LinearLayoutManager(this);

    }
}