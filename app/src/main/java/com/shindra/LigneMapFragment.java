package com.shindra;

import android.util.Log;

import com.shindra.arrakis.controls.MapFragment;
import com.shindra.ctslibrary.bo.Line;
import com.shindra.arrakis.controls.Poi;
import com.shindra.ctslibrary.bo.Stop;

import java.util.ArrayList;

public class LigneMapFragment extends MapFragment
{
    public void addPointsOnCard(Line line)
    {
        ArrayList<Poi> poi = new ArrayList<>();
        for(Stop stop : line.getStops())
        {
            //Log.i("Main", stop.getName() + stop.component5());
            //Log.i("Main", line.getName());
            Double latitude = stop.getPosition().getLatitude();
            Double longitude = stop.getPosition().getLongitude();
            poi.add(new Poi(R.drawable.icon_maps_place_24px, getColor(line.getName()), latitude, longitude));
        }
        addPois(poi);
    }

    private int getColor(String lineRef)
    {
        switch (lineRef)
        {
            case "A":
                return R.color.ligne_a;
            case "B":
                return R.color.ligne_b;
            case "C":
                return R.color.ligne_c;
            case "D":
                return R.color.ligne_d;
            case "E":
                return R.color.ligne_e;
            case "F":
                return R.color.ligne_f;
            default:
                return R.color.black;
        }
    }
}
