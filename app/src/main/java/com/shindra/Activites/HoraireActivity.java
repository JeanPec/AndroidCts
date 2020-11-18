package com.shindra.Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.shindra.Fragments.horaire_fragment;
import com.shindra.R;

public class HoraireActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horaire);

        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.fragment_horaire, horaire_fragment.newInstance("coucou","coucou2","coucou3","coucou4")).commit();
    }
}