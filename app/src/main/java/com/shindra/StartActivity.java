package com.shindra;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.Coordinate;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;

//https://www.programmationfacile.com/android-recyclerview-cardview-tutoriel.html
//https://stackoverflow.com/questions/40584424/simple-android-recyclerview-example
//https://www.journaldev.com/10024/android-recyclerview-android-cardview-example-tutorial

public class StartActivity extends AppCompatActivity {

    LignesAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Nos trams");
        Log.d("info", "je suis la startactivity");

        //myOnClickListener = new MyOnClickListener(this);



        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);


        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>() {
            @Override
            public void onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
                Log.d("info", "message attente a afficher");
                /*
                Training for fragment :
                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                PremierFragment fragment = new PremierFragment();
                fragmentTransaction.add(R.id.fragment_container_view, fragment);
                */
            }

            @Override
            public void onSuccess(ArrayList<Line> data) {
                //call once the network call has responded with a success
                Log.d("info", "appel à API reussi");
                @io.reactivex.rxjava3.annotations.Nullable ArrayList<Line> linesList = new ArrayList<>();
                MyViewModel mvm = new MyViewModel();
                linesList = mvm.lines().getValue();
                //Log.d("CTS", mvm.lines().blockingFirst(ArrayList<line>, 1));
                Log.d("CTS", String.valueOf(linesList.get(0)));
                Log.d("CTS", String.valueOf(linesList.get(1)));

           }

            @Override
            public void onError(@NotNull Throwable throwable) {
                //call if the network call has responded with an error
                Log.d("=erreur", "Erreur appel API");
            }
        });

        //Test-data to populate the RecyclerView with
        ArrayList<Line> animalNames = getListLines(); /*new ArrayList<>();
        animalNames.add("Cheval");
        animalNames.add("Vache");
        animalNames.add("Chameau");
        animalNames.add("Mouton");
        animalNames.add("Chevre");
        */

        //Setup the RecyclerView like slide 110
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.cardList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LignesAdapter( getListLines());
        //recyclerView.setAdapter(adapter);
        recyclerView.setAdapter(adapter);

    }


    private static class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }

        @Override
        public void onClick(View v) {
            removeItem(v);
        }

        private void removeItem(View v) {

        }
    }

    private ArrayList<Stop> getListStops() {
        ArrayList<Stop> Stops = new ArrayList<>();
        Stops.add(new Stop("Gare centrale", new Date(), new Date(), "Parc des Sports", new Coordinate()));
        Stops.add(new Stop("Laiterie", new Date(), new Date(), "Lingolsheim Alouettes", new Coordinate()));
        return Stops;
    }

    private ArrayList<Line> getListLines() {
        ArrayList<Line> Lines = new ArrayList<>();
        Lines.add(new Line("Tram A", RouteType.TRAM, this.getListStops()));
        Lines.add(new Line("Tram B", RouteType.TRAM, this.getListStops()));
        return Lines;
    }

//TO BE CONTINUATED BELOW :
    private ArrayList<Tram> getListReturnInfo() {
        ArrayList<Tram> Trams = new ArrayList<>();
        Trams.add(new Tram("Tram A", R.drawable.ic_tram_a));
    }



}

