package com.shindra.Activites;

import android.graphics.Color;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.R;
import com.shindra.ctslibrary.bo.Line;

public class TramLineViewHolder extends RecyclerView.ViewHolder {

    public final ImageView imageTrame;
    public final Button buttonHoraire;

interface RecyclerItemClick{
    void onHoraireClick (Tram tram);
}

    public TramLineViewHolder(@NonNull View itemView) {
        super(itemView);

        imageTrame = itemView.findViewById(R.id.image_ligne_tram);
        buttonHoraire = itemView.findViewById(R.id.button_horaire);
    }

    /*
    public void onBind (Tram tram){

// set SVH in the view
        imageTrame.setImageResource(tram.drawable_id_svg);
*/

    public void onBind (Tram tram, RecyclerItemClick callback){
        imageTrame.setImageResource(tram.drawable_id_svg);
        buttonHoraire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onHoraireClick(tram);
            }
        });

    }

}
