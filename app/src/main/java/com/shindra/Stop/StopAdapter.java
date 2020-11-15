package com.shindra.Stop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.R;
import com.shindra.Service.Converter;
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
        holder.lineName.setText("Ligne " + Converter.lineNameToLineLetter(lineName));
        holder.lineName.setTextColor(ContextCompat.getColor(holder.lineName.getContext(), Converter.lineNameToLineColor(lineName)));
        holder.stopTime.setText(Converter.dateToTime(item.getEstimatedDepartureTime()));
        holder.information.setText("Prochain départ");
    }

    @Override
    public int getItemCount()
    {
        return stops.size();
    }

    public void setStops(ArrayList<Stop> stops)
    {
        this.stops = stops;
    }
}
