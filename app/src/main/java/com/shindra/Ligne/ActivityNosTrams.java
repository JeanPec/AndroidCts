package com.shindra.Ligne;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.Horaire.ActivityHoraire;
import com.shindra.MyViewModel;
import com.shindra.R;
import com.shindra.Utilites.DialogDeChargement;
import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ActivityNosTrams extends AppCompatActivity
{
    private ArrayList<Line> ligneTram;  //Liste contenant les lignes de trams

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //Configuration de l'activité
        setContentView(R.layout.activity_ligne_tram);

        //Ecriture du titre de la vue
        getSupportActionBar().setTitle(getString(R.string.Txt_page_NosTrams));

        //Configuration de la RecyclerView, ligne de tram
        RecyclerView ListeLigneTram = findViewById(R.id.RecyclerView_Ligne_Tram);   //Referencement vers la recyclerview "NosTrams"
        ListeLigneTram.setLayoutManager(new LinearLayoutManager(this));

        //Alert dialog, Creation de la ProgressBar de chargement
        DialogDeChargement dialogDeChargement = new DialogDeChargement(ActivityNosTrams.this);

        // Callback de detection du bouton appuyé
        TramsViewHolder.RecyclerHoraireClick callBack = new TramsViewHolder.RecyclerHoraireClick()
        {
            @Override
            public void onHoraireLineClick(Line ligne)
            {
                Log.i(getString(R.string.Txt_page_NosTrams),"Appuie BTN : " + ligne.getName());
                //Creation de la nouvelle Intent
                Intent intent = new Intent(ActivityNosTrams.this, ActivityHoraire.class);
                intent.putExtra("LigneTram",ligne.getName());
                startActivity(intent);
            }
        };

        //Realisation de l'appel réseau
        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>()
        {
            @Override
            public void onLoading()
            {
                //call once we started the network called. Indicate that the network call is in progress
                Log.i(getString(R.string.Txt_page_NosTrams),"Chargement de la page Nos Trams");

                //Affiche la ProgressBar
                dialogDeChargement.AfficherPageChargement();
            }

            @Override
            public void onSuccess(ArrayList<Line> data)
            {
                //call once the network call has responded with a success
                Log.i(getString(R.string.Txt_page_NosTrams),"Recupération des Lignes de Trams");

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

                Log.i(getString(R.string.Txt_page_NosTrams),"Recupération des Lignes de Trams Validé");

                //Enleve l'affichage de la ProgressBar
                dialogDeChargement.EnleverPageChargement();
            }

            @Override
            public void onError(@NotNull Throwable throwable)
            {
                //call if the network call has responded with an error
                Log.e(getString(R.string.Txt_page_NosTrams),"Erreur Application");
            }
        });
    }
}

