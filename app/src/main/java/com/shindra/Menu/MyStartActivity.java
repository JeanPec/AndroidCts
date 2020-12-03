package com.shindra.Menu;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.Dialog.LoadingDialog;
import com.shindra.MyViewModel;
import com.shindra.R;
import com.shindra.Schedule.MyScheduleActivity;
import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MyStartActivity extends AppCompatActivity {

     ArrayList<Line> linesTram;

    RecyclerView recyclerViewLineTram;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu_container);
        setTitle(R.string.menu_name);

        LoadingDialog loadingDialog = new LoadingDialog(MyStartActivity.this);
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
                    if (oneLine.getRouteType() == RouteType.TRAM) {
                        //Log.d("MSG", data.get(i).getName());
                        linesTram.add(oneLine);
                    }

                recyclerViewLineTram.setAdapter(new MyRecyclerMenuAdapter(linesTram, line ->  {
                    //setTitle("Click");
                    Intent scheduleActivity = new Intent(MyStartActivity.this, MyScheduleActivity.class);
                    scheduleActivity.putExtra("lineNameSelected", line.getName());
                    startActivity(scheduleActivity);
                }));


                        loadingDialog.dismissDialog();

                    }

            @Override
            public void onError(@NotNull Throwable throwable) {
                //call if the network call has responded with an error
                loadingDialog.dismissDialog();
            }
        });
    }
}



