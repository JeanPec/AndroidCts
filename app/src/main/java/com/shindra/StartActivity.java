package com.shindra;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.system.Int64Ref;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;
import com.shindra.dummy.Tram;
import com.shindra.dummy.Holder;
import com.shindra.dummy.tramAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class StartActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Tram> Trams = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("Mes Trams");
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ef2b3e")));


        recyclerView = findViewById(R.id.RecylceView);


        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);



        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>() {
            @Override
            public void onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
            }

            @Override
            public void onSuccess(ArrayList<Line> data) {
                //call once the network call has responded with a success
                for (Line ITram : data){
                    if(ITram.getRouteType() == RouteType.TRAM){
                        Trams.add(new Tram(ITram));
                    }
                }
                ((tramAdapter) recyclerView.getAdapter()).setTrams(Trams);
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                //call if the network call has responded with an error
            }
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new tramAdapter(Trams,new Holder.onButtonClickListener(){
            @Override
            public void onButtonClick(Tram dtram){
                Intent horaireIntent = new Intent(StartActivity.this,Horaire.class);
                horaireIntent.putExtra("TRAM", dtram.nom);
                startActivity(horaireIntent);
                
            }
        }));
    }

    /*private ArrayList<Tram> getListTram() {
        ArrayList<Tram> trams = new ArrayList<>();
        trams.add(new Tram("A",R.drawable.tram_a));
        trams.add(new Tram("B",R.drawable.tram_b));
        trams.add(new Tram("C",R.drawable.tram_c));
        trams.add(new Tram("D",R.drawable.tram_d));
        trams.add(new Tram("E",R.drawable.tram_e));
        trams.add(new Tram("F",R.drawable.tram_f));

        return trams;
    }*/
}

