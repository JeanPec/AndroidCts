package com.shindra;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;

import com.shindra.arrakis.controls.MapFragment;
import com.shindra.arrakis.controls.Poi;
import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class mapFragment extends MapFragment {

    @Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_gmaps, container,false);
    }

    @Override
    public void onStart() {
        super.onStart();

        loadingWindow window = new loadingWindow(getContext());

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lineWithStop(RouteType.TRAM,getArguments().getString("lineName")), new ObservableListener<Line>() {

            @Override
            public void onError(@NotNull Throwable throwable) {

            }

            @Override
            public void onSuccess(Line data) {

                window.dismissLoadingWindow();
                ArrayList<Poi> listOfPoi = new ArrayList<Poi>() ;
                for (Stop stop: data.getStops()) {

                    listOfPoi.add(new Poi(R.drawable.icon_maps_place_24px,getLineColorFromLine(data),stop.component5().getLatitude(),stop.component5().getLongitude()));
                }
                addPois(listOfPoi);
            }

            @Override
            public void onLoading() {
                window.displayLoadingWindow();
            }
        });

    }

    public int getLineColorFromLine(Line line){

        switch (line.getName())
        {
            case "A":
                return R.color.ligneA;
            case "B":
                return R.color.ligneB;
            case "C":
                return R.color.ligneC;
            case "D":
                return R.color.ligneD;
            case "E":
                return R.color.ligneE;
            case "F":
                return R.color.ligneF;
            default:
                return R.color.black;
        }
    }
}
