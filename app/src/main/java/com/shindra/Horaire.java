package com.shindra;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Stop;
import com.shindra.dummy.Tram;

import java.util.ArrayList;

public class Horaire extends AppCompatActivity {

    Button mapButton;

    public Horaire(){
        super(R.layout.activity_horaire);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mapButton = findViewById(R.id.button2);
        mapButton.setVisibility(View.VISIBLE);

        Intent intentHeure = getIntent();
        String ligne = intentHeure.getStringExtra("TRAM");
        getSupportActionBar().setTitle("Horaire");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ef2b3e")));

        mapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mapButton.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction().replace(R.id.listeHeure,new mapFragment(ligne)).commit();
            }
        });

        if(savedInstanceState == null){
            Bundle bundle = new Bundle();
            bundle.putString("ligne", ligne);

            getSupportFragmentManager().beginTransaction()
                    .setReorderingAllowed(true)
                    .add(R.id.listeHeure,FHoraire.class, bundle)
                    .commit();
        }
    }
}