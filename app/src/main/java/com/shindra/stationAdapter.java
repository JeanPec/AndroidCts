package com.shindra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class stationAdapter extends RecyclerView.Adapter<stationAdapter.stationDiaryViewHolder>{

    private ArrayList<Stop> _listOfStop;
    private Line _line;

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

        public void onBind(Line line, Stop stop, int position){
            stationNameView.setText(stop.component1());
            scheduledTimeView.setText(new SimpleDateFormat("HH'h'mm").format(stop.component2()));
            lineNameView.setText(lineNameView.getContext().getString(R.string.ligneText) + line.getName());
            lineNameView.setTextColor(ContextCompat.getColor(lineNameView.getContext(),getLineColorFromLine(line)));
        }
    }

    public stationAdapter(Line line) {
        _line = line;
        _listOfStop = fillStopListFromLine(line);
    }

    @NonNull
    @Override
    public stationDiaryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View stationCardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_station, parent, false);
        stationAdapter.stationDiaryViewHolder stationDiaryCardVH = new stationAdapter.stationDiaryViewHolder(stationCardView);
        return stationDiaryCardVH;
    }

    @Override
    public void onBindViewHolder(@NonNull stationDiaryViewHolder holder, int position) {

        Stop currentStop = _listOfStop.get(position);
        holder.onBind(_line,currentStop, position);
    }

    @Override
    public int getItemCount() {
        return _listOfStop.size();
    }

    public static int getLineColorFromLine(Line line){

        switch (line.getName())
        {
            case "A":
                return R.color.ligneA;
            case "B":
                return R.color.ligneB;
            case "C":
                return R.color.ligneC;
            case "D":
                return R.color.ligneD;
            case "E":
                return R.color.ligneE;
            case "F":
                return R.color.ligneF;
            default:
                return R.color.black;
        }
    }

    private  ArrayList<Stop> fillStopListFromLine(Line data){
       ArrayList<Stop> tmpList = new ArrayList<Stop>();

        for (Stop stop : data.getStops())
        {
            tmpList.add(stop);
        }
        return tmpList;
    }

}
