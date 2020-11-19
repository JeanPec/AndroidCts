//Ajout 18/11/2020
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

public class NosTramsAdapter extends RecyclerView.Adapter<NosTramsAdapter.NosTramsViewHolder> {

    ArrayList <NosTramsItem> nosTramsList;

    public static class NosTramsViewHolder extends RecyclerView.ViewHolder{

        public ImageView ligneTram;
        public ImageView imageTram;

        public NosTramsViewHolder(@NonNull View itemView) {
            super(itemView);
            ligneTram = itemView.findViewById(R.id.imageLigneTram);
            imageTram = itemView.findViewById(R.id.imageTram);
        }
    }

    public NosTramsAdapter (ArrayList<NosTramsItem> nosTramsList) {
        this.nosTramsList = nosTramsList;
    }

    @NonNull
    @Override
    public NosTramsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.nos_trams_item,parent,false);
        NosTramsViewHolder ntvh = new NosTramsViewHolder (v);
        return ntvh;
    }

    @Override
    public void onBindViewHolder(@NonNull NosTramsViewHolder holder, int position) {
        NosTramsItem currentTram = nosTramsList.get(position);


        holder.ligneTram.setImageResource (currentTram.imageLigne);
        holder.imageTram.setImageResource(currentTram.imageTram);

    }

    @Override
    public int getItemCount() {
        return nosTramsList.size();
    }




}
