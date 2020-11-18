package com.shindra;

import android.widget.Button;
import android.widget.ImageView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Line;

public class Trams_View_Holder extends RecyclerView.ViewHolder
{
    private final ImageView tram_view;
    private final ImageView tram_logo;
    private final Button button;

    public Trams_View_Holder(@NonNull View itemView)
    {
        super(itemView);
        this.tram_logo = itemView.findViewById(R.id.Tram_Logo);
        this.tram_view = itemView.findViewById(R.id.Tram_Line_View);
        this.button = itemView.findViewById(R.id.Tram_view_button);
    }

    public void onBind(Line image)
    {
        String txt = image.getName();
        switch (txt)
        {
            case "Parc des Sports - Illkirch Graffenstaden":
                tram_logo.setImageResource(R.drawable.tram_a);
                break;

            case "Lingolsheim Tiergaertel - Hoenheim Gare":
                tram_logo.setImageResource(R.drawable.tram_b);
                break;

            case "Gare Centrale - Neuhof Rodolphe Reuss":
                tram_logo.setImageResource(R.drawable.tram_c);
                break;

            case "Poteries - Port du Rhin / Kehl Rathaus":
                tram_logo.setImageResource(R.drawable.tram_d);
                break;

            case "Robertsau l'Escale - Campus d'Illkirch":
                tram_logo.setImageResource(R.drawable.tram_e);
                break;

            case "Comtes - Place d'Islande":
                tram_logo.setImageResource(R.drawable.tram_f);
                break;

            default:
                tram_logo.setImageResource(R.drawable.tram);
                break;
        }
    }
    public void onClick(View v)
    {

    }
}

