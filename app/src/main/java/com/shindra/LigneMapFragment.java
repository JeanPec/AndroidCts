package com.shindra;

import com.shindra.arrakis.controls.MapFragment;
import com.shindra.arrakis.controls.Poi;
import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;

import java.util.ArrayList;

public class LigneMapFragment extends MapFragment {
    public void Marqueur(Line line){
        ArrayList<Poi> pois = new ArrayList<>();
        for (Stop stop : line.getStops()){
            Double x = stop.getPosition().getLatitude();
            Double y = stop.getPosition().getLongitude();
            pois.add(new Poi(R.drawable.icon_maps_place_24px,Couleur(line.getName()), x, y));
        }
        addPois(pois);
    }

private int Couleur(String nomLigne){
        switch (nomLigne){
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
