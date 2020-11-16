package com.shindra;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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
}
