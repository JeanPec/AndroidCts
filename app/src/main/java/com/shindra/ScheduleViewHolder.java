package com.shindra;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import retrofit2.Converter;

public class ScheduleViewHolder extends RecyclerView.ViewHolder {

    private final TextView lineNameView;
    private final TextView time;
    private final TextView stop;

    public ScheduleViewHolder(@NonNull View itemView){
        super(itemView);
        lineNameView = itemView.findViewById(R.id.line_name);
        time = itemView.findViewById(R.id.time);
        stop = itemView.findViewById(R.id.stop);

    }
    public void onBind(Stop stop_element, String lineName){
        lineNameView.setText("Ligne " + lineName);
        stop.setText(stop_element.getName());
        time.setText(convertDateToString(stop_element.getEstimatedDepartureTime()));

    }

    private String convertDateToString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("h'h'mm");
        String strDate= formatter.format(date);
        return strDate;
    }


}
