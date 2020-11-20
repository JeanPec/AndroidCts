package com.shindra;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shindra.arrakis.controls.MapFragment;
import com.shindra.arrakis.controls.Poi;
import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;

import java.util.ArrayList;

public class mapFragment extends MapFragment {

    public void addPoi(Line data){

        ArrayList<Poi> listOfPoi = new ArrayList<Poi>() ;

        for (Stop stop: data.getStops()) {

                listOfPoi.add(new Poi(R.drawable.icon_maps_place_24px,getLineColorFromLine(data),stop.component5().getLatitude(),stop.component5().getLongitude()));
            Log.i("Main","Line Name = " + data.getName());
        }
        Log.i("Main","Line Name = " + data.getName());
        addPois(listOfPoi);
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
