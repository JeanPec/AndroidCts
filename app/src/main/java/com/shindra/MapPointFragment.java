package com.shindra;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shindra.arrakis.controls.MapFragment;
import com.shindra.arrakis.controls.Poi;
import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MapPointFragment extends MapFragment {

    private int activityOrientation;
    String letterLine;
    ArrayList positionMap;
    ArrayList<Line> dataCTS;
    private static final String TAG1 = "Map";
    int clickedColor;


    public MapPointFragment(String letter, int color) {
        super();
        letterLine = letter;
        clickedColor = color;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        positionMap = new ArrayList<Poi>();

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lineWithStop(RouteType.TRAM, letterLine),
                new ObservableListener<Line>() {

                    @Override
                    public void onError(@NotNull Throwable throwable) {

                    }

                    @Override
                    public void onSuccess(Line data) {

                        Log.d(TAG1, "answer is made");

                        for (Stop it : data.getStops()) {

                            positionMap.add(new Poi(R.drawable.icon_maps_place_24px,clickedColor,it.getPosition().getLatitude(),it.getPosition().getLongitude()));

                        }
                        addPois(positionMap);

                    }

                    @Override
                    public void onLoading() {

                    }
                });
    }
}