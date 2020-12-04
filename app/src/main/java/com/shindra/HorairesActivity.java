package com.shindra;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.shindra.ctslibrary.bo.Stop;

import java.util.ArrayList;

public class HorairesActivity extends AppCompatActivity {

    //On crée la rcycler view qui va afficher les  ainsi que son adapter
    RecyclerView RecyclerViewHoraires;
    RecyclerView.Adapter RecyclerViewHorairesAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horaires);
        setTitle("Horaire");

        //On crée une liste qui va contenir les arrêts
        ArrayList<Stop> ListeArrets = new ArrayList<>();

    }



}
