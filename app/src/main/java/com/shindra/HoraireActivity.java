package com.shindra;

import android.content.Intent;
import android.os.Bundle;
//import android.support.wearable.activity.WearableActivity;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HoraireActivity extends AppCompatActivity {

    public RecyclerView arretTram;
    public String nomLigne;
    ChargementActivity loadPage;
    Bundle bundle;
    FragmentA fragmentA;
    public Button carte;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horaire);

        nomLigne = getIntent().getStringExtra("nomLigne");

        fragmentA = new FragmentA();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.FragmentA,fragmentA).commit();

        arretTram = findViewById(R.id.listeTramRV2);
        nomLigne = getIntent().getStringExtra("nomLigne");
        arretTram.setLayoutManager(new LinearLayoutManager(this));
        arretTram.setAdapter(new HoraireAdapter(new ArrayList<Stop>(),nomLigne));

        loadPage = new ChargementActivity(this);
        getSupportActionBar().setTitle("Horaires");

        carte = findViewById(R.id.BoutonCarte);
        carte.setOnClickListener(v -> {
            Intent intent = new Intent(HoraireActivity.this,CarteActivity.class);
            intent.putExtra("nomLigne", nomLigne);
            startActivity(intent);
        });

        MyViewModel myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(myViewModel.lineWithEstimatedTimeTable(RouteType.TRAM, nomLigne, 0), new ObservableListener<Line>() {
            @Override
            public void onLoading() {
                loadPage.showChargement();
            }

            @Override
            public void onSuccess(Line data) {
                ArrayList<Stop> Arrets = new ArrayList<Stop>();
                for (Stop stop : data.getStops())
                    if (stop.getEstimatedDepartureTime() != null)
                        Arrets.add(stop);

                ((HoraireAdapter) arretTram.getAdapter()).setArrets(Arrets);
                arretTram.getAdapter().notifyDataSetChanged();
                loadPage.hideChargement();

            }

            @Override
            public void onError(@NotNull Throwable throwable) {

            }
        });
    }
}
