package com.shindra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Stop;

import java.util.ArrayList;

public class ScheduleRecyclerViewAdapter extends RecyclerView.Adapter<ScheduleViewHolder> {
    private ArrayList<Stop>stops;
    String lineName;
    Context context;

    ScheduleRecyclerViewAdapter(ArrayList<Stop> stops, String lineName, Context context){
        this.stops = stops;
        this.lineName = lineName;
        this.context = context;
    }

    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View lineView = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.schedule_recycler_card_view, parent, false);
        return new ScheduleViewHolder(lineView);
    }

    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position){
        holder.onBind(stops.get(position), lineName, context);
    }

    @Override
    public int getItemCount(){
        return stops.size();
    }

}
