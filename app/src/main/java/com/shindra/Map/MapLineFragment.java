package com.shindra.Map;

import com.shindra.R;
import com.shindra.Service.Converter;
import com.shindra.arrakis.controls.MapFragment;
import com.shindra.arrakis.controls.Poi;
import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;
import java.util.ArrayList;

public class MapLineFragment extends MapFragment
{
    private String lineName;

    public MapLineFragment(String lineName)
    {
        this.lineName = lineName;
    }

    public void addStopsOnMap(Line line)
    {
        ArrayList<Poi> pois = new ArrayList<Poi>();
        for (Stop item : line.getStops())
            pois.add(new Poi(R.drawable.icon_maps_place_24px, Converter.lineLetterToLineColor(line.getName()), item.getPosition().getLatitude(), item.getPosition().getLongitude()));
        addPois(pois);
    }

    public String getLineName()
    {
        return lineName;
    }
}
