package com.shindra;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycler_lines);

        //recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerViewAdapter(getListOfLines()));


        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>() {
            @Override
            public void onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
              //  setContentView(R.layout.layout_chargement);
                Log.d("info", "!!!!!!! on loading");
            }

            @Override
            public void onSuccess(ArrayList<Line> data) {
                Log.d("info", "!!!!!!! on success");
                Log.d("info", String.valueOf(data));
                //TODO recuperer les données et convertir en obj Line
                //call once the network call has responded with a success
                recyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                Log.d("info", "!!!!!!! on error");
                //call if the network call has responded with an error
            }
        });
    }
    private  ArrayList<Line> getListOfLines(){
        ArrayList<Line> lines_l = new ArrayList<Line>();
        lines_l.add(new Line("A", RouteType.TRAM,null));
        lines_l.add(new Line("C", RouteType.TRAM,null));
        lines_l.add(new Line("RSJHGH", RouteType.TRAM,null));
        return lines_l;
    }

}

