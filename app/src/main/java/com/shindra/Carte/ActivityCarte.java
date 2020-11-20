package com.shindra.Carte;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.shindra.R;

public class ActivityCarte extends AppCompatActivity
{
    private String NomPage;
    private String LettreLigne;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //Configuration de l'activité
        setContentView(R.layout.activity_carte_ligne_tram);

        //Recuperation de la ligne de tram de l'activite precedente
        Intent intentNosTramLigne = getIntent();
        LettreLigne = intentNosTramLigne.getStringExtra("LettreLigneTram");

        //Ecriture du titre de la vue
        NomPage = getString(R.string.Txt_page_Carte);
        getSupportActionBar().setTitle(NomPage + " " + LettreLigne); //Ecriture du titre de la vue

        //Configuration du Fragment, Carte des arrets
        Bundle BundleCarte = new Bundle();
        BundleCarte.putString("LettreLigneTram", LettreLigne);
        FragmentCarte FragCarteTram = new FragmentCarte();
        FragmentTransaction fragTran = getSupportFragmentManager().beginTransaction();
        fragTran.add(R.id.fragmentCarte, FragCarteTram.newInstance(LettreLigne));
        fragTran.commit();
    }
}
