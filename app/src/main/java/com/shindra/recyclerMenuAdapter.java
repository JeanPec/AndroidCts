package com.shindra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Line;

import java.util.ArrayList;

public class recyclerMenuAdapter extends RecyclerView.Adapter<viewHolderMenu> {

    private ArrayList<Line> tramLines;
    ScheduleButtonListener callback;

    public recyclerMenuAdapter(ArrayList<Line> tramLines1, ScheduleButtonListener callback1) {
        tramLines = tramLines1;
        callback = callback1;
    }



    @NonNull
    @Override
    public viewHolderMenu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View TramView = inflater.inflate(R.layout.custom_menu, parent, false);
        return new viewHolderMenu(TramView);
    }



    @Override
    public void onBindViewHolder(@NonNull viewHolderMenu holder, int position) {
        Line currentTramLine = tramLines.get(position);
        holder.iconLineTram.setImageResource(getLineImg(currentTramLine));
        holder.bindClick(currentTramLine, callback);
    }

    public void setTramLine(ArrayList<Line> tramLine1)
    {
        tramLines = tramLine1;
    }

    @Override
    public int getItemCount() {
        return tramLines.size();
    }

    int getLineImg(Line currentTramLine)
    {
        switch (currentTramLine.getName()) {
            case "Parc des Sports - Illkirch Graffenstaden":
                return (R.drawable.tram_a);

            case "Lingolsheim Tiergaertel - Hoenheim Gare":
                return(R.drawable.tram_b);

            case "Gare Centrale - Neuhof Rodolphe Reuss":
                return (R.drawable.tram_c);

            case "Poteries - Port du Rhin / Kehl Rathaus":
                return(R.drawable.tram_d);

            case "Robertsau l'Escale - Campus d'Illkirch":
                return(R.drawable.tram_e);

            case "Comtes - Place d'Islande":
                return(R.drawable.tram_f);

            default:
                return(R.drawable.tram);

        }

}




}