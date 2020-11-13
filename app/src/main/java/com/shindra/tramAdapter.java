package com.shindra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class tramAdapter extends RecyclerView.Adapter<tramAdapter.tramViewHolder> {
    private ArrayList<tramCardview> _tramList;

    public static class tramViewHolder extends RecyclerView.ViewHolder{
        public ImageView tramLineView;
        public ImageView tramIllustrationView;

        public tramViewHolder(@NonNull View itemView) {
            super(itemView);
            tramLineView = itemView.findViewById(R.id.imageView);
            tramIllustrationView = itemView.findViewById(R.id.imageView2);
        }
    }

    public tramAdapter(ArrayList<tramCardview> tramList) {
        _tramList = tramList;
    }

    @NonNull
    @Override
    public tramViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View tramCardView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_tram, parent, false);
        tramViewHolder tramCardVH = new tramViewHolder(tramCardView);
        return tramCardVH;
    }

    @Override
    public void onBindViewHolder(@NonNull tramViewHolder holder, int position) {
        tramCardview currentTram = _tramList.get(position);

        holder.tramLineView.setImageResource(currentTram.getTramLinePicture());
        holder.tramIllustrationView.setImageResource(currentTram.getIllustrationPicture());
    }

    @Override
    public int getItemCount() {
        return _tramList.size();
    }
}
