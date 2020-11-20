package com.shindra;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.Coordinate;
import com.shindra.ctslibrary.apibo.LineApi;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Date;

import static com.shindra.ctslibrary.apibo.RouteType.*;

//https://www.programmationfacile.com/android-recyclerview-cardview-tutoriel.html
//https://stackoverflow.com/questions/40584424/simple-android-recyclerview-example
//https://www.journaldev.com/10024/android-recyclerview-android-cardview-example-tutorial

public class StartActivity extends AppCompatActivity {

    private static LignesAdapter adapter;
    private static ArrayList<Line>tramLine = new ArrayList<>();
    private static ArrayList<Integer> removedItems;
    static View.OnClickListener myOnClickListener;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Nos trams");
        Log.d("info", "je suis la startactivity");
        myOnClickListener = new MyOnClickListener(this);

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
                //Log.d("CTS", data.get(0).toString());
                //Log.d("CTS", data.get(1).toString());
                int tailleData = data.size();

                Log.d("CTS", "Taille de tramLine avant boucle:"+tramLine.size());
                Log.d("CTS", "Taille de data:"+tailleData);
                //tramLine.addAll(data);

                int indexTramLine = 0;
                for (int i=0 ; i<tailleData ; i++) {
                    if (data.get(i).getRouteType().compareTo(TRAM) == 0) {
                        //Log.d("CTS", "Ligne recuperee:" + data.get(i).toString() + " /Val du booleen:" + data.get(i).getRouteType().compareTo(TRAM));
                        tramLine.add(indexTramLine, data.get(i));
                        indexTramLine++;
                    }

                }


                Log.d("CTS", "Taille de tramLine apres boucle:"+tramLine.size());
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                //call if the network call has responded with an error
                Log.d("=erreur", "Erreur appel API");
            }
        });

        //Setup the RecyclerView like slide 110
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.cardList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        removedItems = new ArrayList<Integer>();
        //adapter = new LignesAdapter( getListLines());
        adapter = new LignesAdapter(tramLine);
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
            /*
            int selectedItemPosition = recyclerView.getChildPosition(v);
             */
        }
    }

    private ArrayList<Stop> getListStops() {
        ArrayList<Stop> Stops = new ArrayList<>();
        Stops.add(new Stop("Gare centrale", new Date(), new Date(), "Parc des Sports", new Coordinate()));
        Stops.add(new Stop("Laiterie", new Date(), new Date(), "Lingolsheim Alouettes", new Coordinate()));
        return Stops;
    }

}

