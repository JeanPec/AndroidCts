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
    private RecyclerView mRecyclerView; // Adapter recommanded: it will display item currently possible
    private RecyclerView.Adapter mAdapter; // Bridge between data (arrayList) & recycler view
    private RecyclerView.LayoutManager mLayoutManager; // Responsible aligning single items in list

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<ArretItem> arretList = new ArrayList<>();
        arretList.add(new ArretItem("Parc malraux","Bonjour","Tram B", "20h00"));
        arretList.add(new ArretItem("Parc malraux","Bonjour","Tram B", "20h00"));
        arretList.add(new ArretItem("Parc malraux","Bonjour","Tram B", "20h00"));
        arretList.add(new ArretItem("Parc malraux","Bonjour","Tram B", "20h00"));        arretList.add(new ArretItem("Parc malraux","Bonjour","Tram B", "20h00"));
        arretList.add(new ArretItem("Parc malraux","Bonjour","Tram B", "20h00"));
        arretList.add(new ArretItem("Parc malraux","Bonjour","Tram B", "20h00"));
        arretList.add(new ArretItem("Parc malraux","Bonjour","Tram B", "20h00"));        arretList.add(new ArretItem("Parc malraux","Bonjour","Tram B", "20h00"));
        arretList.add(new ArretItem("Parc malraux","Bonjour","Tram B", "20h00"));
        arretList.add(new ArretItem("Parc malraux","Bonjour","Tram B", "20h00"));
        arretList.add(new ArretItem("Parc malraux","Bonjour","Tram B", "20h00"));
        arretList.add(new ArretItem("Parc malraux","Bonjour","Tram B", "20h00"));
        arretList.add(new ArretItem("Parc malraux","Bonjour","Tram B", "20h00"));

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true); // if app don't change size: increase perf
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new ArretAdapter(arretList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

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

