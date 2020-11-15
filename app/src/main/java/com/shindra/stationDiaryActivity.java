package com.shindra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Adapter;

import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.bo.Line;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class stationDiaryActivity extends AppCompatActivity {

    private RecyclerView _recyclerView;
    private RecyclerView.Adapter _adapter;
    private RecyclerView.LayoutManager _layoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_diary);

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);

        ArrayList<stationDiaryCardview> listOfStations = new ArrayList<>();
        listOfStations.add(new stationDiaryCardview("Parc Marlaux","Ligne A", "18h35"));
        listOfStations.add(new stationDiaryCardview("Batzendorf","Ligne B", "19h35"));
        listOfStations.add(new stationDiaryCardview("Haguenau","Ligne A", "16h35"));
        listOfStations.add(new stationDiaryCardview("Langstross","Ligne A", "11h35"));
        listOfStations.add(new stationDiaryCardview("Krimeri/Meinau","Ligne A", "23h35"));
        listOfStations.add(new stationDiaryCardview("Paris","Ligne A", "10h35"));
        listOfStations.add(new stationDiaryCardview("New York","Ligne A", "1h35"));
        listOfStations.add(new stationDiaryCardview("Chez Donald Trump","Ligne A", "8h35"));
        listOfStations.add(new stationDiaryCardview("Homme de fer","Ligne A", "12h35"));
        listOfStations.add(new stationDiaryCardview("Champs-Elysees","Ligne A", "18h55"));
        listOfStations.add(new stationDiaryCardview("Parc de l'horloge","Ligne A", "18h15"));

        _recyclerView = findViewById(R.id.recycler_view_diary_stations);
        _recyclerView.setHasFixedSize(true);
        _layoutManager = new LinearLayoutManager(this);
        _adapter = new stationDiaryAdapter(listOfStations);

        _recyclerView.setLayoutManager(_layoutManager);
        _recyclerView.setAdapter(_adapter);

        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>() {
            @Override
            public void onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
            }

            @Override
            public void onSuccess(ArrayList<Line> data) {
                //call once the network call has responded with a success
                getSupportActionBar().setTitle("Horaire");
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                //call if the network call has responded with an error
            }
        });
    }
}