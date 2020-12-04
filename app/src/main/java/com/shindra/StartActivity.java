package com.shindra;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class StartActivity extends AppCompatActivity {


    private RecyclerView recyclerView;

    private ArrayList<Line> lineArrayList;
    public static String lineClicked;


    protected void onStart() {
        super.onStart();

        TramCard.RecyclerItemClick callback = new TramCard.RecyclerItemClick()
        {
            @Override
            public void onHoraireButtonClick(Line line) {

                Intent intent = new Intent(StartActivity.this, HoraireActivity.class);
                intent.putExtra("line", line.getName());
                lineClicked = line.getName();

                startActivity(intent);

            }
        };


        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>() {
            @Override
            public void onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
            }

            @Override
            public void onSuccess(ArrayList<Line> data) {
                //call once the network call has responded with a success

                lineArrayList = new ArrayList<Line>();

                for(Line line:data){
                    if(line.getRouteType() == RouteType.TRAM){
                        lineArrayList.add(line);
                    }

                }
                recyclerView.setAdapter(new TramCardAdapter(lineArrayList, callback));


            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                //call if the network call has responded with an error
            }
        });
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle(getString(R.string.home_title));

        recyclerView = findViewById(R.id.appCts_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

}

