package com.shindra;

import android.os.Bundle;
import android.view.View;

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

public class StartActivity extends AppCompatActivity
{

    private ArrayList<Line> tram_lines;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Nos Trams");

        RecyclerView ListeLigneTram = findViewById(R.id.RecyclerView_Ligne_Tram);
        ListeLigneTram.setLayoutManager(new LinearLayoutManager(this));
        RecyclerView.Adapter LinesAdapter = new RecyclerViewAdapter_Tram_lines(tram_lines);


        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>()
        {
            @Override
            public void onLoading() {
            }

            @Override
            public void onSuccess(ArrayList<Line> data) {
                tram_lines = new ArrayList<Line>();
                for (Line ligne : data)
                {
                    if(ligne.getRouteType() == RouteType.TRAM)
                    {
                        tram_lines.add(ligne);
                    }

                }
                ListeLigneTram.setAdapter(new RecyclerViewAdapter_Tram_lines(tram_lines));
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
            }
        });
    }

}


