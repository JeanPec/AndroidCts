package com.shindra;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
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

    private RecyclerView recyclerView;
    private ArrayList<Line> lines;
    private DialogFragment dialogLoad;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.startactivity);

        setTitle(getString(R.string.title_activity_line));

        /* RecyclerView used to print all subway's lines */
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        /* ArrayList containing subway's lines received with network */
        lines = new ArrayList<Line>();

        dialogLoad = new DialogLoadActivity(StartActivity.this);

    }

    @Override
    protected void onStart() {
        super.onStart();

        /* Intent used to pass LINE_NAME to Stop activity */
        Intent intent = new Intent(this, StopActivity.class);

        LineAdapter lineAdapter = new LineAdapter(lines, new LineClick(){

            @Override
            public void onLineClick(Line line) {
                String name = line.getName();
                intent.putExtra("LINE_NAME", name);
                startActivity(intent);
            }
        });

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);

        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>() {
            @Override
            public void onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
                dialogLoad.show(getSupportFragmentManager(), "Line");
                Log.i("StartActiviy", "Connexion in progress");

            }

            @Override
            public void onSuccess(ArrayList<Line> data) {
                //call once the network call has responded with a success
                dialogLoad.dismiss();
                Log.i("StartActiviy", "Connexion established");
                for(Line item : data)
                {
                    if(item.getRouteType() == RouteType.TRAM)
                        lines.add(item);
                }
                recyclerView.setAdapter(lineAdapter);
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                //call if the network call has responded with an error
                dialogLoad.dismiss();
                Log.i("StartActiviy", "Network Error !");
            }
        });
    }
}

