package com.shindra;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;

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
    ActivityLoad loadPage;
    Bundle bundle;
    HoraireFragment horaireFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horaire);

        lineName = getIntent().getStringExtra("lineName");
        //use fragment
        bundle.putString("lineName",lineName);
        horaireFragment = new HoraireFragment();
        horaireFragment.setArguments(bundle);
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment_container_view_tag, horaireFragment).commit();

        loadPage = new ActivityLoad(this);


        //arrets = findViewById(R.id.recyclerView2);
        //arrets.setLayoutManager(new LinearLayoutManager(getParent()));
        //arrets.setAdapter(new HoraireAdapter(new ArrayList<Stop>(), lineName));

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lineWithEstimatedTimeTable(RouteType.TRAM, getInfos(lineName), 0), new ObservableListener<Line>() {

            @Override
            public void onError(@NotNull Throwable throwable) {
                loadPage.showLoadingScreen();
            }

            @Override
            public void onLoading()
            {
                loadPage.showLoadingScreen();
            }

            @Override
            public void onSuccess(Line data)
            {
                loadPage.HideLoadingScreen();
                getSupportActionBar().setTitle("Ligne " + getInfos(lineName));
                
                ArrayList<Stop> listArrets = new ArrayList<Stop>();
                for (Stop stop : data.getStops())
                    if (stop.getEstimatedDepartureTime() != null)
                        listArrets.add(stop);

                ((HoraireAdapter) arrets.getAdapter()).setArrets(listArrets);
                arrets.getAdapter().notifyDataSetChanged();
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