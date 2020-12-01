package com.shindra;

import android.os.Bundle;
import android.widget.TextView;

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

import io.reactivex.rxjava3.disposables.Disposable;

public class HoraireActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<Stop> stopArrayList;


    protected void onStart() {
        super.onStart();

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lineWithEstimatedTimeTable(RouteType.TRAM, "A", 0), new ObservableListener<Line>() {

            @Override
            public void onSuccess(Line data) {
                stopArrayList = new ArrayList<Stop>();

                for(Stop stop : data.getStops()){

                    if (stop.getEstimatedDepartureTime() != null){
                        stopArrayList.add(stop);
                    }
                }
                recyclerView.setAdapter(new HoraireAdapter(stopArrayList, "A"));
            }

            @Override
            public void onError(@NotNull Throwable throwable) {

            }


            @Override
            public void onLoading() {

            }
        });
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horaire);

        getSupportActionBar().setTitle(getString(R.string.horaire_title));

        recyclerView = findViewById(R.id.appCts_recyclerview);


        //TramCardAdapter tramCardAdapter = new TramCardAdapter(tramCardList, this);

        //recyclerView.setAdapter(tramCardAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));


    }
}