package com.shindra;

import android.os.Bundle;

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
import java.util.List;

public class StartActivity extends AppCompatActivity {


    RecyclerView recyclerViewTram;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Create a List of Lines
        List<TramLine> Lines = new ArrayList<TramLine>();
        //Add All Lines
        Lines.add(new TramLine("A",R.drawable.tram_a,R.drawable.nouveau_tram_strasbourg));
        Lines.add(new TramLine("B",R.drawable.tram_b,R.drawable.nouveau_tram_strasbourg));
        Lines.add(new TramLine("C",R.drawable.tram_c,R.drawable.nouveau_tram_strasbourg));
        Lines.add(new TramLine("D",R.drawable.tram_d,R.drawable.nouveau_tram_strasbourg));
        Lines.add(new TramLine("E",R.drawable.tram_e,R.drawable.nouveau_tram_strasbourg));
        Lines.add(new TramLine("F",R.drawable.tram_f,R.drawable.nouveau_tram_strasbourg));

        recyclerMenuAdapter CardAdapter = new recyclerMenuAdapter(this, Lines);

        recyclerViewTram = findViewById(R.id.recyclerViewTram);
        recyclerViewTram.setAdapter(CardAdapter);
        //Set the Layout Manager of the RecyclerView
        recyclerViewTram.setLayoutManager(new LinearLayoutManager(this));

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



