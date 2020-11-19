package com.shindra;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.Resources;
import android.graphics.Color;
import android.icu.util.TimeZone;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.shindra.ctslibrary.bo.Stop;

import java.text.SimpleDateFormat;

public class StopViewHolder extends RecyclerView.ViewHolder {

    public TextView lineName;
    public TextView stopName;
    public TextView departureTime;
    public TextView info;

    public StopViewHolder(@NonNull View itemView) {
        super(itemView);
        lineName = itemView.findViewById(R.id.lineName);
        stopName = itemView.findViewById(R.id.stopName);
        departureTime = itemView.findViewById(R.id.departureTime);
        info = itemView.findViewById(R.id.info);
    }

    public void OnBind(Stop stops, String lineName){
        String lineText = itemView.getContext().getString(R.string.stop_view_holer_line);
        Integer LineColor = itemView.getContext().getColor(getLineColor(lineName));

        this.lineName.setText(lineText + " " + lineName);
        this.lineName.setTextColor(LineColor);

        stopName.setText(stops.getName());

        departureTime.setText(getFormattedDate(stops));

        info.setText(R.string.stop_view_holer_info);
    }

    public String getFormattedDate(Stop stops){
        SimpleDateFormat df = new SimpleDateFormat("HH:mm");
        String formattedDate = df.format(stops.getEstimatedDepartureTime()).toString();

        return formattedDate;
    }

    public Integer getLineColor (String lineName) {
        switch (lineName) {
            case "A":
                return R.color.Ligne_A;
            case "B":
                return R.color.Ligne_B;
            case "C":
                return R.color.Ligne_C;
            case "D":
                return R.color.Ligne_D;
            case "E":
                return R.color.Ligne_E;
            case "F":
                return R.color.Ligne_F;
            default:
                return R.color.Ligne_A;
        }

    }

}