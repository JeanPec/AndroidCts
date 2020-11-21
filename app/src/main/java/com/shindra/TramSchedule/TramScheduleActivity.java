package com.shindra.TramSchedule;


import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.shindra.R;


public class TramScheduleActivity extends AppCompatActivity
{
    private String Line_Letter;

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.tram_schedules_view);

        getSupportActionBar().setTitle(getString(R.string.horaire_label));

        //Recuperation de la ligne de tram de l'activite précedente
        Intent intentTram_Lines = getIntent();
        Line_Letter = intentTram_Lines.getStringExtra("Tram_Line");

        //Configuration du Fragment, horaire de tram
        Bundle BundleHoraire = new Bundle();
        BundleHoraire.putString("Line_Letter", Line_Letter);
        TramScheduleFrag TramScheduleFrag = new TramScheduleFrag();
        FragmentTransaction fragTran = getSupportFragmentManager().beginTransaction();
        fragTran.add(R.id.ScheduleLayout, TramScheduleFrag.newInstance(Line_Letter));
        fragTran.commit();
    }


}


