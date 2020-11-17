package com.shindra;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Line;

public class LigneViewHolder extends RecyclerView.ViewHolder {
    ImageView image;
    Button button;

    public LigneViewHolder(@NonNull View itemView) {
        super(itemView);
        image = itemView.findViewById(R.id.tramligne);
        button = itemView.findViewById(R.id.button);
    }

    public void OnBind(Line line, RecyclerItemClick callback)
    {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                callback.OnClick(line);
            }
        });
    }
}
