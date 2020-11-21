package com.shindra.dummy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class Holder extends RecyclerView.ViewHolder{

    private final ImageView imageTRAM;
    Button bHorr;

    public Holder(@NonNull View itemView) {
        super(itemView);
        imageTRAM = itemView.findViewById(R.id.image_tram);
        bHorr = itemView.findViewById(R.id.button);
    }

    public void onBind(@NotNull Tram tram, onButtonClickListener callback){
        imageTRAM.setImageDrawable(ContextCompat.getDrawable(imageTRAM.getContext(),tram.image));

        bHorr.setOnClickListener(v -> callback.onButtonClick(tram));

    }

    public interface onButtonClickListener {
        void onButtonClick(Tram tram);
    }
}