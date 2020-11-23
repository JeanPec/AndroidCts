package com.shindra;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class StartActivity extends AppCompatActivity {

    private ArrayList<Line> nosTramsList = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();

        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(getString(R.string.LabelNosTrams));
        NosTramsAdapter.RecyclerTimetableClick callBack = new NosTramsAdapter.RecyclerTimetableClick() {
            @Override
            public void onTimetableClick(Line image) {
                Log.i("Test","Bouton HOURRA");
                setContentView(R.layout.horaire_item);
            }
        };

        RecyclerView nosTramList = findViewById(R.id.recyclerView);
        nosTramList.setLayoutManager (new LinearLayoutManager (this));
        RecyclerView.Adapter LinesAdapter = new NosTramsAdapter(nosTramsList, callBack);

        nosTramList.setAdapter (LinesAdapter);

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>() {

            @Override
            public void onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
            }

            @Override
            public void onSuccess(ArrayList<Line> data) {
                //call once the network call has responded with a success
                for (Line LignesCTS : data)
                {
                    if (LignesCTS.getRouteType() == RouteType.TRAM)
                    {
                        nosTramsList.add(LignesCTS);
                    }
                    Log.i("NosTram","Recupération des Lignes terminées");
                }

                nosTramList.setAdapter(new NosTramsAdapter(nosTramsList, callBack));
            }
            @Override
            public void onError(@NotNull Throwable throwable) {
                //call if the network call has responded with an error
            }
        });

    }
}

