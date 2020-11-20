package com.shindra.Schedule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.OurTrams.OurTramsItem;
import com.shindra.R;

import java.util.ArrayList;

public class ScheduleAdapter extends RecyclerView.Adapter<ScheduleViewHolder>{

    // Attributes
    private ArrayList<ScheduleItem> mScheduleList;

    // Constructor
    public ScheduleAdapter(ArrayList<ScheduleItem> arrayList){ mScheduleList = arrayList; }

    // Methods
    @NonNull
    @Override
    public ScheduleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.cv_schedule, parent, false);
        ScheduleViewHolder scheduleVH = new ScheduleViewHolder(v);
        return scheduleVH;
    }
    @Override
    public void onBindViewHolder(@NonNull ScheduleViewHolder holder, int position) {
        ScheduleItem currentItem = mScheduleList.get(position);
        holder.onBind(currentItem);
    }
    @Override
    public int getItemCount() { return mScheduleList.size(); }
    public void SetScheduleList(ArrayList<ScheduleItem> list){ mScheduleList = list; }
}
