package com.shindra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Adapter;

import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.EstimatedTimeTable;
import com.shindra.ctslibrary.apibo.EstimatedTimetableDelivery;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.apibo.ServiceDelivery;
import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;
import com.shindra.ctslibrary.mapper.LineStopEstimatedTimeMapper;

import org.jetbrains.annotations.NotNull;

public class stationDiaryActivity extends AppCompatActivity {

    private RecyclerView _recyclerView;
    private RecyclerView.Adapter _adapter;
    private RecyclerView.LayoutManager _layoutManager;
    private String _lineName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_diary);
        getSupportActionBar().setTitle("Horaire");

        _recyclerView = findViewById(R.id.recycler_view_stations);
        _recyclerView.setHasFixedSize(true);
        _layoutManager = new LinearLayoutManager(this);
        _recyclerView.setLayoutManager(_layoutManager);

        Intent intentFromStartActivity = getIntent();
        _lineName = intentFromStartActivity.getStringExtra("TRAM_LINE") ;

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lineWithEstimatedTimeTable(RouteType.TRAM,_lineName,0), new ObservableListener<Line>() {

            @Override
            public void onError(@NotNull Throwable throwable) {

            }

            @Override
            public void onSuccess(Line data) {
                _adapter = new stationDiaryAdapter(data);
                _recyclerView.setAdapter(_adapter);
            }

            @Override
            public void onLoading() {
                Log.i("Main","LOADING");
            }
        });
    }
}