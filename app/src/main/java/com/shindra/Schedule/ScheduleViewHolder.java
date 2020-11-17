package com.shindra.Schedule;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.R;
import com.shindra.Schedule.ScheduleItem;

public class ScheduleViewHolder extends RecyclerView.ViewHolder {

    // Attributes
    public TextView mTextLocation;
    public TextView mTextNextDepartureTime;
    public TextView mTextTramLine;

    // Constructor
    public ScheduleViewHolder(@NonNull View itemView) {
        super(itemView);
        mTextLocation = itemView.findViewById(R.id.cv_schedule_textLocation);
        mTextNextDepartureTime = itemView.findViewById(R.id.cv_schedule_textNextDepartureTime);
        mTextTramLine = itemView.findViewById(R.id.cv_schedule_textTramLine);
    }

    // Methods
    public void onBind(ScheduleItem arret)
    {
        mTextLocation.setText(arret.getmTextLocation());
        mTextNextDepartureTime.setText(arret.getmTextNextDepartureTime());
        mTextTramLine.setText(arret.getmTextTramLine());
    }
}