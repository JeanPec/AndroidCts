package com.shindra.Schedule;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.HelperLine;
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
    public void onBind(ScheduleItem item, Context context) {
        mTramStationName.setText(item.GetTramStationName());
        mNextDepartureTime.setText(item.GetNextDepartureTime());
        mTramLineLetter.setText(context.getString(R.string.line) + " " + item.GetTramLineLetter());
        mTramLineLetter.setTextColor(ContextCompat.getColor(context, HelperLine.GetLineColor(item.GetTramLineLetter())));
    }
}