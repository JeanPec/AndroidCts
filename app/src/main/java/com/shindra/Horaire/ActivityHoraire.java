package com.shindra.Horaire;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.shindra.R;

public class ActivityHoraire extends AppCompatActivity
{

    private String NomPage = "Horaire";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horaire_tram);

        setTitle(NomPage);  //Ecriture du titre de la vue

        //RecyclerView, horaire de tram
        FragmentTransaction fragTran = getSupportFragmentManager().beginTransaction();
        fragTran.add(R.id.fragmentHoraire, new FragmentHoraireTram());
        fragTran.commit();
    }

}
