package com.shindra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerScheduleAdapter extends RecyclerView.Adapter<viewHolderSchedule>{

    private ArrayList<myStop> tramStops;
    private String tramLineSelected;
    private TextView stopName;
    private TextView stopScheduleTime;

    public RecyclerScheduleAdapter(ArrayList<myStop> tramStops1) {

        tramStops = tramStops1;
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
        myStop currentStop = tramStops.get(position);
        holder.lineTram.setText(currentStop.getLineName());
        holder.lineStation.setText(currentStop.getLineStation());
        holder.stopScheduleTime.setText(currentStop.getHours());
    }



    @Override
    public int getItemCount() {
        return tramStops.size();
    }
}
