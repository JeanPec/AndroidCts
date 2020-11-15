package com.shindra;

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

        setContentView(R.layout.fragment_tram);

        ArrayList<Line> lines = new ArrayList<Line>();

        setTitle("Nos Trams");

        LinesRecyclerView = findViewById(R.id.cardList);
        LinesRecyclerView.setHasFixedSize(true);
        LinesLayoutManager = new LinearLayoutManager(this);
        //mAdapter = new RecyclerViewAdapter(getListOfTramLines());
        LinesAdapter = new LinesRecyclerViewAdapter(lines);
        LinesRecyclerView.setLayoutManager(LinesLayoutManager);
        //mRecyclerView.setAdapter(mAdapter);




        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);


        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>() {
            @Override
            public void onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
            }

            @Override
            public void onSuccess(ArrayList<Line> data) {
                data.size();//call once the network call has responded with a success

                ArrayList<String> names = new ArrayList<String>();
                for( Line line : data ) {
                    if (line.getRouteType() == RouteType.TRAM){
                        lines.add(line);
                        names.add(line.getName());
                        //System.out.println(line.getName());
                        LinesRecyclerView.setAdapter(LinesAdapter);
                    }
                }
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                //call if the network call has responded with an error
            }
        });


    }

    /*private ArrayList<Line> getListOfTramLines(){
        ArrayList<Line> lines = new ArrayList<Line>();

        lines.add(new Line("Tram A", RouteType.TRAM, null));
        lines.add(new Line("Tram B", RouteType.TRAM, null));
        lines.add(new Line("Tram C", RouteType.TRAM, null));


        return lines;
    }*/
}

