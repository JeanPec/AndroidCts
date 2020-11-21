package com.shindra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.arrakis.cache.Cache;
import com.shindra.ctslibrary.bo.Stop;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class HoursAdapter extends RecyclerView.Adapter<HoursHolder>{

    public ArrayList<Stop> stops;
    String strLineName;

    public HoursAdapter(String strLineName, ArrayList<Stop> stopList) {
        this.stops = stopList;
        this.strLineName = strLineName;
    }

    public HoursAdapter() {

    }

    @NonNull
    @Override
    public HoursHolder onCreateViewHolder(@NonNull ViewGroup vgParent, int viewType) {
        View vStop = LayoutInflater.from(vgParent.getContext()).inflate(R.layout.hourslayout,vgParent,false);
        return new HoursHolder(vStop);
    }

    @Override
    public void onBindViewHolder(@NonNull HoursHolder holder, int iPosition) {
        Stop sStop = stops.get(iPosition);
        holder.tvStop.setText(sStop.getName());
        holder.tvLine.setText(getLine(strLineName));
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH'h'mm");
        holder.tvTime.setText(simpleDateFormat.format(sStop.getEstimatedDepartureTime()));

    }

    private static String getLine(String strLineName) {
        String strLine;
        switch (strLineName)
        {
            case "A":
                strLine = "Ligne A";
                break;
            case "B":
                strLine = "Ligne B";
                break;
            case "C":
                strLine = "Ligne C";
                break;
            case "D":
                strLine = "Ligne D";
                break;
            case "E":
                strLine = "Ligne E";
                break;
            case "F":
                strLine = "Ligne F";
                break;
            default:
                strLine = " ";
        }
        return strLine;
    }

    @Override
    public int getItemCount() {
        return stops.size();
    }

    public void setStopList(ArrayList<Stop> stops){
        this.stops = stops;
    }
}
