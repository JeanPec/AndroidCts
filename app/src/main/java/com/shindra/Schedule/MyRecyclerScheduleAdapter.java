package com.shindra.Schedule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.R;
import com.shindra.ctslibrary.bo.Stop;

import java.util.ArrayList;

public class MyRecyclerScheduleAdapter extends RecyclerView.Adapter<MyViewHolderSchedule>{

    private ArrayList<Stop> tramStops;
    private String tramLineSelected;
    private String lineText;

    public MyRecyclerScheduleAdapter(ArrayList<Stop> tramStops1, String tramLineSelected1, String lineText1) {

        tramStops = tramStops1;
        tramLineSelected = tramLineSelected1;
        lineText = lineText1;
    }


    @NonNull
    @Override
    public MyViewHolderSchedule onCreateViewHolder(@NonNull ViewGroup parent , int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View scheduleView = inflater.inflate(R.layout.schedule_card_view, parent, false);
        return new MyViewHolderSchedule(scheduleView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderSchedule holder , int position) {
        Stop currentStop = tramStops.get(position);
        holder.onBind(currentStop,lineText,tramLineSelected);
    }

    @Override
    public int getItemCount() {
        return tramStops.size();
    }

    public void setListStop(ArrayList<Stop> tramStops)
    {
        this.tramStops = tramStops;
    }
}
