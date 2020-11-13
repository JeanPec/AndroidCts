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

import java.util.ArrayList;
import java.util.Date;

public class StartActivity extends AppCompatActivity
{
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);

        ArrayList<Line> test = new ArrayList<Line>();
        test.add(new Line("Ligne A", RouteType.BUS, null));
        test.add(new Line("Ligne B", RouteType.BUS, null));
        test.add(new Line("Ligne C", RouteType.BUS, null));
        test.add(new Line("Ligne D", RouteType.BUS, null));


        RecyclerView lineViews = findViewById(R.id.line_views);
        lineViews.setLayoutManager(new LinearLayoutManager(this));
        lineViews.setAdapter(new LineAdapter(test));

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
                int a = 5;
            }

            @Override
            public void onError(@NotNull Throwable throwable)
            {
                //call if the network call has responded with an error
            }
        });
    }
}

