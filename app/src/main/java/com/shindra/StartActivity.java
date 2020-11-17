package com.shindra;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.bo.Line;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class StartActivity extends AppCompatActivity {

    RecyclerView rTramList;
    int iTrams[] = {R.drawable.tram_a,R.drawable.tram_b,R.drawable.tram_c,R.drawable.tram_d,R.drawable.tram_e,R.drawable.tram_f};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.Nos_trams);

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);

        rTramList = findViewById(R.id.RecyclerView);

        rTramList.setAdapter(new RecyclerViewAdapter(getListTrams()));
        rTramList.setLayoutManager(new LinearLayoutManager(this));


        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>() {
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

    private ArrayList<Integer> getListTrams(){
        ArrayList<Integer> iTramsArray = new ArrayList<>();
        iTramsArray.add(R.drawable.tram_a);
        iTramsArray.add(R.drawable.tram_b);
        iTramsArray.add(R.drawable.tram_c);
        iTramsArray.add(R.drawable.tram_d);
        iTramsArray.add(R.drawable.tram_e);
        iTramsArray.add(R.drawable.tram_f);

        return iTramsArray;
    }

}

