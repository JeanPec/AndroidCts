package com.shindra.Map;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.shindra.ErrorScreen;
import com.shindra.HelperLine;
import com.shindra.LoadingScreen;
import com.shindra.MyViewModel;
import com.shindra.R;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;
import com.shindra.arrakis.controls.Poi;
import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;


import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class MapLineFragment extends com.shindra.arrakis.controls.MapFragment
{

    // Attributes
    private static final String TAG = "MapLineFragment";
    private String mTramLineLetter;
    private LoadingScreen mLoadingScreen;
    private ErrorScreen mErrorScreen;

    // Constructor
    public static MapLineFragment onInstance(String tramLineLetter){
        MapLineFragment fragment = new MapLineFragment();
        Bundle args = new Bundle();
        args.putString("tramLineLetter", tramLineLetter);
        fragment.setArguments(args);
        return fragment;
    }

    // Methods
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        View v = inflater.inflate(R.layout.fragment_gmaps,container, false);

        // Retrieve activity data
        mTramLineLetter = getArguments().getString("tramLineLetter");
        ((AppCompatActivity) getContext()).getSupportActionBar().setTitle(getString(R.string.line)+ " " + mTramLineLetter);
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();

        mLoadingScreen = new LoadingScreen(getContext());
        mErrorScreen = new ErrorScreen(getContext());

        // Network calls
        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lineWithStop(RouteType.TRAM, mTramLineLetter), new ObservableListener<Line>() {
            @Override
            public void onLoading()
            {
                Log.i(TAG, "onLoading");
                mLoadingScreen.show();
            }

            @Override
            public void onSuccess(Line data)
            {
                Log.i(TAG, "onSuccess");
                mLoadingScreen.dismiss();
                UpdateView(data);
            }

            @Override
            public void onError(@NotNull Throwable throwable)
            {
                Log.i(TAG, "onError");
                mLoadingScreen.dismiss();
                mErrorScreen.show();
            }
        });
    }

    public void UpdateView(Line line)
    {
        // Add positions on map
        Log.i(TAG, "UpdateView");
        ArrayList<Poi> positions = new ArrayList<Poi>();
        for (Stop element : line.getStops())
            positions.add(new Poi(R.drawable.icon_maps_place_24px, HelperLine.GetLineColor(line.getName()), element.getPosition().getLatitude(), element.getPosition().getLongitude()));
        addPois(positions);
    }
}