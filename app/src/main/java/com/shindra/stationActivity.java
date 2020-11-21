package com.shindra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;

import org.jetbrains.annotations.NotNull;

public class stationActivity extends AppCompatActivity {

    private stationFragment frag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_station_diary);

        getSupportActionBar().setTitle(getString(R.string.stationActivityName));

        //Create a new fragment stationFragment and his bundle
        frag = new stationFragment();
        Bundle bundle = new Bundle();

        //begin transaction between the container and the fragment frag. Add parameter to the bundle
        getSupportFragmentManager().beginTransaction().add(R.id.container_station,frag).commit();
        bundle.putString("lineName",getIntent().getStringExtra("TRAM_LINE"));
        frag.setArguments(bundle);



    }

}