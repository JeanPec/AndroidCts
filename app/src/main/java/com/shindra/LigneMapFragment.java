package com.shindra;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.arrakis.controls.MapFragment;
import com.shindra.arrakis.controls.Poi;
import com.shindra.arrakis.observable.ObservableExtensionKt;
import com.shindra.arrakis.observable.ObservableListener;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class LigneMapFragment extends MapFragment {

    String strLineName;
    LoadingDialog loadingPage;

    public static LigneMapFragment newInstance(String strLineName) {

        Bundle args = new Bundle();
        LigneMapFragment fragment = new LigneMapFragment();
        args.putString("lineName", strLineName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onStart(){

        super.onStart();

        loadingPage = new LoadingDialog(getActivity());

        strLineName = getArguments().getString("lineName", getContext().getString(R.string.defaultLineName));
        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);
        ObservableExtensionKt.observe(model.lineWithStop(RouteType.TRAM, strLineName), new ObservableListener<Line>() {
            @Override
            public void onLoading() {
                loadingPage.show();
            }

            @Override
            public void onSuccess(Line data) {
                loadingPage.dismiss();
                setMarqueur(data);
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
                loadingPage.dismiss();
            }
        });
    }

    public void setMarqueur(Line lLine){
        ArrayList<Poi> poiPositions = new ArrayList<>();
        for (Stop stops : lLine.getStops()){
            Double dX = stops.getPosition().getLatitude();
            Double dY = stops.getPosition().getLongitude();
            poiPositions.add(new Poi(R.drawable.icon_maps_place_24px,setColor(lLine.getName()),dX,dY));
        }
        addPois(poiPositions);
    }

    public int setColor(String strLineName){
        switch (strLineName){
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
                return R.color.HeadLine;
        }
    }
}
