package com.shindra;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class StopAdapter extends RecyclerView.Adapter<StopViewHolder>
{
    ArrayList<Stop> stops;
    String lineName;

    public StopAdapter(ArrayList<Stop> stops, String lineName)
    {
        this.stops = stops;
        this.lineName = lineName;
    }

    @NonNull
    @Override
    public StopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stop_view, parent, false);
        return new StopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StopViewHolder holder, int position)
    {
        Stop item = stops.get(position);
        holder.stopName.setText(item.getName());
        holder.lineName.setText(lineName);
        holder.lineName.setTextColor(ContextCompat.getColor(holder.lineName.getContext(),getLineColor(lineName)));
        holder.stopTime.setText(getTimeFromDate(item.getEstimatedDepartureTime()));
        holder.information.setText("Prochain départ");
    }

    @Override
    public int getItemCount()
    {
        return stops.size();
    }

    int getLineColor(String lineName)
    {
        switch (lineName)
        {
            case "Ligne A":
                return R.color.LineA;
            case "Ligne B":
                return R.color.LineB;
            case "Ligne C":
                return R.color.LineC;
            case "Ligne D":
                return R.color.LineD;
            case "Ligne E":
                return R.color.LineE;
            case "Ligne F":
                return R.color.LineF;
            default:
                return R.color.black;

        }
    }

    private String getTimeFromDate(Date date)
    {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.HOUR_OF_DAY) + "h" + calendar.get(Calendar.MINUTE);
    }
}
