package com.shindra;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.shindra.ctslibrary.bo.Line;


public class TramViewHolder extends RecyclerView.ViewHolder  {

    ImageView mImageView;
    Button mTimeButton;

    public TramViewHolder(@NonNull View pItemView) {

        super(pItemView);
        mImageView = itemView.findViewById(R.id.TramText);
        this.mTimeButton = itemView.findViewById(R.id.TimeButton);
    }

    public void onBind(Line pLine, RecyclerItemClick pCallback){

        mTimeButton.setOnClickListener(v -> pCallback.onTimeClick(pLine));
    }
}
