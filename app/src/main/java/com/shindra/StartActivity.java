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
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class StartActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        setContentView(R.layout.fragment_tram);


        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);


        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>() {
            @Override
            public void onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
            }

            @Override
            public void onSuccess(ArrayList<Line> data) {
                data.size();//call once the network call has responded with a success
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                //call if the network call has responded with an error
            }
        });

        mRecyclerView = findViewById(R.id.cardList);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new RecyclerViewAdapter(getListOfTramLines());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    private ArrayList<Line> getListOfTramLines(){
        ArrayList<Line> lines = new ArrayList<Line>();

        lines.add(new Line("Tram A", RouteType.TRAM, null));
        lines.add(new Line("Tram B", RouteType.TRAM, null));
        lines.add(new Line("Tram C", RouteType.TRAM, null));


        return lines;
    }
}

