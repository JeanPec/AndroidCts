package com.shindra;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Line;

import java.util.ArrayList;
import java.util.List;

public class LignesAdapter extends RecyclerView.Adapter<LignesAdapter.ViewHolder> {

    private List<Line> lines;
    private LayoutInflater mInflater;
    private ItemClickListener mClickListener;

    //Data is passed into the constructor like slide 104
    LignesAdapter(List<Line> lines) {
        this.lines=lines;
    }

    //Inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View lineView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_row, parent, false);
        return new ViewHolder(lineView);
    }

    //Binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.onBind(lines.get(position));
    }

    //Total number of rows
    @Override
    public int getItemCount() {
        return lines.size();
    }

    //Stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        ImageView imageTram;
        ImageView imageLetter;

        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.myLine);
            itemView.setOnClickListener(this);
        }

        public void onBind(Line line) {
            //set values to views
            imageTram.setImageIcon(R.drawable.ic_tram);
            imageLetter.setImageIcon(R.drawable.ic_a);
            //2 errors : make a list of trams in StartActivity to get tram.something here ?
            myTextView.setText(line.getName());
            Log.d("CTS", "Ligne : " + line.getName());
            switch(line.getName()) {
                case "Parc des Sports - Illkirch Graffenstaden":
                    //lineName.setImageResource(R.drawable.ic_tram_a);
                    break;
            }
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }


    }

    //Convenience method for getting data at click position deleted

    //Allows clicks events to be caught
    void setClickListener(ItemClickListener itemClickListener) {
        this.mClickListener = itemClickListener;
    }

    //Parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

}


