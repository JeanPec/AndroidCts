
package com.shindra.Horaire;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.shindra.R;


public class ActivityHoraire extends AppCompatActivity
{
    private String LettreLigne;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        //Configuration de l'activité
        setContentView(R.layout.activity_horaire_tram);

        //Ecriture du titre de la vue
        getSupportActionBar().setTitle(getString(R.string.Txt_page_Horaires));

        //Recuperation de la ligne de tram de l'activite précedente
        Intent intentNosTramLigne = getIntent();
        LettreLigne = intentNosTramLigne.getStringExtra("LigneTram");

        //Configuration du Fragment, horaire de tram
        FragmentHoraireTram FragHoraireTram = new FragmentHoraireTram();
        FragmentTransaction fragTran = getSupportFragmentManager().beginTransaction();
        fragTran.add(R.id.fragmentHoraire, FragHoraireTram.newInstance(LettreLigne));
        fragTran.commit();
    }
}