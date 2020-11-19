package com.shindra;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.shindra.ctslibrary.bo.Line;


public class TramViewHolder extends RecyclerView.ViewHolder {

    ImageView ivTextTram;
    ImageView ivIconTram;
    Button bHours;

    public TramViewHolder(@NonNull View view){
        super(view);
        ivTextTram = view.findViewById(R.id.imageView3);
        ivIconTram = view.findViewById(R.id.imageView4);
        bHours = view.findViewById(R.id.button);
    }

    public void onBind(Line lLine, onButtonClickListener callback) {
        bHours.setOnClickListener(v -> callback.onButtonClick(lLine));
    }

    interface onButtonClickListener {
        void onButtonClick(Line iLine);
    }
}
