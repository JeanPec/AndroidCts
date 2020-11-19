package com.shindra;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;


public class StopFragment extends Fragment {

    public RecyclerView stops;
    public Button map;
    public String lineName;
    public ArrayList<Stop> stopsWithDepartureTime;


    public StopFragment (String lineName){
        this.lineName = lineName;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /* ArrayList containing subway's stops and their departure time received with network */
        stopsWithDepartureTime = new ArrayList<Stop>();

        /* Network call to CTS API */
        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);

        ObservableExtensionKt.observe(model.lineWithEstimatedTimeTable(RouteType.TRAM, lineName, 0), new ObservableListener<Line>() {
            @Override
            public void onLoading() {
                Log.i("Stop", "Connexion in progress");
            }

            @Override
            public void onSuccess(Line data) {
                Log.i("Stop", "Connexion established");

                for(Stop item : data.getStops())
                {
                    if(item.getEstimatedDepartureTime() != null)
                        stopsWithDepartureTime.add(item);
                }
                Log.i("Stop", "stops = " + stopsWithDepartureTime);
                stops.setAdapter(new StopAdapter(stopsWithDepartureTime, lineName));

            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                Log.i("Stop", "Network Error !");
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.stopfragment, container, false);

        stops = view.findViewById(R.id.recyclerView_stop);
        map = view.findViewById(R.id.map);

        stops.setLayoutManager(new LinearLayoutManager(getContext()));

        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Intent used to pass LINE_NAME to Map activity */
                Intent intent = new Intent(getActivity(), LineMapActivity.class);
                intent.putExtra("LINE_NAME", lineName);
                startActivity(intent);
            }
        });

        return view;

    }

}