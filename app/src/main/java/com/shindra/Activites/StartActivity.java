package com.shindra.Activites;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.Activites.MyViewModel;
import com.shindra.R;
import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.Coordinate;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycler_layout);
        RecyclerView list = (RecyclerView) findViewById(R.id.list_trame_recycler_view);
        list.setLayoutManager(new LinearLayoutManager( this));


        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);


        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>() {
            @Override
            public void onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
            }

            @Override
            public void onSuccess(ArrayList<Line> data) {
                //call once the network call has responded with a success
                ArrayList<Tram> Trames = new ArrayList<>();
                for(Line lineData : data){
                    switch(lineData.getName()){
                        case  "A" :
                            Trames.add(new Tram(lineData.getName(),R.drawable.ic_tram_a));
                        break;
                        case  "B" :
                            Trames.add(new Tram(lineData.getName(),R.drawable.ic_tram_b));
                            break;
                        case  "C" :
                            Trames.add(new Tram(lineData.getName(),R.drawable.ic_tram_c));
                            break;
                        case  "D" :
                            Trames.add(new Tram(lineData.getName(),R.drawable.ic_tram_d));
                            break;
                        case  "E" :
                            Trames.add(new Tram(lineData.getName(),R.drawable.ic_tram_e));
                            break;
                        case  "F" :
                            Trames.add(new Tram(lineData.getName(),R.drawable.ic_tram_f));
                            break;
                    }
                }
                list.setAdapter(new TramLineRecyclerViewAdaptater(Trames, new TramLineViewHolder.RecyclerItemClick() {
                    @Override
                    public void onHoraireClick( Tram trames) {
                    /* c'est la qu'on définit les actions qui seront déclenchées au moment du click*/
                        /* et contient l'objet tram associé à la vue*/
                        //on creer une nouvelle intent on definit la class de depart ici this et la class d'arrivé ici SecondActivite
                        Intent myIntent = new Intent(getBaseContext(), HoraireActivity.class);
                        startActivityForResult(myIntent, 0);
                    }
                }));
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                //call if the network call has responded with an error
            }
        });
    }

    /*
    private ArrayList<Stop> getListofStops() {
        ArrayList<Stop> Stops = new ArrayList<>();
        Stops.add(new Stop("Campus Illkirch",new Date(),new Date(),"Parc des sports",new Coordinate()));
        Stops.add(new Stop("Général Leclerc",new Date() , new Date(),"Parc des sports",new Coordinate()));
        return Stops;
    }

    private ArrayList<Line> getListofLine() {
        ArrayList<Line> Lines = new ArrayList<>();
        Lines.add(new Line("Tram A", RouteType.TRAM, getListofStops()));
        Lines.add(new Line("Tram B", RouteType.TRAM, getListofStops()));
        return Lines;
    }
ImageView image = (ImageView) findViewById(R.id.image_ligne_tram);
*/




}

