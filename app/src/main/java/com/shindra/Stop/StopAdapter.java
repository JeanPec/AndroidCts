package com.shindra.Stop;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.R;
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
            case "Parc des Sports - Illkirch Graffenstaden":
                return R.color.LineA;
            case "Lingolsheim Tiergaertel - Hoenheim Gare":
                return R.color.LineB;
            case "Gare Centrale - Neuhof Rodolphe Reuss":
                return R.color.LineC;
            case "Poteries - Port du Rhin / Kehl Rathaus":
                return R.color.LineD;
            case "Robertsau l'Escale - Campus d'Illkirch":
                return R.color.LineE;
            case "Comtes - Place d'Islande":
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
