package com.shindra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Stop;

import org.jetbrains.annotations.NotNull;

import java.util.Date;
import java.util.ArrayList;

public class ScheduleRecyclerViewAdapter extends RecyclerView.Adapter<ScheduleRecyclerViewAdapter.ScheduleViewHolder> {
    private ArrayList<Stop> stops;
    private String lineID;
    public static class ScheduleViewHolder extends RecyclerView.ViewHolder {
        public TextView scheduleStop;
        public TextView scheduleLine;
        public TextView scheduleTime;

        public ScheduleViewHolder(View itemView) {
            super(itemView);
            scheduleStop = itemView.findViewById(R.id.Schedule_stop);
            scheduleLine = itemView.findViewById(R.id.Schedule_line);
            scheduleTime = itemView.findViewById(R.id.Schedule_time);
        }
    }
    public ScheduleRecyclerViewAdapter(ArrayList<Stop> stops, String lineID) {
        this.stops = stops;
        this.lineID = lineID;
    }
    @Override
    public ScheduleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.schedule_cardview, parent, false);
        ScheduleViewHolder evh = new ScheduleViewHolder(v);
        return evh;
    }
    @Override
    public void onBindViewHolder(ScheduleViewHolder holder, int position) {
        Stop currentItem = stops.get(position);

        holder.scheduleStop.setText(currentItem.getName());
        holder.scheduleLine.setText("Ligne " + lineID);
        if (lineID.equals("A")){
            holder.scheduleLine.setTextColor(ContextCompat.getColor(holder.scheduleLine.getContext(), R.color.ligne_A));
        }
        else if (lineID.equals("B")){
            holder.scheduleLine.setTextColor(ContextCompat.getColor(holder.scheduleLine.getContext(), R.color.ligne_B));
        }
        else if (lineID.equals("C")){
            holder.scheduleLine.setTextColor(ContextCompat.getColor(holder.scheduleLine.getContext(), R.color.ligne_C));
        }
        else if (lineID.equals("D")){
            holder.scheduleLine.setTextColor(ContextCompat.getColor(holder.scheduleLine.getContext(), R.color.ligne_D));
        }
        else if (lineID.equals("E")){
            holder.scheduleLine.setTextColor(ContextCompat.getColor(holder.scheduleLine.getContext(), R.color.ligne_E));
        }
        else if (lineID.equals("F")){
            holder.scheduleLine.setTextColor(ContextCompat.getColor(holder.scheduleLine.getContext(), R.color.ligne_F));
        }
        else {
            holder.scheduleLine.setTextColor(ContextCompat.getColor(holder.scheduleLine.getContext(), R.color.Body2));
        }
        holder.scheduleTime.setText(convert(currentItem.getEstimatedDepartureTime()));


    }

    @NotNull
    public static String convert (Date input) {
        if (input != null){
            int hours = input.getHours();
            return String.format("%dh%02d",hours,input.getMinutes());
        }
        else{
            return "";
        }
    }

    @Override
    public int getItemCount() {
        return stops.size();
    }
}
