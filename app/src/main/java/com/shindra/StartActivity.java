package com.shindra;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.Coordinate;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;

import org.jetbrains.annotations.NotNull;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class StartActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);

        // Uncomment one of those line to test each recycler views
        //setLineRecyclerView(R.id.line_views);
        //setStopRecyclerView(R.id.line_views);

        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>()
        {
            @Override
            public void onLoading()
            {
                //call once we started the network called. Indicate that the network call is in progress
            }

            @Override
            public void onSuccess(ArrayList<Line> data)
            {
                //call once the network call has responded with a success
            }

            @Override
            public void onError(@NotNull Throwable throwable)
            {
                //call if the network call has responded with an error
            }
        });
    }

    private void setLineRecyclerView(int targetRecyclerId)
    {
        RecyclerView lineViews = findViewById(targetRecyclerId);
        lineViews.setLayoutManager(new LinearLayoutManager(this));
        lineViews.setAdapter(new LineAdapter(getDummyLines()));
    }

    private void setStopRecyclerView(int targetRecyclerId)
    {
        RecyclerView stopViews = findViewById(targetRecyclerId);
        stopViews.setLayoutManager(new LinearLayoutManager(this));
        stopViews.setAdapter(new StopAdapter(getDummyStops(), "Ligne A"));
    }

    private ArrayList<Line>  getDummyLines()
    {
        ArrayList<Line> temp = new ArrayList<Line>();
        temp.add(new Line("Ligne A", RouteType.TRAM, getDummyStops()));
        temp.add(new Line("Ligne B", RouteType.TRAM, getDummyStops()));
        temp.add(new Line("Ligne C", RouteType.TRAM, getDummyStops()));
        temp.add(new Line("Ligne D", RouteType.TRAM, getDummyStops()));
        temp.add(new Line("Ligne E", RouteType.TRAM, getDummyStops()));
        temp.add(new Line("Ligne F", RouteType.TRAM, getDummyStops()));
        return temp;
    }

    private ArrayList<Stop> getDummyStops()
    {
        Date currentDate = new Date(System.currentTimeMillis());
        ArrayList<Stop> temp = new ArrayList<Stop>();
        temp.add(new Stop("Parc des Sports", currentDate, currentDate, "Parc des Sports", new Coordinate()));
        temp.add(new Stop("Le Galet", currentDate, currentDate, "Parc des Sports", new Coordinate()));
        temp.add(new Stop("Cervantès", currentDate, currentDate, "Parc des Sports", new Coordinate()));
        temp.add(new Stop("Dante", currentDate, currentDate, "Parc des Sports", new Coordinate()));
        return temp;
    }
}

