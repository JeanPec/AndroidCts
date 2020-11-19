package com.shindra.dummy;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

class TramCard extends RecyclerView.ViewHolder{

    private final ImageView imageTRAM;

    public TramCard(@NonNull View itemView) {
        super(itemView);
        imageTRAM = itemView.findViewById(R.id.image_tram);
    }

    public void onBind(@NotNull Tram tram){

        imageTRAM.setImageDrawable(ContextCompat.getDrawable(imageTRAM.getContext(),tram.image));

    }
}

