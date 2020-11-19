package com.shindra;

import android.os.Bundle;
import android.widget.Button;

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

    //Ajout 18/11/2020
    private String NomVue = "Nos Trams";    // Insert the name of the view

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager; //Responsible for the alignment of the items of the list

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ajout 18/11/2020
        setTitle(NomVue); //Change the name of the view

        ArrayList<NosTramsItem> nosTramsList = new ArrayList<>(); //Initialization of an array containing "NosTramsItem" object
        //Manually adding object in the array
        nosTramsList.add(new NosTramsItem(R.drawable.tram_a, R.drawable.tram));
        nosTramsList.add(new NosTramsItem(R.drawable.tram_b,  R.drawable.tram));
        nosTramsList.add(new NosTramsItem(R.drawable.tram_c,  R.drawable.tram));
        nosTramsList.add(new NosTramsItem(R.drawable.tram_d,  R.drawable.tram));

        recyclerView = findViewById(R.id.recyclerView);
        //recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager (this);
        adapter = new NosTramsAdapter (nosTramsList);

        recyclerView.setLayoutManager (layoutManager);
        recyclerView.setAdapter (adapter);

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

