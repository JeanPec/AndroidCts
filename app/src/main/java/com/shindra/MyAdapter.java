package com.shindra;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.zip.Inflater;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    int pictures[];
    Context context;

    public MyAdapter(Context ct, int pictures[]){
        context = ct;
        this.pictures = pictures;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.cardview, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.MyPicture.setImageResource(pictures[position]);
    }

    @Override
    public int getItemCount() {
        return pictures.length;
    }

    public class MyViewHolder extends  RecyclerView.ViewHolder {

        ImageView MyPicture;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            MyPicture = itemView.findViewById(R.id.Subway_number);
        }
    }
}
