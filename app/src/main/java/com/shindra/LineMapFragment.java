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
import com.shindra.ctslibrary.apibo.Coordinate;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class LineMapFragment extends MapFragment {
    public String lineName;
    public ArrayList<Coordinate> stopsWithPosition;

    public LineMapFragment(String lineName){
        this.lineName = lineName;
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        stopsWithPosition = new ArrayList<Coordinate>();
        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);

        ObservableExtensionKt.observe(model.lineWithStop(RouteType.TRAM, lineName), new ObservableListener<Line>() {
            @Override
            public void onLoading() {

            }

            @Override
            public void onSuccess(Line data) {
                Log.i("LineMap", "Connexion established");

                for(Stop item : data.getStops())
                {
                        stopsWithPosition.add(item.getPosition());
                }
                Log.i("LineMap", "stopsWithPosition = " + stopsWithPosition);

            }

            @Override
            public void onError(@NotNull Throwable throwable) {

            }
        });
    }


    @Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /* Set title activity to tram line */
        getActivity().setTitle(getContext().getString(R.string.stop_view_holer_line) + " " + lineName);


        //addPois();
        return super.onCreateView(inflater, container, savedInstanceState);
    }
}