package com.shindra.TramSchedule;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.R;
import com.shindra.ctslibrary.bo.Stop;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Trams_Schedule_Holder extends RecyclerView.ViewHolder {

    public final TextView Stop_Name;
    public final TextView Line_Tram;
    public final TextView Departure_Time;
    public final TextView Next_Departure;


    public Trams_Schedule_Holder(@NonNull View itemView)
    {
        super(itemView);
        Stop_Name = itemView.findViewById(R.id.Stop_Name);
        Line_Tram = itemView.findViewById(R.id.Line_Tram);
        Departure_Time = itemView.findViewById(R.id.Departure_Time);
        Next_Departure = itemView.findViewById(R.id.Next_Departure);

    }

    public void onBind(Stop requestStopTram, String LineName)
    {
        Integer LineColor = itemView.getContext().getColor(GetLineColor(LineName));
        Line_Tram.setTextColor(LineColor);
        Line_Tram.setText("ligne " +  LineName);

        Departure_Time.setText(DateToString(requestStopTram.getEstimatedDepartureTime()));
        Stop_Name.setText(requestStopTram.getName());
    }

    private String DateToString(Date date)
    {
        SimpleDateFormat Date_Format = new SimpleDateFormat("HH:mm");
        return Date_Format.format(date);
    }

    private int GetLineColor(String LineLetter)
    {
        int color;

        switch (LineLetter)
        {
            case "A":
                color = R.color.Ligne_A;
                break;

            case "B":
                color = R.color.Ligne_B;
                break;

            case "C":
                color = R.color.Ligne_C;
                break;

            case "D":
                color = R.color.Ligne_D;
                break;

            case "E":
                color = R.color.Ligne_E;
                break;

            case "F":
                color = R.color.Ligne_F;
                break;

            default:
                color = R.color.black;
                break;
        }
        return color;
    }

}
