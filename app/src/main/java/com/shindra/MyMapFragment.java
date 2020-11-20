package com.shindra;

import android.os.Bundle;
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

import java.util.ArrayList;


public class MyMapFragment extends MapFragment {

    String lineName;
    ArrayList<Poi> linePoints;

    public static MyMapFragment newInstance(String param1 , String param2) {
        MyMapFragment fragment = new MyMapFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater , ViewGroup container ,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        lineName = this.getArguments().getString("lineName");

        return inflater.inflate(R.layout.fragment_gmaps, container , false);
    }

    @Override
    public void onStart() {
        super.onStart();

        LoadingDialog loadingDialog = new LoadingDialog(getActivity());
        loadingDialog.startAnimation();

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class); // recupere l'objet class d'un modele
        ObservableExtensionKt.observe(model.lineWithStop(RouteType.TRAM , lineName) , new ObservableListener<Line>() {
            @Override
            public void onLoading() {

            }

            @Override
            public void onSuccess(Line data) {

                linePoints = new ArrayList<Poi>();

                for (Stop oneStop : data.getStops())
                {
                    linePoints.add(new Poi(R.drawable.icon_maps_place_24px,getColor(lineName),oneStop.getPosition().getLatitude(),oneStop.getPosition().getLongitude()));
                }

                addPois(linePoints);
                 loadingDialog.dismissDialog();

            }

            @Override
            public void onError(@NotNull Throwable throwable) {

            }
        });
    }

    public int getColor(String tramLineSelected)
    {
        switch  (tramLineSelected)
        {
            case "A":
                return (R.color.Ligne_A);

            case "B":
                return(R.color.Ligne_B);

            case "C":
                return (R.color.Ligne_C);

            case "D":
                return(R.color.Ligne_D);

            case "E":
                return(R.color.Ligne_E);

            case "F":
                return(R.color.Ligne_F);

            default:
                return(R.color.black);

        }

    }
}