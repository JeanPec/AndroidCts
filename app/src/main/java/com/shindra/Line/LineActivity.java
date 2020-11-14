package com.shindra.Line;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.Line.ILineClickable;
import com.shindra.Line.LineAdapter;
import com.shindra.MyViewModel;
import com.shindra.R;
import com.shindra.StopAdapter;
import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.Coordinate;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;

public class LineActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.line_activity);

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
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
                getSupportActionBar().setTitle(R.string.lines);
                
                RecyclerView lines = findViewById(R.id.lines);
                lines.setLayoutManager(new LinearLayoutManager(getParent()));
                lines.setAdapter(new LineAdapter(getDummyLines(), new ILineClickable() {
                    @Override
                    public void OnLineClick(Line line)
                    {
                    }
                }));
            }

            @Override
            public void onError(@NotNull Throwable throwable)
            {
                //call if the network call has responded with an error
            }
        });
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

