package com.shindra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Stop;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RecyclerScheduleAdapter extends RecyclerView.Adapter<viewHolderSchedule>{

    private ArrayList<Stop> tramStops;
    private String tramLineSelected;
    private String lineText;

    public RecyclerScheduleAdapter(ArrayList<Stop> tramStops1, String tramLineSelected1, String lineText1) {

        tramStops = tramStops1;
        tramLineSelected = tramLineSelected1;
        lineText = lineText1;
    }


    @NonNull
    @Override
    public viewHolderSchedule onCreateViewHolder(@NonNull ViewGroup parent , int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View scheduleView = inflater.inflate(R.layout.schedule_card_view, parent, false);
        return new viewHolderSchedule(scheduleView);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderSchedule holder , int position) {
        Stop currentStop = tramStops.get(position);
        holder.lineTram.setText(lineText + " " + tramLineSelected);
        holder.lineTram.setTextColor(ContextCompat.getColor(holder.lineTram.getContext(),getColor(tramLineSelected)));
        holder.lineStation.setText(currentStop.getName());
        holder.stopScheduleTime.setText(getDate(currentStop.getEstimatedDepartureTime()));
    }

    public String getDate(Date date)
    {
        SimpleDateFormat s = new SimpleDateFormat("HH'h'mm");
        return s.format(date);
    }


    public int getColor(String tramLineSelected)
    {
        switch  (tramLineSelected)
        {
        case "A":
        return (R.color.Ligne_A);

        case "B":
        return(R.color.Ligne_B);

        case "C":
        return (R.color.Ligne_C);

        case "D":
        return(R.color.Ligne_D);

        case "E":
        return(R.color.Ligne_E);

        case "F":
        return(R.color.Ligne_F);

        default:
        return(R.color.black);

         }

    }




    @Override
    public int getItemCount() {
        return tramStops.size();
    }
}
