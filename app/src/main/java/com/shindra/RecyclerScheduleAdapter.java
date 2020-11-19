package com.shindra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Stop;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class RecyclerScheduleAdapter extends RecyclerView.Adapter<viewHolderSchedule>{

    private ArrayList<Stop> tramStops;
    private String tramLineSelected;
    private TextView stopName;
    private TextView stopScheduleTime;

    public RecyclerScheduleAdapter(ArrayList<Stop> tramStops1, String tramLineSelected1) {

        tramStops = tramStops1;
        tramLineSelected = tramLineSelected1;
    }

   /* public RecyclerScheduleAdapter(ArrayList<myStop> tramStops1, String tramLineSelected1) {
        tramStops = tramStops1;
        tramLineSelected = tramLineSelected1;
    }*/


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
        holder.lineTram.setText(tramLineSelected);
        holder.lineStation.setText(currentStop.getName());
        holder.stopScheduleTime.setText(getDate(currentStop.getEstimatedDepartureTime()));
    }

    public String getDate(Date date)
    {
        SimpleDateFormat s = new SimpleDateFormat("h:mm");
        return s.format(date);
    }





    @Override
    public int getItemCount() {
        return tramStops.size();
    }
}
