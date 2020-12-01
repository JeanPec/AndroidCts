package com.shindra;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
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

public class StartActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    //private RecyclerView.LayoutManager layoutManager;
   // private TramCardAdapter tramCardAdapter;

    private ArrayList<Line> lineArrayList;


   // private List<HoraireCard> horaireCardList;

    protected void onStart() {
        super.onStart();

        TramCard.RecyclerItemClick callback = new TramCard.RecyclerItemClick()
        {
            @Override
            public void onHoraireButtonClick(Line line) {

                Intent intent = new Intent(StartActivity.this, HoraireActivity.class);
                intent.putExtra("line", line.getName());

                startActivity(intent);

            }
        };


        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>() {
            @Override
            public void onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
            }

            @Override
            public void onSuccess(ArrayList<Line> data) {
                //call once the network call has responded with a success

                lineArrayList = new ArrayList<Line>();

                //horaireCardList = new ArrayList<Line>();

                for(Line line:data){
                    if(line.getRouteType() == RouteType.TRAM){
                        //horaireCardList.add(new HoraireCard(line.getStops().get(0).getName(), "8h00", "test"));
                        lineArrayList.add(line);
                    }

                }
                recyclerView.setAdapter(new TramCardAdapter(lineArrayList, callback));


            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                //call if the network call has responded with an error
            }
        });
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // setContentView(R.layout.fragment_first);

        //setTitle("Nos trams");
        getSupportActionBar().setTitle(getString(R.string.home_title));
        //getSupportActionBar().setTitle(getString(R.string.horaire_title));


        //Drawable imgTram = getDrawable(R.drawable.nouveau_tram_strasbourg);

        //ArrayList<TramCard> tramCardList = new ArrayList<TramCard>();
        /*tramCardList.add(new TramCard(getDrawable(R.drawable.tram_a), imgTram));
        tramCardList.add(new TramCard(getDrawable(R.drawable.tram_b), imgTram));
        tramCardList.add(new TramCard(getDrawable(R.drawable.tram_c), imgTram));
        tramCardList.add(new TramCard(getDrawable(R.drawable.tram_d), imgTram));
        tramCardList.add(new TramCard(getDrawable(R.drawable.tram_e), imgTram));
        tramCardList.add(new TramCard(getDrawable(R.drawable.tram_f), imgTram));*/



        recyclerView = findViewById(R.id.appCts_recyclerview);

        //TramCardAdapter tramCardAdapter = new TramCardAdapter(tramCardList, this);

        //recyclerView.setAdapter(tramCardAdapter);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }




}

