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
import com.shindra.ctslibrary.bo.Line;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class StartActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private TramCardAdapter tramCardAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // setContentView(R.layout.fragment_first);

        setTitle("Nos trams");


        List<TramCard> tramCardList = new ArrayList<TramCard>();
        tramCardList.add(new TramCard("Ligne A"));
        tramCardList.add(new TramCard("Ligne B"));
        tramCardList.add(new TramCard("Ligne C"));
        tramCardList.add(new TramCard("Ligne D"));
        tramCardList.add(new TramCard("Ligne E"));
        tramCardList.add(new TramCard("Ligne F"));
        tramCardList.add(new TramCard("Ligne G"));
        tramCardList.add(new TramCard("Ligne H"));

        recyclerView = findViewById(R.id.appCts_recyclerview);
        tramCardAdapter = new TramCardAdapter(tramCardList);
        recyclerView.setAdapter(tramCardAdapter);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);




        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>() {
            @Override
            public void onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
            }

            @Override
            public void onSuccess(ArrayList<Line> data) {
                //call once the network call has responded with a success
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                //call if the network call has responded with an error
            }
        });
    }
}

