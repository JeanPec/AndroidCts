package com.shindra;

import android.content.Intent;
import android.os.Bundle;

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
import java.util.Objects;

public class HorairesActivity extends AppCompatActivity {

    //On crée la recycler view qui va afficher les  ainsi que son adapter
    RecyclerView RecyclerViewHoraires;
    RecyclerView.Adapter RecyclerViewHorairesAdapter;

    //On crée une liste qui va contenir les arrêts
    ArrayList<Stop> ListeArrets = new ArrayList<>();

    //On récupère le nom de la ligne via l'Intent
    Intent Intent = getIntent();
    String NomLigne = Intent.getStringExtra("LINE");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horaires);
        setTitle("Horaire");

        //On met en place la RecyclerView
        RecyclerViewHoraires = findViewById(R.id.ListeHoraires);
        RecyclerViewHoraires.setHasFixedSize(true);
        RecyclerView.LayoutManager LayoutManagerHoraire = new LinearLayoutManager(this);
        RecyclerViewHoraires.setLayoutManager(LayoutManagerHoraire);

        //On crée un view model pour cette activité
        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);

        ObservableExtensionKt.observe(model.lineWithEstimatedTimeTable(RouteType.TRAM, NomLigne, 0), new ObservableListener<Line>() {

            @Override
            public void onLoading() {
            }

            @Override
            public void onSuccess(Line Ligne) {
                //On ajoute les arrêts dans la liste
                ListeArrets.addAll(Objects.requireNonNull(Ligne.getStops()));
                RecyclerViewHoraires.setAdapter(RecyclerViewHorairesAdapter);
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
            }
        });

        //On définit l'adapter comme celui de notre RecyclerView
        RecyclerViewHorairesAdapter = new RecyclerViewHorairesAdapter(ListeArrets, NomLigne);
        RecyclerViewHoraires.setAdapter(RecyclerViewHorairesAdapter);
    }
}




