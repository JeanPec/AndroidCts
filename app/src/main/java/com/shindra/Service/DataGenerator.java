package com.shindra.Service;

import com.shindra.ctslibrary.apibo.Coordinate;
import com.shindra.ctslibrary.apibo.RouteType;
import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;

import java.util.ArrayList;
import java.util.Date;

public abstract class DataGenerator
{
    public static ArrayList<Line> generateLines()
    {
        ArrayList<Line> temp = new ArrayList<Line>();
        temp.add(new Line("Ligne A", RouteType.TRAM, generateStops()));
        temp.add(new Line("Ligne B", RouteType.TRAM, generateStops()));
        temp.add(new Line("Ligne C", RouteType.TRAM, generateStops()));
        temp.add(new Line("Ligne D", RouteType.TRAM, generateStops()));
        temp.add(new Line("Ligne E", RouteType.TRAM, generateStops()));
        temp.add(new Line("Ligne F", RouteType.TRAM, generateStops()));
        return temp;
    }

    public static ArrayList<Stop> generateStops()
    {
        Date currentDate = new Date(System.currentTimeMillis());
        ArrayList<Stop> temp = new ArrayList<Stop>();
        temp.add(new Stop("Parc des Sports", currentDate, currentDate, "Parc des Sports", new Coordinate()));
        temp.add(new Stop("Le Galet", currentDate, currentDate, "Parc des Sports", new Coordinate()));
        temp.add(new Stop("Cervantès", currentDate, currentDate, "Parc des Sports", new Coordinate()));
        temp.add(new Stop("Dante", currentDate, currentDate, "Parc des Sports", new Coordinate()));
        return temp;
    }
}
