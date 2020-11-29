package com.shindra.Timetable;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.R;
import com.shindra.ctslibrary.bo.Stop;

import java.util.ArrayList;


public class TimetableAdapter extends RecyclerView.Adapter<TimetableViewHolder> {

    private ArrayList<Stop> ListArret;
    private String nomLigne;

    TimetableAdapter (ArrayList<Stop> ListArret, String nomLigne)
    {
        this.ListArret = ListArret;
        this.nomLigne = nomLigne;
    }

    @NonNull
    @Override
    public TimetableViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View timeTableView = LayoutInflater.from(parent.getContext()).inflate(R.layout.horaire_item, parent, false);
        return new TimetableViewHolder(timeTableView);
    }

    @Override
    public void onBindViewHolder(@NonNull TimetableViewHolder holder, int position) {
        Stop infoStop = ListArret.get(position);
        holder.onBind(infoStop, nomLigne);//Compléter la méthode on Bind
    }

    @Override
    public int getItemCount() {
                return ListArret.size();
    }
}
