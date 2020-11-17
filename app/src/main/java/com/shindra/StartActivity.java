package com.shindra;

import android.content.Intent;
import android.os.Bundle;

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

    private RecyclerView LinesRecyclerView;
    private RecyclerView.Adapter LinesAdapter;
    private RecyclerView.LayoutManager LinesLayoutManager;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        setContentView(R.layout.fragment_lines);

        ArrayList<Line> lines = new ArrayList<Line>();

        setTitle("Nos Trams");

        LinesRecyclerView = findViewById(R.id.cardListLines);
        LinesRecyclerView.setHasFixedSize(true);
        LinesLayoutManager = new LinearLayoutManager(this);
        //LinesAdapter = new LinesRecyclerViewAdapter(lines);
        LinesRecyclerView.setLayoutManager(LinesLayoutManager);
        LinesRecyclerView.setAdapter(LinesAdapter);

        Intent intent = new Intent(StartActivity.this, ScheduleActivity.class);


        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);


        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>() {
            @Override
            public void onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
            }

            @Override
            public void onSuccess(ArrayList<Line> data) {
                //call once the network call has responded with a success

                for (Line line : data) {
                    if (line.getRouteType() == RouteType.TRAM) {
                        lines.add(line);
                    }
                }


               LinesRecyclerView.setAdapter(new LinesRecyclerViewAdapter(lines, new RecyclerItemClick() {
                    @Override
                    public void onScheduleClick(Line line) {
                        System.out.println(line.getName());
                        intent.putExtra("LINE", line.getName());
                        startActivity(intent);
                    }
                }));

            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                //call if the network call has responded with an error
            }
        });


    }



}

