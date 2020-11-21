package com.shindra;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;
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
    private String lineName;
    private ArrayList<Poi> poi;
    private DialogFragment dialogLoad;

    public LineMapFragment(String lineName) {
        this.lineName = lineName;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();

        poi = new ArrayList<Poi>();

        dialogLoad = new DialogLoadActivity(getActivity());

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);

        ObservableExtensionKt.observe(model.lineWithStop(RouteType.TRAM, lineName), new ObservableListener<Line>() {
            @Override
            public void onLoading() {
                dialogLoad.show(getActivity().getSupportFragmentManager(), "LineMapFragment");
                Log.i("LineMapFragment", "Connexion in progress");
            }

            @Override
            public void onSuccess(Line data) {
                dialogLoad.dismiss();
                Log.i("LineMapFragment", "Connexion established");

                for (Stop item : data.getStops()) {

                    Double latitude = item.getPosition().getLatitude();
                    Double longitude = item.getPosition().getLongitude();

                    poi.add(new Poi(R.drawable.icon_maps_place_24px, getLineColor(lineName), latitude, longitude));
                }
                /* Add ArrayList to the map */
                addPois(poi);

            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                dialogLoad.dismiss();
                Log.i("LineMapFragment", "Network Error !");
            }
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NotNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        /* Set title activity to tram line */
        getActivity().setTitle(getContext().getString(R.string.stop_view_holer_line) + " " + lineName);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

    public Integer getLineColor(String lineName) {
        switch (lineName) {
            case "A":
                return R.color.Ligne_A;
            case "B":
                return R.color.Ligne_B;
            case "C":
                return R.color.Ligne_C;
            case "D":
                return R.color.Ligne_D;
            case "E":
                return R.color.Ligne_E;
            case "F":
                return R.color.Ligne_F;
            default:
                return R.color.Default;
        }
    }
}