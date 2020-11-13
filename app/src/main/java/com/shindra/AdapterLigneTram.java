package com.shindra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class AdapterLigneTram extends RecyclerView.Adapter<AdapterLigneTram.MyViewHolder>
{
    int ImageLigneTram[];
    Context context;

    public AdapterLigneTram(Context ct, int Img[])
    {
        context = ct;
        ImageLigneTram = Img;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View TramView = LayoutInflater.from(parent.getContext()).inflate(R.layout.tableau_deroulant_ligne_tram, parent, false);
        return new MyViewHolder(TramView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position)
    {
        holder.ImageLigneTram.setImageResource(ImageLigneTram[position]);
    }

    @Override
    public int getItemCount() {
        return ImageLigneTram.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder
    {
        ImageView ImageLigneTram;
        public MyViewHolder(@NonNull View itemView)
        {
            super(itemView);
            ImageLigneTram = itemView.findViewById(R.id.imageLigneTram);
        }
    }

}
