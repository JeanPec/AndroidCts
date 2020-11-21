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
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragmentHoraire, horaireFragment).commit();

        loadPage = new ActivityLoad(this);
        getSupportActionBar().setTitle(getString(R.string.ligne ,lineRef));

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lineWithEstimatedTimeTable(RouteType.TRAM, horaireFragment.getLineRef(), 0), new ObservableListener<Line>() {
            @Override
            public void onError(@NotNull Throwable throwable) {loadPage.HideLoadingScreen();}

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
}