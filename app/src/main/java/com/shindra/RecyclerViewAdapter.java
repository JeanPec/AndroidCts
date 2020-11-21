package com.shindra;

import android.content.Context;
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
    public RecyclerViewAdapter(Context context, ArrayList<Line> mlines) {
        TramLines = mlines;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

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
        switch (line.getName()) {
            case "Tram A":
                holder.mImageView.setImageResource(R.drawable.tram_a);
                break;

            case "Tram B":
                holder.mImageView.setImageResource(R.drawable.tram_b);
                break;

            case "Tram C":
                holder.mImageView.setImageResource(R.drawable.tram_c);
                break;

            case "Tram D":
                holder.mImageView.setImageResource(R.drawable.tram_d);
                break;

            case "Tram E":
                holder.mImageView.setImageResource(R.drawable.tram_e);
                break;

            case "Tram F":
                holder.mImageView.setImageResource(R.drawable.tram_f);
                break;

            default:
                holder.mImageView.setImageResource(R.drawable.tram);
                break;
        }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return TramLines.size();
    }
}