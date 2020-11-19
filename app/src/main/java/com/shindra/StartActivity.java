package com.shindra;

import android.content.Intent;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
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


    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    ChargementActivity loadPage;

    /*int images1[] ={R.drawable.tram_a,R.drawable.tram_b,R.drawable.tram_c,R.drawable.tram_d,R.drawable.tram_e,R.drawable.tram_f};
    int images2[] ={R.drawable.nouveau_tram_strasbourg};*/

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadPage = new ChargementActivity(this);

        recyclerView = findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getParent());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(new RVAdapter(new ArrayList<Line>(), line -> {
            Intent intent = new Intent(StartActivity.this,HoraireActivity.class);
            intent.putExtra("nomLigne",line.getName());
            startActivity(intent);
        }));

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);

        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>() {
            @Override
            public void onLoading() {
                loadPage.showChargement();
                //call once we started the network called. Indicate that the network call is in progress
            }

            @Override
            public void onSuccess(ArrayList<Line> data) {
                getSupportActionBar().setTitle("Nos trams");
                loadPage.hideChargement();

                ArrayList<Line> ligneTram = new ArrayList<Line>();

                for (Line line : data)
                    if (line.getRouteType() == RouteType.TRAM)
                        ligneTram.add(line);

                ((RVAdapter) recyclerView.getAdapter()).setLine(ligneTram);
                recyclerView.getAdapter().notifyDataSetChanged();


                //call once the network call has responded with a success
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                //call if the network call has responded with an error
            }
        });
    }

}

