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

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>() {
            @Override
            public void onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
                setContentView(R.layout.layout_chargement);
            }

            @Override
            public void onSuccess(ArrayList<Line> data) {
                //call once the network call has responded with a success
                setContentView(R.layout.activity_main);
                RecyclerView recyclerView = findViewById(R.id.cardList);

                recyclerView.setHasFixedSize(true);
                LinearLayoutManager llmanager = new LinearLayoutManager(this);
                llmanager.setOrientation(LinearLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(llmanager);


            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                //call if the network call has responded with an error
            }
        });
    }
}

