package com.shindra.Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.shindra.Fragments.horaire_fragment;
import com.shindra.R;

public class HoraireActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horaire);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // faire le point entre les 2 activités
        Intent intentA = getIntent();
        String nomline = intentA.getStringExtra("STRING10");
        ft.add(R.id.fragment_horaire, horaire_fragment.newInstance(nomline)).commit();
        Log.d("HoraireActivity","c" );
    }
}