package com.shindra.Horaire;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.shindra.MyViewModel;
import com.shindra.R;
import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class ActivityHoraire extends AppCompatActivity
{

    private String NomPage = "Horaire";  //this.getString(R.string.page_Horaires);

    private ArrayList<Stop> ListeArretTram;  //Liste contenant les Arrets d'une ligne de tram
    private String LettreLigne;

    private AlertDialog CircularProgressBar;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horaire_tram);

        getSupportActionBar().setTitle(getString(R.string.page_Horaires)); //Ecriture du titre de la vue

        //Recuperation de la ligne de tram de l'activite precedente
        Intent intentNosTramLigne = getIntent();
        LettreLigne = intentNosTramLigne.getStringExtra("LigneTram");

        //Fragment, horaire de tram
        FragmentHoraireTram FragHoraireTram = new FragmentHoraireTram();
        FragmentTransaction fragTran = getSupportFragmentManager().beginTransaction();
        fragTran.add(R.id.fragmentHoraire,  FragHoraireTram);
        fragTran.commit();

        //Alert dialog, chargement de vue
        AlertDialog.Builder CreateProgressBar = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        CreateProgressBar.setView(inflater.inflate(R.layout.loading_view, null));
        CircularProgressBar = CreateProgressBar.create();

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lineWithEstimatedTimeTable(RouteType.TRAM, LettreLigne, 0), new ObservableListener<Line>()
        {
            @Override
            public void onLoading()
            {
                //call once we started the network called. Indicate that the network call is in progress
                Log.i(NomPage,"Chargement de la page Horaire");

                //Affiche la ProgressBar
                CircularProgressBar.show();
            }

            @Override
            public void onSuccess(Line data)
            {
                //call once the network call has responded with a success
                Log.i(NomPage,"Success reception des données");

                //Remplissage dynamique des tableaux des lignes de trams
                ListeArretTram = new ArrayList<Stop>();
                for (Stop listStop : data.getStops())
                {
                    if (listStop.getEstimatedDepartureTime() != null)
                    {
                        ListeArretTram.add(listStop);
                    }
                                    }
                FragHoraireTram.setParamFragment(ListeArretTram,LettreLigne);


                //Enleve l'affichage de la ProgressBar
                CircularProgressBar.dismiss();

            }

            @Override
            public void onError(@NotNull Throwable throwable)
            {
                //call if the network call has responded with an error
                Log.e(NomPage,"Erreur Application, Vue: Horaire");
            }
        });

    }
}
