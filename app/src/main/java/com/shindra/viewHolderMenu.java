package com.shindra;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Line;

public class viewHolderMenu extends RecyclerView.ViewHolder {

    ImageView iconLineTram;
    ImageView imgTram;
    Button scheduleTimeButton;

    public viewHolderMenu(@NonNull View itemView) {
        super(itemView);
        iconLineTram = itemView.findViewById(R.id.tramLineImg_onLayout);
        imgTram = itemView.findViewById(R.id.tramImg_onLayout);
        scheduleTimeButton = itemView.findViewById(R.id.scheduleTimeButton);
    }

    void onBind(Line line , ScheduleButtonListener callback) {

        iconLineTram.setImageResource(getLineImg(line));

        scheduleTimeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onClick(line);
            }
        });
    }
    int getLineImg(Line currentTramLine) {
        switch (currentTramLine.getName()) {
            case "A":
                return (R.drawable.tram_a);

            case "B":
                return (R.drawable.tram_b);

            case "C":
                return (R.drawable.tram_c);

            case "D":
                return (R.drawable.tram_d);

            case "E":
                return (R.drawable.tram_e);

            case "F":
                return (R.drawable.tram_f);

            default:
                return (R.drawable.tram);

        }
    }

}