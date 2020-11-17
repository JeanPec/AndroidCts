package com.shindra;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Line;


public class LinesViewHolder extends RecyclerView.ViewHolder {

    ImageView mImageView;
    Button schedule_button;

    public LinesViewHolder(@NonNull View itemView) {
        super(itemView);

        mImageView = itemView.findViewById(R.id.tramLine);

        this.schedule_button = itemView.findViewById(R.id.schedule_button);
    }




    public void onBind(Line line, RecyclerItemClick callback){
        //lineName.setText(line.na);

        schedule_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onScheduleClick(line);
            }
        });
    }

}
