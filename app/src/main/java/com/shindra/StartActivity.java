package com.shindra;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
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
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(getString(R.string.tramLinesActivityName));

        loadingWindow window = new loadingWindow(this);

        RecyclerView recyclerView = findViewById(R.id.recycler_view_tram);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>() {
            @Override
            public void onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
                window.displayLoadingWindow();
            }

            @Override
            public void onSuccess(ArrayList<Line> data) {
                //call once the network call has responded with a success
                ArrayList<Line> listOfLines = new ArrayList<Line>();

                window.dismissLoadingWindow();

                for (Line lineData : data ) {
                    if(lineData.getRouteType() == RouteType.TRAM)
                    {
                       listOfLines.add(lineData);
                    }
                }

                RecyclerView.Adapter _adapter = new StartActivityAdapter(listOfLines, new StartActivityAdapter.RecyclerItemClick() {
                    @Override
                    public void onDiaryButtonClick(Line tram) {
                        Intent intent = new Intent(StartActivity.this, stationActivity.class);
                        intent.putExtra("TRAM_LINE",tram.getName());
                        startActivity(intent);
                    }
                });

                recyclerView.setAdapter(_adapter);
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                //call if the network call has responded with an error
            }
        });

    }
}

