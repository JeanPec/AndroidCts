package com.shindra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TramCardAdapter extends RecyclerView.Adapter<TramCardAdapter.TramViewHolder> {

    List<TramCard> listeTramCard;

    public static class TramViewHolder extends RecyclerView.ViewHolder {

        TextView nomLigne;

        public TramViewHolder(@NonNull View itemView) {
            super(itemView);
            nomLigne = itemView.findViewById(R.id.nomLigne);
        }
    }

    public TramCardAdapter(List<TramCard> listeTramCard) {
        this.listeTramCard = listeTramCard;
    }

    @NonNull
    @Override
    public TramViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_ligne_tram, parent, false);
        TramViewHolder tramViewHolder = new TramViewHolder(view);
        return tramViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TramViewHolder holder, int position) {
        TramCard tramCard = listeTramCard.get(position);
        holder.nomLigne.setText(tramCard.getNomLigne());
    }

    @Override
    public int getItemCount() {
        return listeTramCard.size();
    }


}
