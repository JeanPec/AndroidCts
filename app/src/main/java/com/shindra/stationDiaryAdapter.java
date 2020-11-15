package com.shindra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class stationDiaryAdapter extends RecyclerView.Adapter<stationDiaryAdapter.stationDiaryViewHolder>{

    private ArrayList<stationDiaryCardview> _stationDiaryList;

    public static class stationDiaryViewHolder extends RecyclerView.ViewHolder{
        public TextView stationNameView;
        public TextView lineNameView;
        public TextView scheduledTimeView;

        public stationDiaryViewHolder(@NonNull View itemView) {
            super(itemView);
            stationNameView = itemView.findViewById(R.id.stationName);
            lineNameView = itemView.findViewById(R.id.tramLine);
            scheduledTimeView = itemView.findViewById(R.id.scheduledTime);
        }
    }

    public stationDiaryAdapter(ArrayList<stationDiaryCardview> stationDiaryList) {
        _stationDiaryList = stationDiaryList;
    }


    @NonNull
    @Override
    public stationDiaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View stationDiaryCardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_station_diary, parent, false);
        stationDiaryAdapter.stationDiaryViewHolder stationDiaryCardVH = new stationDiaryAdapter.stationDiaryViewHolder(stationDiaryCardView);
        return stationDiaryCardVH;
    }

    @Override
    public void onBindViewHolder(@NonNull stationDiaryViewHolder holder, int position) {
        stationDiaryCardview currentStationDiary = _stationDiaryList.get(position);

        holder.stationNameView.setText(currentStationDiary.getStationName());
        holder.lineNameView.setText(currentStationDiary.getLineName());
        holder.scheduledTimeView.setText(currentStationDiary.getScheduledTime());
    }

    @Override
    public int getItemCount() {
        return _stationDiaryList.size();
    }



}
