package com.shindra;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
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

public class ActivityNosTrams extends AppCompatActivity
{
    private ArrayList<Line> ligneTram;  //Liste contenant les lignes de trams
    private String NomPage = "Nos Trams";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horaire_tram);


        setTitle(NomPage);  //Ecriture du titre de la vue

        //RecyclerView, ligne de tram
        RecyclerView ListeLigneTram = findViewById(R.id.RecyclerView_Ligne_Tram);   //Referencement vers la recyclerview "NosTrams"
        ListeLigneTram.setLayoutManager(new LinearLayoutManager(this));

        // callback de detection du bouton appuyé
        TramsViewHolder.RecyclerHoraireClick callBack = new TramsViewHolder.RecyclerHoraireClick()
        {
            @Override
            public void onHoraireLineClick(Line ligne)
            {
                Log.i("Main",ligne.getName());
                //Toast.makeText(getApplicationContext(), ligne.getName(), Toast.LENGTH_SHORT).show();
                //Creation de la nouvelle Intent
                //Intent intent = new Intent(this, ActivityHoraire.this);

                //startActivity(intent);
            }
        };

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>()
        {
            @Override
            public void onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
            }

            @Override
            public void onSuccess(ArrayList<Line> data) {
                //call once the network call has responded with a success

                //Remplissage dynamique des tableaux des lignes de trams
                ligneTram = new ArrayList<Line>();
                for (Line ligne : data)
                {
                    if(ligne.getRouteType() == RouteType.TRAM)
                    {
                        ligneTram.add(ligne);
                    }
                }
                ListeLigneTram.setAdapter(new RecyclerViewAdapterLigneTram(ligneTram, callBack));
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                //call if the network call has responded with an error
            }
        });
    }
}

