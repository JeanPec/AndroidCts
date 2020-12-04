package com.shindra;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.R;
import com.shindra.ctslibrary.bo.Line;

public class TramCard extends RecyclerView.ViewHolder {

    public ImageView imgNomLigne, imgTram;
    public Button  horaireButton;

    interface RecyclerItemClick {
        void onHoraireButtonClick(Line line);
    }

    public TramCard(@NonNull View itemView) {
        super(itemView);
        this.imgTram = itemView.findViewById(R.id.imgTram);
        this.imgNomLigne = itemView.findViewById(R.id.imgLigne);
        this.horaireButton = itemView.findViewById(R.id.horaireButton);

    }

    public void onBind(Line line, RecyclerItemClick callback)
    {
            switch (line.getName()){    // choix de l'image en fonction de la ligne sélectionée
                case "A":
                    this.imgNomLigne.setImageResource(R.drawable.tram_a);
                    break;
                case "B":
                    this.imgNomLigne.setImageResource(R.drawable.tram_b);
                    break;
                case "C":
                    this.imgNomLigne.setImageResource(R.drawable.tram_c);
                    break;
                case "D":
                    this.imgNomLigne.setImageResource(R.drawable.tram_d);
                    break;
                case "E":
                    this.imgNomLigne.setImageResource(R.drawable.tram_e);
                    break;
                case "F":
                    this.imgNomLigne.setImageResource(R.drawable.tram_f);
                    break;
            }




        horaireButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onHoraireButtonClick(line);


            }
        });
    }

}
