package com.shindra;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Line;

import java.util.ArrayList;



public class LinesRecyclerViewAdapter extends RecyclerView.Adapter<LinesViewHolder> {
    private ArrayList<Line> lines;

    /*public static class LinesViewHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;

        Button schedule_button;

        public LinesViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.tramLine);

            //this.schedule_button = (Button) itemView.findViewById(R.id.schedule_button);
        }
    }*/

    private RecyclerItemClick callback;

    public LinesRecyclerViewAdapter(ArrayList<Line> lines, RecyclerItemClick callback) {
        this.lines = lines;
        this.callback = callback;
    }
    @Override
    public LinesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.line_cardview, parent, false);
        LinesViewHolder evh = new LinesViewHolder(v);
        return evh;
    }
    @Override
    public void onBindViewHolder(LinesViewHolder holder, int position) {
        Line currentItem = lines.get(position);
        if ("Parc des Sports - Illkirch Graffenstaden".equals(currentItem.getName())){
            holder.mImageView.setImageResource(R.drawable.tram_a);//currentItem.getImageResource());
        }
        else if ("Lingolsheim Tiergaertel - Hoenheim Gare".equals(currentItem.getName())) {
            holder.mImageView.setImageResource(R.drawable.tram_b);//currentItem.getImageResource());
        }
        else if ("Gare Centrale - Neuhof Rodolphe Reuss".equals(currentItem.getName())) {
            holder.mImageView.setImageResource(R.drawable.tram_c);//currentItem.getImageResource());
        }
        else if ("Poteries - Port du Rhin / Kehl Rathaus".equals(currentItem.getName())) {
            holder.mImageView.setImageResource(R.drawable.tram_d);//currentItem.getImageResource());
        }
        else if ("Robertsau l'Escale - Campus d'Illkirch".equals(currentItem.getName())) {
            holder.mImageView.setImageResource(R.drawable.tram_e);//currentItem.getImageResource());
        }
        else if ("Comtes - Place d'Islande".equals(currentItem.getName())) {
            holder.mImageView.setImageResource(R.drawable.tram_f);//currentItem.getImageResource());
        }
        else{
            holder.mImageView.setImageResource(R.drawable.tram);//currentItem.getImageResource());
        }

        holder.onBind(currentItem, callback);

    }

    @Override
    public int getItemCount() {
        return lines.size();
    }
}
