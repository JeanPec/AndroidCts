package com.shindra;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class CarteActivity extends AppCompatActivity {
    public String nomLigne;
    ChargementActivity loadPage;
    LigneMapFragment ligneMapFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carte);

        ligneMapFragment = new LigneMapFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.carte, ligneMapFragment).commit();

        loadPage = new ChargementActivity(this);
        nomLigne = getIntent().getStringExtra("nomLigne");
        getSupportActionBar().setTitle("Ligne " + nomLigne);

        MyViewModel myViewModel = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(myViewModel.lineWithStop(RouteType.TRAM, nomLigne), new ObservableListener<Line>() {
            @Override
            public void onLoading() {
                loadPage.showChargement();
            }

            @Override
            public void onSuccess(Line data) {
                loadPage.hideChargement();
                ligneMapFragment.Marqueur(data);
            }

            @Override
            public void onError(@NotNull Throwable throwable) {

            }
        });
    }
}
