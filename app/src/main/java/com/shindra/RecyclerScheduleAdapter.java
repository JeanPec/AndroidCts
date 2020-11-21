package com.shindra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Stop;

import java.util.ArrayList;

public class RecyclerScheduleAdapter extends RecyclerView.Adapter<viewHolderSchedule>{

    private ArrayList<Stop> tramStops;
    private String tramLineSelected;
    private String lineText;

    public RecyclerScheduleAdapter(ArrayList<Stop> tramStops1, String tramLineSelected1, String lineText1) {

        tramStops = tramStops1;
        tramLineSelected = tramLineSelected1;
        lineText = lineText1;
    }


    @NonNull
    @Override
    public viewHolderSchedule onCreateViewHolder(@NonNull ViewGroup parent , int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View scheduleView = inflater.inflate(R.layout.schedule_card_view, parent, false);
        return new viewHolderSchedule(scheduleView);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolderSchedule holder , int position) {
        Stop currentStop = tramStops.get(position);
        holder.onBind(currentStop,lineText,tramLineSelected);
    }

    @Override
    public int getItemCount() {
        return tramStops.size();
    }
}
