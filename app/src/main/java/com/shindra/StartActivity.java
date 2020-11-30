package com.shindra;

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


public class StartActivity extends AppCompatActivity {

    private RecyclerView mTramRecyclerView;
    private RecyclerView.Adapter mTramdapter;
    private RecyclerView.LayoutManager mTramLayoutManager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_tram);
        setTitle("Nos Trams");

        ArrayList<Line> mTramLines = new ArrayList<>();

        mTramLines.add(new Line("Tram A", RouteType.TRAM, null));
        mTramLines.add(new Line("Tram B", RouteType.TRAM, null));
        mTramLines.add(new Line("Tram C", RouteType.TRAM, null));
        mTramLines.add(new Line("Tram D", RouteType.TRAM, null));
        mTramLines.add(new Line("Tram E", RouteType.TRAM, null));
        mTramLines.add(new Line("Tram F", RouteType.TRAM, null));

        mTramRecyclerView = findViewById(R.id.TramListCardview);
        mTramRecyclerView.setHasFixedSize(true);
        mTramLayoutManager = new LinearLayoutManager(this);
        mTramdapter = new TramRecyclerViewAdapter(mTramLines);
        mTramRecyclerView.setLayoutManager(mTramLayoutManager);
        mTramRecyclerView.setAdapter(mTramdapter);

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);

        ObservableExtensionKt.observe(model.lines(), new ObservableListener<ArrayList<Line>>() {

            @Override
            public void onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
            }

            @Override
            public void onSuccess(ArrayList<Line> data) {

                /*for (Line l : data) {
                    if (l.getRouteType() == RouteType.TRAM) {
                        mTramLines.add(l);
                    }
                }*/
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                //call if the network call has responded with an error
            }
        });

    }
}