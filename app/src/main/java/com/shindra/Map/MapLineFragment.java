package com.shindra.Map;

import com.shindra.R;
import com.shindra.Service.Converter;
import com.shindra.arrakis.controls.MapFragment;
import com.shindra.arrakis.controls.Poi;
import com.shindra.ctslibrary.bo.Stop;
import java.util.ArrayList;

public class MapLineFragment extends MapFragment
{
    public void addStopsOnMap(String lineName, ArrayList<Stop> stops)
    {
        ArrayList<Poi> pois = new ArrayList<Poi>();
        for (Stop item : stops)
            pois.add(new Poi(R.drawable.icon_maps_place_24px, Converter.lineNameToLineColor(lineName), item.getPosition().getLatitude(), item.getPosition().getLongitude()));
        addPois(pois);
    }
}
