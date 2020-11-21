package com.shindra;

import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;


import com.shindra.arrakis.controls.MapFragment;
import com.shindra.arrakis.controls.Poi;
import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class mapActivity extends AppCompatActivity {

    private mapFragment frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        getSupportActionBar().setTitle(getString(R.string.ligneText) + getIntent().getStringExtra("LINE_NAME"));

        frag = new mapFragment();
        Bundle bundle = new Bundle();

        getSupportFragmentManager().beginTransaction().add(R.id.map_container,frag).commit();
        frag.setArguments(bundle);
        bundle.putString("lineName",getIntent().getStringExtra("LINE_NAME"));

    }

}