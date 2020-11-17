package com.shindra;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class StartActivity extends AppCompatActivity {

    private RecyclerView _recyclerView;
    private RecyclerView.Adapter _adapter;
    private RecyclerView.LayoutManager _layoutManager;

    public ArrayList<Line> listOfLines;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Nos trams");

        _recyclerView = findViewById(R.id.recycler_view_tram);
        _recyclerView.setHasFixedSize(true);
        _layoutManager = new LinearLayoutManager(this);
        _recyclerView.setLayoutManager(_layoutManager);

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>() {
            @Override
            public void onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
            }

            @Override
            public void onSuccess(ArrayList<Line> data) {
                //call once the network call has responded with a success
                listOfLines = new ArrayList<Line>();
                for (Line lineData : data ) {
                    if(lineData.getRouteType() == RouteType.TRAM)
                    {
                       listOfLines.add(lineData);
                        Log.i("Main",lineData.getName());
                    }
                }

                _adapter = new tramAdapter(listOfLines, new tramAdapter.RecyclerItemClick() {
                    @Override
                    public void onDiaryButtonClick(Line tram) {
                        Intent intent = new Intent(StartActivity.this, stationDiaryActivity.class);
                        intent.putExtra("TRAM_LINE",getLineLetterFrom(tram));
                        startActivity(intent);
                    }
                });
                Log.i("Main","Create tramAdapter");

                _recyclerView.setAdapter(_adapter);
                Log.i("Main","set tramAdapterOK");
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                //call if the network call has responded with an error
                Log.e("Main", "ATTENTION" + throwable.getMessage());
            }
        });

    }

    public String getLineLetterFrom(Line tramLine){
        switch(tramLine.getName())
        {
            case "Parc des Sports - Illkirch Graffenstaden":
                return "A";
            case "Lingolsheim Tiergaertel - Hoenheim Gare":
                return "B";
            case "Gare Centrale - Neuhof Rodolphe Reuss":
                return "C";
            case "Poteries - Port du Rhin / Kehl Rathaus":
                return "D";
            case "Robertsau l'Escale - Campus d'Illkirch":
                return "E";
            case "Comtes - Place d'Islande":
                return "F";
            default:
                return "G";
        }
    }
}

