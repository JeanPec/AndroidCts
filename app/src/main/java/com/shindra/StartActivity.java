package com.shindra;

import android.content.Intent;
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

public class StartActivity extends AppCompatActivity {

    public RecyclerView recyclerView;
    public ArrayList<Line> lines;
    //int pictures[] = {R.drawable.tram_a, R.drawable.tram_b, R.drawable.tram_c, R.drawable.tram_d, R.drawable.tram_e, R.drawable.tram_f};


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startactivity);


        Intent intent = new Intent(this, Stop.class);

        /* RecyclerView used to print all subway's lines */
        recyclerView = findViewById(R.id.recyclerView);

        lines = new ArrayList<Line>();
        lines = getListOfLines();

        // LineAdapter myAdapter = new LineAdapter(this, pictures);
        LineAdapter myAdapter = new LineAdapter(lines, new LineClick(){

            @Override
            public void onLineClick(Line line) {
                String name = line.getName();
               /* Log.d("StartActivity", "name = " + name); */
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

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

    public ArrayList<Line> getListOfLines(){

        ArrayList<Line> lines = new ArrayList<Line>();

        lines.add(new Line("A", RouteType.TRAM, null));
        lines.add(new Line("B", RouteType.TRAM, null));
        lines.add(new Line("C", RouteType.TRAM, null));
        lines.add(new Line("D", RouteType.TRAM, null));
        lines.add(new Line("E", RouteType.TRAM, null));
        lines.add(new Line("F", RouteType.TRAM, null));

        return lines;
    }
}

