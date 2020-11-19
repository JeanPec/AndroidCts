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


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tram);

        setTitle("Nos Trams");

        ArrayList<Line> TramList = new ArrayList<Line>();
        TramList.add(new Line("A", RouteType.TRAM, null));
        TramList.add(new Line("B", RouteType.TRAM, null));
        TramList.add(new Line("C", RouteType.TRAM, null));


        // set up the RecyclerView
        RecyclerView recyclerView = findViewById(R.id.CardViewList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        RecyclerViewAdapter adapter = new RecyclerViewAdapter(TramList);
        recyclerView.setAdapter(adapter);
        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);


        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>() {

            @Override
            public void onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
            }

            @Override
            public void onSuccess(ArrayList<Line> data) {
                //call once the network call has responded with a success
                // data to populate the RecyclerView with
                for (Line l : data) {
                    if (l.getRouteType() == RouteType.TRAM)
                    {
                        TramList.add(l);
                    }
                }
                recyclerView.setAdapter(new RecyclerViewAdapter(TramList));
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                //call if the network call has responded with an error
            }
        });
    }
}