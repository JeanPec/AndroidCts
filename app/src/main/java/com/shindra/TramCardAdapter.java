package com.shindra;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Line;

import java.util.ArrayList;
import java.util.List;

import static com.shindra.R.id.imgTram;

public class TramCardAdapter extends RecyclerView.Adapter<TramCard> {

    ArrayList<Line> lineArrayList;
    TramCard.RecyclerItemClick callback;


    public TramCardAdapter(ArrayList<Line> lineArrayList, TramCard.RecyclerItemClick callback) {
        this.lineArrayList = lineArrayList;
        this.callback = callback;
    }

    @NonNull
    @Override
    public TramCard onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_ligne_tram, parent, false);
        TramCard tramViewHolder = new TramCard(view);
        return tramViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TramCard holder, int position) {
        Line line = lineArrayList.get(position);
        holder.onBind(line, callback);


    }

    @Override
    public int getItemCount() {
        return lineArrayList.size();
    }


}
