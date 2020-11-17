package com.shindra.Ligne;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.Horaire.ActivityHoraire;
import com.shindra.MyViewModel;
import com.shindra.R;
import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ActivityNosTrams extends AppCompatActivity
{
    private ArrayList<Line> ligneTram;  //Liste contenant les lignes de trams
    private String NomPage = "Nos Trams";   //this.getString(R.string.page_NosTrams);

    private AlertDialog CircularProgressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ligne_tram);

        setTitle(NomPage);  //Ecriture du titre de la vue

        //RecyclerView, ligne de tram
        RecyclerView ListeLigneTram = findViewById(R.id.RecyclerView_Ligne_Tram);   //Referencement vers la recyclerview "NosTrams"
        ListeLigneTram.setLayoutManager(new LinearLayoutManager(this));

        //Alert dialog, chargement de vue
        AlertDialog.Builder CreateProgressBar = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        CreateProgressBar.setView(inflater.inflate(R.layout.loading_view, null));
        CircularProgressBar = CreateProgressBar.create();


        // callback de detection du bouton appuyé
        TramsViewHolder.RecyclerHoraireClick callBack = new TramsViewHolder.RecyclerHoraireClick()
        {
            @Override
            public void onHoraireLineClick(Line ligne)
            {
                Log.i(NomPage,"Appuie BTN : " + ligne.getName());
                //Creation de la nouvelle Intent
                Intent intent = new Intent(ActivityNosTrams.this, ActivityHoraire.class);
                intent.putExtra("LigneTram",ligne.getName());
                startActivity(intent);
            }
        };

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>()
        {
            @Override
            public void onLoading()
            {
                //call once we started the network called. Indicate that the network call is in progress
                Log.i(NomPage,"Chargement de la page Nos Trams");

                //Affiche la ProgressBar
                CircularProgressBar.show();
            }

            @Override
            public void onSuccess(ArrayList<Line> data)
            {
                //call once the network call has responded with a success
                Log.i(NomPage,"Recupération des Lignes de Trams");

                //Enleve l'affichage de la ProgressBar
                CircularProgressBar.dismiss();

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
                Log.i(NomPage,"Recupération des Lignes de Trams Validé");
            }

            @Override
            public void onError(@NotNull Throwable throwable)
            {
                //call if the network call has responded with an error
                Log.e(NomPage,"Erreur Application");
            }
        });
    }
}

