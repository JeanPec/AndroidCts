package com.shindra.Ligne;

import android.widget.Button;
import android.widget.ImageView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.R;
import com.shindra.ctslibrary.bo.Line;

public class TramsViewHolder extends RecyclerView.ViewHolder
{
    //Widjets de la vue "Nos Trams"
    private final ImageView imagePhotoTram;
    private final ImageView imageLigneTram;
    private final Button button;

    interface RecyclerHoraireClick
    {
        void onHoraireLineClick(Line ligne);
    }

    //Constructeur
    public TramsViewHolder(@NonNull View itemView)
    {
        super(itemView);
        this.imageLigneTram = itemView.findViewById(R.id.imageLigneTram);
        this.imagePhotoTram = itemView.findViewById(R.id.imageTram);
        this.button = itemView.findViewById(R.id.buttonlignetram);
    }


    // Retourne le ViewHolder des données sur lequels elles sont liées / Appelé pour chaque elements dans la liste
    public void onBind(Line image, RecyclerHoraireClick callBack)
    {
        switch (image.getName())
        {
            case "A":    //ligne A
                imageLigneTram.setImageResource(R.drawable.tram_a);
                break;

            case "B":    //ligne B
                imageLigneTram.setImageResource(R.drawable.tram_b);
                break;

            case "C":    //ligne C
                imageLigneTram.setImageResource(R.drawable.tram_c);
                break;

            case "D":    //ligne D
                imageLigneTram.setImageResource(R.drawable.tram_d);
                break;

            case "E":    //ligne E
                imageLigneTram.setImageResource(R.drawable.tram_e);
                break;

            case "F":    //ligne F
                imageLigneTram.setImageResource(R.drawable.tram_f);
                break;

            default:
                imageLigneTram.setImageResource(R.drawable.tram);
                break;
        }

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                callBack.onHoraireLineClick(image);
            }
        });
    }
}
