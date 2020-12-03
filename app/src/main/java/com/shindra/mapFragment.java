package com.shindra;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
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

import static androidx.core.content.ContextCompat.getSystemService;

public class mapFragment extends MapFragment {

    String ligne;
    ArrayList<Poi> mapPoint;


    public mapFragment(String ligne) {
        super();
        this.ligne=ligne;
        mapPoint = new ArrayList<Poi>();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState){
        super.onViewCreated(view, savedInstanceState);
        int couleur;

        switch (ligne){
            case "A":
                couleur = Color.parseColor("#D8232A");
                break;
            case "B":
                couleur =Color.parseColor("#00A3D4");
                break;
            case "C":
                couleur =Color.parseColor("#EB8B2D");
                break;
            case "D":
                couleur = Color.parseColor("#009F4A");
                break;
            case "E":
                couleur =Color.parseColor("#7F78AB");
                break;
            case "F":
                couleur =Color.parseColor("#84BF41");
                break;
            default:
                couleur =Color.parseColor("#000000");
                break;
        }

       // LayoutInflater inflater = (LayoutInflater) view.getContext().getSystemService(view.getContext().LAYOUT_INFLATER_SERVICE);
       // PopupWindow pw = new PopupWindow(inflater.inflate(R.layout.loadingpopup,null,false),100,100,true);

        MyViewModel model = new ViewModelProvider(this).get(MyViewModel.class);

        ObservableExtensionKt.observe(model.lineWithStop(RouteType.TRAM, ligne), new ObservableListener<Line>() {
            @Override
            public void onLoading() {
               // pw.showAtLocation(view, Gravity.CENTER, 0,0);
            }

            @Override
            public void onSuccess(Line data) {
                for(Stop iStop : data.getStops()){
                    mapPoint.add(new Poi(R.drawable.icon_maps_place_24px,couleur, iStop.getPosition().getLatitude(),iStop.getPosition().getLongitude()));
                }
                addPois(mapPoint);
            //    pw.dismiss();
            }

            @Override
            public void onError(@NotNull Throwable throwable) {
             //   pw.dismiss();
            }
        });
    }

}
