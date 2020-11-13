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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle(R.string.main_activity_name);

        RecyclerView tramList = findViewById(R.id.tram_list);

        tramList.setLayoutManager(new LinearLayoutManager(this));
        tramList.setAdapter(new TramRecyclerViewAdapter(getListOfTram()));

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);

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

    private ArrayList<Integer> getListOfTram(){
        ArrayList<Integer> tramArray = new ArrayList<>();
        tramArray.add(R.drawable.tram_a);
        tramArray.add(R.drawable.tram_b);
        tramArray.add(R.drawable.tram_c);
        tramArray.add(R.drawable.tram_d);
        tramArray.add(R.drawable.tram_e);
        tramArray.add(R.drawable.tram_f);

        return tramArray;
    }
}

