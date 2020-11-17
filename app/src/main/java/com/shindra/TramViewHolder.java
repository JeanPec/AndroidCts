package com.shindra;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.shindra.ctslibrary.bo.Line;


public class TramViewHolder extends RecyclerView.ViewHolder {

    private ImageView ivTextTram;
    private Button bHours;

    public TramViewHolder(@NonNull View view){
        super(view);
        ivTextTram = view.findViewById(R.id.imageView3);
        bHours = view.findViewById(R.id.button);
    }

    public void onBind(Line iLine, onButtonClickListener callback) {
        bHours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v){
                callback.onButtonClick(iLine);
            }
        });
    }

    interface onButtonClickListener {
        void onButtonClick(Line iLine);
    }
}
