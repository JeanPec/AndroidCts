package com.shindra;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Line;

// stores and recycles views as they are scrolled off screen
public class TramlineViewHolder extends RecyclerView.ViewHolder {
    interface RecyclerItemClick{
        void OnTramlineClick(Line l);
    }

    interface LineNames {
        final String LineA = "Parc des Sports - Illkirch Graffenstaden";
        final String LineB = "Lingolsheim Tiergaertel - Hoenheim Gare";final String LineC = "Gare Centrale - Neuhof Rodolphe Reuss";
        final String LineD = "Poteries - Port du Rhin / Kehl Rathaus";
        final String LineE = "Robertsau l'Escale - Campus d'Illkirch";
        final String LineF = "Comtes - Place d'Islande";
    }

    private static final String TAG = "tramlineViewHolder";

    View itemView;

    ImageView lineLogo;
    ImageView tramPhoto;
    Button button;

    TramlineViewHolder(View itemView) {
        super(itemView);
        Log.i(TAG,"constructor");

        this.itemView = itemView;
        this.lineLogo = itemView.findViewById(R.id.cvTramline_tramlogo);
        this.tramPhoto = itemView.findViewById(R.id.cvTramline_tramphoto);
        this.button = itemView.findViewById(R.id.cvTramline_button);
    }

    public void onBind(Line l, RecyclerItemClick callback) {
        String s = l.getName();
        switch(s){
            case LineNames.LineA:
                //LineA
                lineLogo.setImageResource(R.drawable.ic_tram_a);
                break;

            case LineNames.LineB:
                //LineB
                lineLogo.setImageResource(R.drawable.ic_tram_b);
                break;

            case LineNames.LineC:
                //LineC
                lineLogo.setImageResource(R.drawable.ic_tram_c);
                break;

            case LineNames.LineD:
                //LineD
                lineLogo.setImageResource(R.drawable.ic_tram_d);
                break;

            case LineNames.LineE:
                //LineE
                lineLogo.setImageResource(R.drawable.ic_tram_e);
                break;

            case LineNames.LineF:
                //LineF
                lineLogo.setImageResource(R.drawable.ic_tram_f);
                break;
        }

        //register onclick callback
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.OnTramlineClick(l);
            }
        });
    }

}