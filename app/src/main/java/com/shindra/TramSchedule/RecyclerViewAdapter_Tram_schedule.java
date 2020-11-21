package com.shindra.TramSchedule;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.R;
import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;

import java.util.ArrayList;

public class RecyclerViewAdapter_Tram_schedule extends RecyclerView.Adapter<Trams_Schedule_Holder>
{
    private ArrayList<Stop> TramStopList;
    private String StopListName;


    public RecyclerViewAdapter_Tram_schedule(ArrayList<Stop> TramStopList, String StopListName)
    {
        this.TramStopList = TramStopList;
        this.StopListName = StopListName;

    }

    @NonNull
    @Override
    public Trams_Schedule_Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View ScheduleView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tram_lines_view, parent, false);
        return new Trams_Schedule_Holder(ScheduleView);
    }

    @Override
    public void onBindViewHolder(@NonNull Trams_Schedule_Holder holder, int position)
    {
        Stop requestTramLine = TramStopList.get(position);
        holder.onBind(requestTramLine, StopListName);

    }

    @Override
    public int getItemCount()
    {
        return TramStopList.size();
    }

}
