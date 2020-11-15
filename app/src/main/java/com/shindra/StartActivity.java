package com.shindra;

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
    private static final String TAG = "mainActivity";

    private ArrayList<Line> availableTramlines;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.rvTramLines);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        TramlineViewHolder.RecyclerItemClick callback = new TramlineViewHolder.RecyclerItemClick() {
            @Override
            public void OnTramlineClick(Line l) {
                Log.i(TAG, "Clicked on the line with name : "+l.getName());
            }
        };

        //Call api for available lines
        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>() {
            @Override
            public void onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
                Log.i(TAG, "Waiting for answer...");
            }

            @Override
            public void onSuccess(ArrayList<Line> receivedData) {
                //call once the network call has responded with a success
                Log.i(TAG, "Received data");

                availableTramlines = new ArrayList<>();
                for(Line l : receivedData) {
                    if(l.getRouteType() == RouteType.TRAM)
                        availableTramlines.add(l);
                }
                recyclerView.setAdapter(new TramlinesRecyclerViewAdapter(availableTramlines, callback));
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                //call if the network call has responded with an error
                Log.e(TAG, "API error");
            }
        });
    }
}

