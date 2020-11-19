package com.shindra;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MapActivity extends AppCompatActivity {
    public ActivityLoad loadPage;
    public String lineRef;
    public LigneMapFragment fragment;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        fragment = new LigneMapFragment();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frameMap, fragment).commit();

        loadPage = new ActivityLoad(this);

        lineRef = getIntent().getStringExtra("lineRef");
        getSupportActionBar().setTitle("Ligne " + lineRef);

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lineWithStop(RouteType.TRAM, lineRef), new ObservableListener<Line>() {
            @Override
            public void onLoading() {loadPage.showLoadingScreen();}

            @Override
            public void onSuccess(Line line) {
                loadPage.HideLoadingScreen();
                fragment.addPointsOnCard(line);
            }

            @Override
            public void onError(@NotNull Throwable throwable) {loadPage.HideLoadingScreen();}
        });
    }
}
