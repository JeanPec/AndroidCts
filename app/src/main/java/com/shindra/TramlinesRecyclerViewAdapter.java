package com.shindra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Line;

import java.util.List;

public class TramlinesRecyclerViewAdapter extends RecyclerView.Adapter<TramlineViewHolder> {

    private TramlineViewHolder.RecyclerItemClick callback;

    private List<Line> availableTramlines;

    // data is passed into the constructor
    TramlinesRecyclerViewAdapter(List<Line> availableTramlines, TramlineViewHolder.RecyclerItemClick callback) {
        this.callback = callback;
        this.availableTramlines = availableTramlines;
    }

    // inflates the row layout from xml when needed
    @NonNull
    @Override
    public TramlineViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View tramline = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cardview_tramline, parent, false);

        return new TramlineViewHolder(tramline);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(TramlineViewHolder receivingHolder, int position) {
        Line requestedTramline = availableTramlines.get(position);

        receivingHolder.onBind(requestedTramline, callback);
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return availableTramlines.size();
    }

}
