
package com.shindra.Horaire;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.shindra.R;


public class ActivityHoraire extends AppCompatActivity
{

    private String NomPage = "Horaire";  //this.getString(R.string.page_Horaires);
    private String LettreLigne;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horaire_tram);

        //getSupportActionBar().setTitle(NomPage); //Ecriture du titre de la vue

        //Recuperation de la ligne de tram de l'activite precedente
        Intent intentNosTramLigne = getIntent();
        LettreLigne = intentNosTramLigne.getStringExtra("LigneTram");

        //Fragment, horaire de tram
        Bundle BundleHoraire = new Bundle();
        BundleHoraire.putString("LettreLigne", LettreLigne);
        FragmentHoraireTram FragHoraireTram = new FragmentHoraireTram();
        FragmentTransaction fragTran = getSupportFragmentManager().beginTransaction();
        fragTran.add(R.id.fragmentHoraire, FragHoraireTram.newInstance(LettreLigne));
        fragTran.commit();
    }
}


//
//    public void onMapClick(String Ligne)
//    {
//        Log.i(NomPage,"Appuie BTN Map Ligne " + Ligne);
//        //Creation de la nouvelle Intent
//        Intent intent = new Intent(ActivityHoraire.this, ActivityCarte.class);
//        intent.putExtra("LettreLigneTram",Ligne);
//        startActivity(intent);
//    }
//}
