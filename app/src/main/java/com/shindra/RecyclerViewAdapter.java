package com.shindra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.shindra.ctslibrary.bo.Line;

import java.util.*;


public class RecyclerViewAdapter extends RecyclerView.Adapter<TramViewHolder> {

    ArrayList<Line> listTrams;
    TramViewHolder.onButtonClickListener callback;

    RecyclerViewAdapter(ArrayList<Line> listTrams, TramViewHolder.onButtonClickListener callback){
        this.listTrams = listTrams;
        this.callback = callback;
    }

    @NonNull
    @Override
    public TramViewHolder onCreateViewHolder(@NonNull ViewGroup vgParent, int iViewType) {
        View vTrams = LayoutInflater.from(vgParent.getContext()).inflate(R.layout.tramlayout,vgParent,false);
        return new TramViewHolder(vTrams);
    }

    @Override
    public void onBindViewHolder(@NonNull TramViewHolder holder, int iPosition) {
        Line lLine = listTrams.get(iPosition);
        holder.ivTextTram.setImageResource(getTramIcon(lLine.getName()));
        holder.onBind(lLine,callback);
    }

    private int getTramIcon(String strLineName) {
        int iPosition;
        switch (strLineName) {

            case "A":
                iPosition = R.drawable.tram_a;
                break;
            case "B":
                iPosition = R.drawable.tram_b;
                break;
            case "C":
                iPosition = R.drawable.tram_c;
                break;
            case "D":
                iPosition = R.drawable.tram_d;
                break;
            case "E":
                iPosition = R.drawable.tram_e;
                break;
            case "F":
                iPosition = R.drawable.tram_f;
                break;
            default:
                iPosition = R.drawable.tram;
        }
        return (iPosition);
    }

    @Override
    public int getItemCount() {
        return listTrams.size();
    }

    public void setListTrams(ArrayList<Line> listTrams) {
        this.listTrams = listTrams;
    }
}
