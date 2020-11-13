package com.shindra;

import android.view.View;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

// stores and recycles views as they are scrolled off screen
public class LinesViewHolder extends RecyclerView.ViewHolder {
    TextView lineName;

    LinesViewHolder(View itemView) {
        super(itemView);
        lineName = itemView.findViewById(R.id.card_tramlines_Name);
    }

}