package com.shindra.Schedule;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.R;
import com.shindra.ctslibrary.bo.Stop;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyViewHolderSchedule extends RecyclerView.ViewHolder {
     TextView lineStation;
     TextView lineTram;
     TextView stopScheduleTime;
        TextView nextStation;

    public MyViewHolderSchedule(@NonNull View itemView) {
        super(itemView);
        lineStation = itemView.findViewById(R.id.station_txt);
        lineTram  = itemView.findViewById(R.id.lineTram_txt);
        stopScheduleTime = itemView.findViewById(R.id.scheduleTime_txt);
        nextStation = itemView.findViewById(R.id.nextStation_txt);
    }



    public void  onBind(Stop line, String lineText, String tramLineSelected)
    {
        lineTram.setText(lineText + " " + tramLineSelected);
        lineTram.setTextColor(ContextCompat.getColor(lineTram.getContext(),getColor(tramLineSelected)));
        lineStation.setText(line.getName());
        stopScheduleTime.setText(getDate(line.getEstimatedDepartureTime()));

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

}
