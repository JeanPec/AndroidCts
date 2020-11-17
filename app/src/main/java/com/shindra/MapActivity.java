package com.shindra;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MapActivity extends AppCompatActivity {
    public ActivityLoad loadPage;
    public String lineName;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        lineName = getIntent().getStringExtra("lineName");
        getSupportActionBar().setTitle("Ligne " + getInfos(lineName));

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lineWithStop(RouteType.TRAM, getInfos(lineName)), new ObservableListener<Line>() {
            @Override
            public void onLoading() {loadPage.showLoadingScreen();}

            @Override
            public void onSuccess(Line line) {
                loadPage.HideLoadingScreen();
            }

            @Override
            public void onError(@NotNull Throwable throwable) {}
        });
    }

    private String getInfos(String lineName) {
        switch (lineName) {
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
