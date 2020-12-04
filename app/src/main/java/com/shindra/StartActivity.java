package com.shindra;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

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

    //On crée la RecyclerView qui va accueillir les lignes et son adapter
    private RecyclerView ListeLignesRecyclerView;
    private RecyclerView.Adapter ListeLignesRecylclerViewAdapter;

    //On crée une liste des lignes
    ArrayList<Line> ListeLignes = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Nos Trams");

        //On met en place la RecyclerView
        ListeLignesRecyclerView = findViewById(R.id.liste_lignes_recyclerview);
        ListeLignesRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager ListeLignesLayoutManager = new LinearLayoutManager(this);
        ListeLignesRecyclerView.setLayoutManager(ListeLignesLayoutManager);

        //On instancie l'Intent
        Intent IntentListeLignes = new Intent(StartActivity.this, HorairesActivity.class);

        //On crée un view model pour cette activité
        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);

        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>() {
            @Override
            public void onLoading() {
            }

            @Override
            public void onSuccess(ArrayList<Line> data) {

                //On ajoute chaque ligne dans la liste qu'on a crée plus haut
                for (Line ligne : data)
                {
                    if (ligne.getRouteType() == RouteType.TRAM)
                    {
                        ListeLignes.add(ligne);
                    }
                    ListeLignesRecyclerView.setAdapter(ListeLignesRecylclerViewAdapter);
                }
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
            }
        });

        //On instancie l'adapter de notre RecyclerView
        ListeLignesRecylclerViewAdapter = new RecyclerViewListeLignesAdapter(ListeLignes, Line -> {
            IntentListeLignes.putExtra("LINE", Line.getName());
            startActivity(IntentListeLignes);
        });

        //On définit l'adapter comme celui de notre RecyclerView
        ListeLignesRecyclerView.setAdapter(ListeLignesRecylclerViewAdapter);
    }
}

