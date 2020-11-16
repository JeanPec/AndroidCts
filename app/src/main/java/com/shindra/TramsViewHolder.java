package com.shindra;

import android.widget.Button;
import android.widget.ImageView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Line;

public class TramsViewHolder extends RecyclerView.ViewHolder
{
    private final ImageView imagePhotoTram;
    private final ImageView imageLigneTram;
    private final Button button;

    public TramsViewHolder(@NonNull View itemView)
    {
        super(itemView);
        this.imageLigneTram = itemView.findViewById(R.id.imageLigneTram);
        this.imagePhotoTram = itemView.findViewById(R.id.imageTram);
        this.button = itemView.findViewById(R.id.buttonlignetram);
    }

    public void onBind(Line image)
    {
        String txt = image.getName();
        switch (txt)
        {
            case "Parc des Sports - Illkirch Graffenstaden":    //ligne A
                imageLigneTram.setImageResource(R.drawable.tram_a);
                break;

            case "Lingolsheim Tiergaertel - Hoenheim Gare":    //ligne B
                imageLigneTram.setImageResource(R.drawable.tram_b);
                break;

            case "Gare Centrale - Neuhof Rodolphe Reuss":    //ligne C
                imageLigneTram.setImageResource(R.drawable.tram_c);
                break;

            case "Poteries - Port du Rhin / Kehl Rathaus":    //ligne D
                imageLigneTram.setImageResource(R.drawable.tram_d);
                break;

            case "Robertsau l'Escale - Campus d'Illkirch":    //ligne E
                imageLigneTram.setImageResource(R.drawable.tram_e);
                break;

            case "Comtes - Place d'Islande":    //ligne F
                imageLigneTram.setImageResource(R.drawable.tram_f);
                break;

            default:
                imageLigneTram.setImageResource(R.drawable.tram);
                break;
        }
    }
    public void onClick(View v)
    {

    }
}
