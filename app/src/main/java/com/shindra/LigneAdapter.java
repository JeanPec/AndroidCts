package com.shindra;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import com.shindra.ctslibrary.bo.Line;

import java.util.ArrayList;

public class LigneAdapter extends RecyclerView.Adapter<LigneViewHolder> {
    ArrayList<Line> line;
    RecyclerItemClick callback;

    LigneAdapter(ArrayList<Line> line, RecyclerItemClick callback){
        this.line = line;
        this.callback = callback;
    }

    @NonNull
    @Override
    public LigneViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_ligne,parent,false);
        return new LigneViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LigneViewHolder holder, int position) {
        Line newLigne = line.get(position);
        holder.image.setImageResource(getImage(newLigne));
        holder.OnBind(newLigne, callback);
    }

    @Override
    public int getItemCount() {
        return line.size();
    }

    public void setLine(ArrayList<Line> line)
    {
        this.line = line;
    }

    private int getImage(Line line)
    {
        switch (line.getName())
        {
            case "Parc des Sports - Illkirch Graffenstaden":
                return R.drawable.ic_tram_a;
            case "Lingolsheim Tiergaertel - Hoenheim Gare":
                return R.drawable.ic_tram_b;
            case "Gare Centrale - Neuhof Rodolphe Reuss":
                return R.drawable.ic_tram_c;
            case "Poteries - Port du Rhin / Kehl Rathaus":
                return R.drawable.ic_tram_d;
            case "Robertsau l'Escale - Campus d'Illkirch":
                return R.drawable.ic_tram_e;
            case "Comtes - Place d'Islande":
                return R.drawable.ic_tram_f;
            default:
                return R.drawable.ic_tram;
        }
    }
}
