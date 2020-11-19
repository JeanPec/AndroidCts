//Ajout 18/11/2020
package com.shindra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NosTramsAdapter extends RecyclerView.Adapter<NosTramsAdapter.NosTramsViewHolder> {

    private ArrayList <NosTramsItem> nosTramsList;

    public static class NosTramsViewHolder extends RecyclerView.ViewHolder{

        public ImageView imageTram;
        public TextView textView1;
        public TextView textView2;

        public NosTramsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageTram = itemView.findViewById(R.id.imageView);
            textView1 = itemView.findViewById(R.id.textView1);
            textView2 = itemView.findViewById(R.id.textView2);
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

        holder.imageTram.setImageResource (currentTram.getImageTram());
        holder.textView1.setText(currentTram.getText1());
        holder.textView2.setText(currentTram.getText2());
    }

    @Override
    public int getItemCount() {
        return nosTramsList.size();
    }




}
