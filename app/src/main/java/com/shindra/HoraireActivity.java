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
    public  String lineName;
    public ActivityLoad loadPage;
    public HoraireFragment horaireFragment;
    public Button seeMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horaire);

        lineName = getIntent().getStringExtra("lineName");
        horaireFragment = new HoraireFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragmentHoraire, horaireFragment).commit();

        arrets = findViewById(R.id.recyclerView2);
        arrets.setLayoutManager(new LinearLayoutManager(this));
        arrets.setAdapter(new HoraireAdapter(new ArrayList<Stop>(), lineName));

        loadPage = new ActivityLoad(this);
        getSupportActionBar().setTitle("Ligne " + getInfos(lineName));

        seeMap = findViewById(R.id.carteButton);
        seeMap.setOnClickListener(v -> {
            Intent intent = new Intent(HoraireActivity.this, MapActivity.class);
            intent.putExtra("lineName", lineName);
            startActivity(intent);
        });


        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lineWithEstimatedTimeTable(RouteType.TRAM, getInfos(lineName), 0), new ObservableListener<Line>() {

            @Override
            public void onError(@NotNull Throwable throwable) {}

            @Override
            public void onLoading()
            {
                loadPage.showLoadingScreen();
            }

            @Override
            public void onSuccess(Line data)
            {
                ArrayList<Stop> listArrets = new ArrayList<Stop>();
                for (Stop stop : data.getStops())
                    if (stop.getEstimatedDepartureTime() != null)
                        listArrets.add(stop);

                ((HoraireAdapter) arrets.getAdapter()).setArrets(listArrets);
                arrets.getAdapter().notifyDataSetChanged();
                loadPage.HideLoadingScreen();
            }
        });
    }

    private String getInfos(String lineName)
    {
        switch (lineName)
        {
            case "Parc des Sports - Illkirch Graffenstaden":
                return "A";
            case "Lingolsheim Tiergaertel - Hoenheim Gare":
                return "B";
            case "Gare Centrale - Neuhof Rodolphe Reuss":
                return "C";
            case "Poteries - Port du Rhin / Kehl Rathaus":
                return "D";
            case "Robertsau l'Escale - Campus d'Illkirch":
                return "E";
            case "Comtes - Place d'Islande":
                return "F";
            default:
                return "?";
        }
    }
}