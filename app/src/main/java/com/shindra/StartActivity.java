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

public class StartActivity extends AppCompatActivity {

    private RecyclerView _recyclerView;
    private RecyclerView.Adapter _adapter;
    private RecyclerView.LayoutManager _layoutManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<tramCardview> listOfTrams = new ArrayList<>();
        listOfTrams.add(new tramCardview(R.drawable.tram_a, R.drawable.nouveau_tram_strasbourg));
        listOfTrams.add(new tramCardview(R.drawable.tram_b, R.drawable.nouveau_tram_strasbourg));
        listOfTrams.add(new tramCardview(R.drawable.tram_c, R.drawable.nouveau_tram_strasbourg));
        listOfTrams.add(new tramCardview(R.drawable.tram_d, R.drawable.nouveau_tram_strasbourg));
        listOfTrams.add(new tramCardview(R.drawable.tram_e, R.drawable.nouveau_tram_strasbourg));

        _recyclerView = findViewById(R.id.recycler_view_tram);
        _recyclerView.setHasFixedSize(true);
        _layoutManager = new LinearLayoutManager(this);
        _adapter = new tramAdapter(listOfTrams);

        _recyclerView.setLayoutManager(_layoutManager);
        _recyclerView.setAdapter(_adapter);

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);

        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>() {
            @Override
            public void onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
            }

            @Override
            public void onSuccess(ArrayList<Line> data) {
                //call once the network call has responded with a success
                getSupportActionBar().setTitle("Nos trams");
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                //call if the network call has responded with an error
            }
        });
    }
}

