package com.shindra;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

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

     ArrayList<Line> linesTram;

    RecyclerView recyclerViewLineTram;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_container);
        setTitle(R.string.menu_name);

        LoadingDialog loadingDialog = new LoadingDialog(StartActivity.this);
        loadingDialog.startAnimation();




        //RecyclerView
        recyclerViewLineTram = findViewById(R.id.recyclerViewTram);
        recyclerViewLineTram.setLayoutManager(new LinearLayoutManager(this));


        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);


        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>() {

            @Override
            public void onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
            }

            @Override
            public void onSuccess(ArrayList<Line> data) {
                //call once the network call has responded with a success
                linesTram = new ArrayList<Line>();

                for (Line oneLine : data)
                    if (oneLine.getRouteType().name() == "TRAM") {
                        //Log.d("MSG", data.get(i).getName());
                        linesTram.add(oneLine);
                    }

                recyclerViewLineTram.setAdapter(new RecyclerMenuAdapter(linesTram, line ->  {
                    //setTitle("Click");
                    Intent scheduleActivity = new Intent(StartActivity.this, ScheduleActivity.class);
                    scheduleActivity.putExtra("lineNameSelected", line.getName());
                    startActivity(scheduleActivity);
                }));

                Handler handlerLoadingView = new Handler();
                handlerLoadingView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        loadingDialog.dismissDialog();
                    }
                }, 500);
            }
            @Override
            public void onError(@NotNull Throwable throwable) {
                //call if the network call has responded with an error
                loadingDialog.dismissDialog();
            }
        });
    }
}



