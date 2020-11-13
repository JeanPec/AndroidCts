package com.shindra;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MyViewHolder> {

    int images1[];
    int images2[];
    Context context;

    public RVAdapter(Context ct, int img1[], int img2[]){
        context = ct;
        images1 = img1;
        images2 = img2;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.card, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.myImg1.setImageResource(images1[position]);
        holder.myImg2.setImageResource(images2[position]);
    }

    @Override
    public int getItemCount() {
        return images1.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView myImg1, myImg2;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myImg1 = itemView.findViewById(R.id.imageView);
            myImg2 = itemView.findViewById(R.id.imageView2);
        }
    }
}
