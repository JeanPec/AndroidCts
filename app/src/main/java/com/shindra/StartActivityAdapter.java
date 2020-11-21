package com.shindra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Line;

import java.util.ArrayList;
import java.util.List;

public class StartActivityAdapter extends RecyclerView.Adapter<StartActivityAdapter.tramViewHolder> {
    private ArrayList<Line> _lineList;
    private RecyclerItemClick _callBack;

    public interface RecyclerItemClick{
        void onDiaryButtonClick(Line tram);
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

        public void onBind(Line tram, RecyclerItemClick callback){
            tramIllustrationView.setImageResource(R.drawable.nouveau_tram_strasbourg);
            buttonToStationDiary.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onDiaryButtonClick(tram);
                }
            });
        }

    }

    public StartActivityAdapter(ArrayList<Line> lineList, RecyclerItemClick callBack) {
        _lineList = lineList;
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
        Line currentTram = _lineList.get(position);
        switch (currentTram.getName())
        {
            case "A":
                holder.tramLineView.setImageResource(R.drawable.tram_a);
                break;
            case "B":
                holder.tramLineView.setImageResource(R.drawable.tram_b);
                break;
            case "C":
                holder.tramLineView.setImageResource(R.drawable.tram_c);
                break;
            case "D":
                holder.tramLineView.setImageResource(R.drawable.tram_d);
                break;
            case "E":
                holder.tramLineView.setImageResource(R.drawable.tram_e);
                break;
            case "F":
                holder.tramLineView.setImageResource(R.drawable.tram_f);
                break;
            default:
                holder.tramLineView.setImageResource(R.drawable.tram);
                break;

        }

        //holder.tramIllustrationView.setImageResource(R.drawable.nouveau_tram_strasbourg);
        holder.onBind(currentTram, _callBack);
    }

    @Override
    public int getItemCount() {
        return _lineList.size();
    }
}
