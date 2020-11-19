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
import com.shindra.ctslibrary.bo.Line;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class StartActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    public ArrayList<Line> lines;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startactivity);

        /* Intent used to pass LINE_NAME to Stop activity */
        Intent intent = new Intent(this, StopActivity.class);

        /* RecyclerView used to print all subway's lines */
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        /* ArrayList containing subway's lines received with network */
        lines = new ArrayList<Line>();


        LineAdapter lineAdapter = new LineAdapter(lines, new LineClick(){

            @Override
            public void onLineClick(Line line) {
                String name = line.getName();
                intent.putExtra("LINE_NAME", name);
                startActivity(intent);
            }
        });

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);

        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>() {
            @Override
            public void onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
                Log.i("StartActiviy", "Connexion in progress");
            }

            @Override
            public void onSuccess(ArrayList<Line> data) {
                //call once the network call has responded with a success
                Log.i("StartActiviy", "Connexion established");
                for(Line item : data)
                {
                    if(item.getRouteType().toString() == "TRAM")
                        lines.add(item);
                }
                recyclerView.setAdapter(lineAdapter);
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                //call if the network call has responded with an error
                Log.i("StartActiviy", "Network Error !");
            }
        });
    }
/*
    public ArrayList<Line> getListOfLines(){

        ArrayList<Line> lines = new ArrayList<Line>();

        lines.add(new Line("A", RouteType.TRAM, null));
        lines.add(new Line("B", RouteType.TRAM, null));
        lines.add(new Line("C", RouteType.TRAM, null));
        lines.add(new Line("D", RouteType.TRAM, null));
        lines.add(new Line("E", RouteType.TRAM, null));
        lines.add(new Line("F", RouteType.TRAM, null));

        return lines;
    }

 */
}

