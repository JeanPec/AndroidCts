package com.shindra;

import android.os.Bundle;
import android.util.Log;
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
import com.shindra.ctslibrary.bo.Line;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

//https://www.programmationfacile.com/android-recyclerview-cardview-tutoriel.html
//https://stackoverflow.com/questions/40584424/simple-android-recyclerview-example

public class StartActivity extends AppCompatActivity {

    LignesAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("info", "je suis la startactivity");

        //Test-data to populate the RecyclerView with
        ArrayList<String> animalNames = new ArrayList<>();
        animalNames.add("Cheval");
        animalNames.add("Vache");
        animalNames.add("Chameau");
        animalNames.add("Mouton");
        animalNames.add("Chevre");

        //Setup the RecyclerView like slide 110
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.cardList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new LignesAdapter(this, animalNames);
        recyclerView.setAdapter(adapter);

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
                Log.d("info", model.toString());

           }

            @Override
            public void onError(@NotNull Throwable throwable) {
                //call if the network call has responded with an error
                Log.d("=erreur", "Erreur appel API");
            }
        });
    }

}

