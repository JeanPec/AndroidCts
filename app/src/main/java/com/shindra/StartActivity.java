package com.shindra;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

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
import java.util.List;

public class StartActivity extends AppCompatActivity {

    private ArrayList<Line> nosTramsList; //Initialization of an array containing "NosTramsItem" object
    //Ajout 18/11/2020
    private String NomVue;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager; //Responsible for the alignment of the items of the list

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onStart() {
        super.onStart();

        setContentView(R.layout.activity_main);

        getSupportActionBar().setTitle(getString(R.string.LabelNosTrams)); //setTitle(NomVue); //Change the name of the view

        RecyclerView nosTramList = findViewById(R.id.recyclerView);
        //recyclerView.setHasFixedSize(true);
        nosTramList.setLayoutManager (new LinearLayoutManager (this));
        RecyclerView.Adapter LinesAdapter = new NosTramsAdapter(nosTramsList);
        adapter = LinesAdapter;

        nosTramList.setAdapter (adapter);

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>() {

            @Override
            public void onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
            }

            @Override
            public void onSuccess(ArrayList<Line> data) {
                //call once the network call has responded with a success

                nosTramsList =  new ArrayList<Line>();//Crée une liste contenant des objets de type Line
                //nosTramList=data.filter
                for (Line LignesCTS : data)
                {

                    if (LignesCTS.getRouteType() == RouteType.TRAM)
                    {
                        nosTramsList.add(LignesCTS);
                    }
                    Log.i("NosTram","Recupération des Lignes terminées");
                }

                nosTramList.setAdapter(new NosTramsAdapter(nosTramsList)); //Création de la vue Nos Trams avec l'ensemble des trams de la liste

            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                //call if the network call has responded with an error
            }
        });

    }
}

