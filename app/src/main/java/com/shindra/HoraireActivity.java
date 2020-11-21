package com.shindra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HoraireActivity extends AppCompatActivity {

    public RecyclerView arrets;
    public  String lineRef;
    public ActivityLoad loadPage;
    public HoraireFragment horaireFragment;
    public Button seeMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horaire);

        lineRef = getIntent().getStringExtra("lineRef");

        horaireFragment = new HoraireFragment(getIntent().getStringExtra("lineRef"));
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentHoraire,horaireFragment).commit();

        //FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        //fragmentTransaction.add(R.id.fragmentHoraire, horaireFragment).commit();

        getSupportActionBar().setTitle(getString(R.string.ligne ,lineRef));

    }
}