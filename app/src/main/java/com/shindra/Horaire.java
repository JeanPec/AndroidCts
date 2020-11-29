package com.shindra;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Stop;
import com.shindra.dummy.Tram;

import java.util.ArrayList;

public class Horaire extends AppCompatActivity {

    public Horaire(){
        super(R.layout.activity_horaire);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intentHeure = getIntent();
        String ligne = intentHeure.getStringExtra("TRAM");

        if(savedInstanceState == null){
            Bundle bundle = new Bundle();
            bundle.putString("ligne", ligne);

            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.listeHeure,FHoraire.class, bundle)
                    .commit();
        }

        /*FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment_container_view_tag, new FHoraire(ligne)).commit();*/

    }
}