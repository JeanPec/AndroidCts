package com.shindra;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class viewHolderSchedule extends RecyclerView.ViewHolder {
     TextView lineStation;
     TextView lineTram;
     TextView stopScheduleTime;
        TextView nextStation;

    public viewHolderSchedule(@NonNull View itemView) {
        super(itemView);
        lineStation = itemView.findViewById(R.id.station_txt);
        lineTram  = itemView.findViewById(R.id.lineTram_txt);
        stopScheduleTime = itemView.findViewById(R.id.scheduleTime_txt);
        nextStation = itemView.findViewById(R.id.nextStation_txt);
    }
}
