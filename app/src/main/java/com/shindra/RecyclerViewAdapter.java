package com.shindra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.ArrayList;
import androidx.recyclerview.widget.RecyclerView;
import com.shindra.ctslibrary.bo.Line;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    private ArrayList<Line> TramLines;
    private LayoutInflater mInflater;

    // data is passed into the constructor
    public RecyclerViewAdapter(ArrayList<Line> mlines) {
        TramLines = mlines;

    }

    // stores and recycles views as they are scrolled off screen
    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;

        ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.TramList);
        }
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.cardview, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Line line = TramLines.get(position);
        //TODO
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return TramLines.size();
    }
}