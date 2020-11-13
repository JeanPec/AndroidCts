package com.shindra;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class LineViewHolder extends RecyclerView.ViewHolder
{
    public ImageView TramIcon;
    public ImageView TramPicture;
    public Button ScheduleButton;

    public LineViewHolder(@NonNull View itemView) {
        super(itemView);
        this.TramIcon = (ImageView)itemView.findViewById(R.id.tramIcon);
        this.TramPicture = (ImageView)itemView.findViewById(R.id.tramPicture);
        this.ScheduleButton = (Button)itemView.findViewById(R.id.scheduleButton);
    }
}
