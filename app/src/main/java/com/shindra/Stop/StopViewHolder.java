package com.shindra.Stop;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.R;

import org.w3c.dom.Text;

public class StopViewHolder extends RecyclerView.ViewHolder
{
    public TextView stopName;
    public TextView lineName;
    public TextView stopTime;
    public TextView information;

    public StopViewHolder(@NonNull View itemView)
    {
        super(itemView);
        stopName = itemView.findViewById(R.id.stopName);
        lineName = itemView.findViewById(R.id.lineName);
        stopTime = itemView.findViewById(R.id.stopTime);
        information = itemView.findViewById(R.id.information);
    }
}
