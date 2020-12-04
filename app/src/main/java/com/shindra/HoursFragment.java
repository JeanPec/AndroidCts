package com.shindra;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class HoursFragment extends Fragment{

    private ArrayList<Stop> stopList = new ArrayList<>();
    Button bMap;
    LoadingDialog loadingPage;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.hoursfragment, container, false);
        String strLineName = getArguments().getString("lineName", getContext().getString(R.string.defaultLineName));

        RecyclerView stopRecyclerView = view.findViewById(R.id.stopRecyclerView);
        bMap = view.findViewById(R.id.button_map);
        stopRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        stopRecyclerView.setAdapter(new HoursAdapter(strLineName,stopList));

        bMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MapActivity.class);
                intent.putExtra("lineName", strLineName);
                startActivity(intent);
            }
        });

        loadingPage = new LoadingDialog(getActivity());

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lineWithEstimatedTimeTable(RouteType.TRAM, strLineName, 0), new ObservableListener<Line>() {
            @Override
            public void onLoading() {
                loadingPage.show();
            }

            @Override
            public void onSuccess(Line data) {
                loadingPage.dismiss();
                ArrayList<Stop> stops = new ArrayList<>();
                for (Stop stop : data.getStops()){
                    if (stop.getEstimatedDepartureTime() != null){
                        stops.add(stop);
                    }
                }
                ((HoursAdapter) stopRecyclerView.getAdapter()).setStopList(stops);
                stopRecyclerView.getAdapter().notifyDataSetChanged();
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                loadingPage.dismiss();
            }
        });
        return view;
    }
}
