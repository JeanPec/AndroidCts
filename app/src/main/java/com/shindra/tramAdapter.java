package com.shindra;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class tramAdapter extends RecyclerView.Adapter<tramAdapter.tramViewHolder> {
    private ArrayList<tramCardview> _tramList;
    private RecyclerItemClick _callBack;

    public interface RecyclerItemClick{
        void onDiaryButtonClick(tramCardview tram);
    }

    public static class tramViewHolder extends RecyclerView.ViewHolder{
        public ImageView tramLineView;
        public ImageView tramIllustrationView;
        public Button buttonToStationDiary;

        public tramViewHolder(@NonNull View itemView) {
            super(itemView);
            tramLineView = itemView.findViewById(R.id.tramLinePicture);
            tramIllustrationView = itemView.findViewById(R.id.illustrationPicture);
            buttonToStationDiary = itemView.findViewById(R.id.diaryStationButton);
        }

        public void onBind(tramCardview tram, RecyclerItemClick callback){
            buttonToStationDiary.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onDiaryButtonClick(tram);
                }
            });

        }
    }

    public tramAdapter(ArrayList<tramCardview> tramList, RecyclerItemClick callBack) {
        _tramList = tramList;
        _callBack = callBack;
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
        holder.onBind(currentTram, _callBack);
    }

    @Override
    public int getItemCount() {
        return _tramList.size();
    }
}
