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

    public recyclerMenuAdapter(ArrayList<Line> tramLines1) {
        tramLines = tramLines1;
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

        switch (currentTramLine.getName()) {
            case "Parc des Sports - Illkirch Graffenstaden":
                holder.iconLineTram.setImageResource(R.drawable.tram_a);
                break;
            case "Lingolsheim Tiergaertel - Hoenheim Gare":
                holder.iconLineTram.setImageResource(R.drawable.tram_b);
                break;
            case "Gare Centrale - Neuhof Rodolphe Reuss":
                holder.iconLineTram.setImageResource(R.drawable.tram_c);
                break;
            case "Poteries - Port du Rhin / Kehl Rathaus":
                holder.iconLineTram.setImageResource(R.drawable.tram_d);
                break;
            case "Robertsau l'Escale - Campus d'Illkirch":
                holder.iconLineTram.setImageResource(R.drawable.tram_e);
                break;
            case "Comtes - Place d'Islande":
                holder.iconLineTram.setImageResource(R.drawable.tram_f);
                break;
            default:
                break;

        }
    }

    @Override
    public int getItemCount() {
        return tramLines.size();
    }

}