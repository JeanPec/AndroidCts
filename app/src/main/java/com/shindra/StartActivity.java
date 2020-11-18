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
import com.shindra.ctslibrary.bo.Line;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class StartActivity extends AppCompatActivity {


    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private TramCardAdapter tramCardAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // setContentView(R.layout.fragment_first);

        setTitle("Nos trams");


        Drawable imgTram = getDrawable(R.drawable.nouveau_tram_strasbourg);




        List<TramCard> tramCardList = new ArrayList<TramCard>();
        tramCardList.add(new TramCard(getDrawable(R.drawable.tram_a), imgTram));
        tramCardList.add(new TramCard(getDrawable(R.drawable.tram_b), imgTram));
        tramCardList.add(new TramCard(getDrawable(R.drawable.tram_c), imgTram));
        tramCardList.add(new TramCard(getDrawable(R.drawable.tram_d), imgTram));
        tramCardList.add(new TramCard(getDrawable(R.drawable.tram_e), imgTram));
        tramCardList.add(new TramCard(getDrawable(R.drawable.tram_f), imgTram));


        recyclerView = findViewById(R.id.appCts_recyclerview);
        tramCardAdapter = new TramCardAdapter(tramCardList);
        recyclerView.setAdapter(tramCardAdapter);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);




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
}

