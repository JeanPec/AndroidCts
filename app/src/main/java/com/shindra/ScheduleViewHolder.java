package com.shindra;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Stop;


public class ScheduleViewHolder extends RecyclerView.ViewHolder {

    public ScheduleViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void onBind(Stop stop){
        //lineName.setText(line.na);
    }

}
