package com.shindra;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MySecondAdapter extends RecyclerView.Adapter<MySecondAdapter.MyViewHolder>{

    String textTramLine;
    Context context;

    public MySecondAdapter(Context ct, String textLine){
        context = ct;
        textTramLine = textLine;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.row_schedules, parent, false);
            return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.textLine.setText(textTramLine);

    }

    @Override
    public int getItemCount() {

        return (int)10;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textLine;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            textLine = itemView.findViewById(R.id.LineTram);

        }
    }
}
