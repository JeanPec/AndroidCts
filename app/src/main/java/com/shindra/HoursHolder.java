package com.shindra;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Stop;

import java.text.SimpleDateFormat;

public class HoursHolder extends RecyclerView.ViewHolder {

    public TextView tvStop;
    public TextView tvLine;
    public TextView tvTime;
    HoursAdapter adapter = new HoursAdapter();

    public HoursHolder(@NonNull View itemView){
        super(itemView);
        tvStop = itemView.findViewById(R.id.textStop);
        tvLine = itemView.findViewById(R.id.texLline);
        tvTime = itemView.findViewById(R.id.textHours);
    }


}
