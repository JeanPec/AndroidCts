package com.shindra;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
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
import java.util.List;

public class StartActivity extends AppCompatActivity implements RecyclerItemClick {


    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private TramCardAdapter tramCardAdapter;

    private List<HoraireCard> horaireCardList;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // setContentView(R.layout.fragment_first);

        //setTitle("Nos trams");
        getSupportActionBar().setTitle(getString(R.string.home_title));
        //getSupportActionBar().setTitle(getString(R.string.horaire_title));


        Drawable imgTram = getDrawable(R.drawable.nouveau_tram_strasbourg);

        ArrayList<TramCard> tramCardList = new ArrayList<TramCard>();
        tramCardList.add(new TramCard(getDrawable(R.drawable.tram_a), imgTram));
        tramCardList.add(new TramCard(getDrawable(R.drawable.tram_b), imgTram));
        tramCardList.add(new TramCard(getDrawable(R.drawable.tram_c), imgTram));
        tramCardList.add(new TramCard(getDrawable(R.drawable.tram_d), imgTram));
        tramCardList.add(new TramCard(getDrawable(R.drawable.tram_e), imgTram));
        tramCardList.add(new TramCard(getDrawable(R.drawable.tram_f), imgTram));



        recyclerView = findViewById(R.id.appCts_recyclerview);

        TramCardAdapter tramCardAdapter = new TramCardAdapter(tramCardList, this);

        recyclerView.setAdapter(tramCardAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));




        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>() {
            @Override
            public void onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
            }

            @Override
            public void onSuccess(ArrayList<Line> data) {
                //call once the network call has responded with a success
                horaireCardList = new ArrayList<HoraireCard>();

                for(Line line:data){
                    if(line.getRouteType() == RouteType.TRAM){
                        horaireCardList.add(new HoraireCard(String.valueOf(line.getStops().size()), "8h00", "test"));
                    }

                }


            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                //call if the network call has responded with an error
            }
        });
    }

    @Override
    public void onHoraireButtonClick(TramCard tramCard) {
        //List<HoraireCard> horaireCardList = new ArrayList<HoraireCard>();



       /* horaireCardList.add(new HoraireCard("Arret 1", "19h00", "A"));
        horaireCardList.add(new HoraireCard("Arret 2", "19h15", "B"));
        horaireCardList.add(new HoraireCard("Arret 1", "19h00", "C"));
        horaireCardList.add(new HoraireCard("Arret 2", "19h15", "D"));
        horaireCardList.add(new HoraireCard("Arret 1", "19h00", "E"));
        horaireCardList.add(new HoraireCard("Arret 2", "19h15", "F"));*/




        recyclerView.setAdapter(new HoraireAdapter(horaireCardList));
    }
}

