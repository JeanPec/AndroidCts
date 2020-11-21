package com.shindra.Menu;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.R;
import com.shindra.ctslibrary.bo.Line;

import java.util.ArrayList;

public class RecyclerMenuAdapter extends RecyclerView.Adapter<viewHolderMenu> {

    private ArrayList<Line> tramLines;
    ScheduleButtonListener callback;

    public RecyclerMenuAdapter(ArrayList<Line> tramLines1, ScheduleButtonListener callback1) {
        tramLines = tramLines1;
        callback = callback1;
    }



    @NonNull
    @Override
    public viewHolderMenu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View TramView = inflater.inflate(R.layout.menu_card_view , parent, false);
        return new viewHolderMenu(TramView);
    }



    @Override
    public void onBindViewHolder(@NonNull viewHolderMenu holder, int position) {
        Line currentTramLine = tramLines.get(position);
        holder.onBind(currentTramLine, callback);
    }

    public void setTramLine(ArrayList<Line> tramLine1)
    {
        tramLines = tramLine1;
    }

    @Override
    public int getItemCount() {
        return tramLines.size();
    }


}




