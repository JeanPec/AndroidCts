package com.shindra;

import android.content.Intent;
import android.os.Bundle;

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

public class LigneActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ActivityLoad loadPage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ligne);

        loadPage = new ActivityLoad(this);

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getParent());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new LigneAdapter(new ArrayList<Line>(), line -> {
            Intent intent = new Intent(LigneActivity.this, HoraireActivity.class);
            intent.putExtra("lineRef", line.getName());
            startActivity(intent);
        }));

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>() {
            @Override
            public void onLoading() {loadPage.showLoadingScreen();}

            @Override
            public void onSuccess(ArrayList<Line> data) {
                getSupportActionBar().setTitle("Nos trams");
                loadPage.HideLoadingScreen();

                ArrayList<Line> lineTram = new ArrayList<Line>();
                for (Line line : data)
                    if (line.getRouteType() == RouteType.TRAM)
                        lineTram.add(line);

                ((LigneAdapter) recyclerView.getAdapter()).setLine(lineTram);
                recyclerView.getAdapter().notifyDataSetChanged();

            }
            @Override
            public void onError(@NotNull Throwable throwable) {loadPage.HideLoadingScreen();}
        });
    }
}

