package com.shindra;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.bo.Line;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class StartActivity extends AppCompatActivity {

    //Creation des variables
    //int ImagesLigneTram[] = {R.drawable.tram_a,R.drawable.tram_b,R.drawable.tram_c,R.drawable.tram_d,R.drawable.tram_e,R.drawable.tram_f};

    //Creation de la Recycler View
    //RecyclerView RecyclerView_Ligne_Tram;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView ListeLigneTram = findViewById(R.id.RecyclerView_Ligne_Tram);

        ListeLigneTram.setLayoutManager(new LinearLayoutManager(this));
        ListeLigneTram.setAdapter(new RecyclerViewAdapterLigneTram(getListOfTrams()));

        //RecyclerView_Ligne_Tram = findViewById(R.id.RecyclerView_Ligne_Tram);

        //RecyclerViewAdapterLigneTram adapterView = new RecyclerViewAdapterLigneTram(this, ImagesLigneTram);
        //RecyclerView_Ligne_Tram.setAdapter(adapterView);
        //RecyclerView_Ligne_Tram.setLayoutManager(new LinearLayoutManager(this));




        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);

        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>()
        {
            @Override
            public void onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
            }

            @Override
            public void onSuccess(ArrayList<Line> data) {
                //call once the network call has responded with a success
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                //call if the network call has responded with an error
            }
        });
    }

    private ArrayList<Integer> getListOfTrams()
    {
        ArrayList<Integer> ligneTram = new ArrayList<>();
        ligneTram.add(R.drawable.tram_a);
        ligneTram.add(R.drawable.tram_b);
        ligneTram.add(R.drawable.tram_c);
        ligneTram.add(R.drawable.tram_d);
        ligneTram.add(R.drawable.tram_e);
        ligneTram.add(R.drawable.tram_f);
        return ligneTram;
    }

}

