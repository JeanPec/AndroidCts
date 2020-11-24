package com.shindra;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Stop;


import java.text.SimpleDateFormat;
import java.util.Date;

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
    public void onBind(Stop stop_element, String lineName, Context context){
        lineNameView.setText("Ligne " + lineName);
        setLineNameViewColor(lineName, context);
        stop.setText(stop_element.getName());
        time.setText(convertDateToString(stop_element.getEstimatedDepartureTime()));

    }
    private void setLineNameViewColor(String name, Context context){
        switch(name) {

            case "A":
                lineNameView.setTextColor(ContextCompat.getColor(context, R.color.ligne_a));
                break;
            case "B":
                lineNameView.setTextColor(ContextCompat.getColor(context, R.color.ligne_b));
                break;
            case "C":
                lineNameView.setTextColor(ContextCompat.getColor(context, R.color.ligne_c));
                break;
            case "D":
                lineNameView.setTextColor(ContextCompat.getColor(context, R.color.ligne_d));
                break;
            case "E":
                lineNameView.setTextColor(ContextCompat.getColor(context, R.color.ligne_e));
                break;
            case "F":
                lineNameView.setTextColor(ContextCompat.getColor(context, R.color.ligne_f));
                break;
            default:
                lineNameView.setTextColor(ContextCompat.getColor(context, R.color.body_2));
        }
    }

    private String convertDateToString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("h'h'mm");
        String strDate= formatter.format(date);
        return strDate;
    }


}
