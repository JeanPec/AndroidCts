package com.shindra.Schedule;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.CT;
import com.shindra.R;

public class ScheduleViewHolder extends RecyclerView.ViewHolder {

    // Attributes
    private TextView mNextDepartureTime;
    private TextView mTramStationName;
    private TextView mTramLineLetter;

    // Constructor
    public ScheduleViewHolder(@NonNull View itemView) {
        super(itemView);
        mTramStationName = itemView.findViewById(R.id.cv_schedule_textTramStation);
        mNextDepartureTime = itemView.findViewById(R.id.cv_schedule_textNextDepartureTime);
        mTramLineLetter = itemView.findViewById(R.id.cv_schedule_textTramLineLetter);
    }

    // Methods
    public void onBind(ScheduleItem item) {
        mTramStationName.setText(item.GetTramStationName());
        mNextDepartureTime.setText(item.GetNextDepartureTime());
        mTramLineLetter.setText("Ligne " + item.GetTramLineLetter());
        int testcolor = CT.GetLineColor(item.GetTramLineLetter());
        mTramLineLetter.setTextColor(CT.GetLineColor(item.GetTramLineLetter()));
    }
}