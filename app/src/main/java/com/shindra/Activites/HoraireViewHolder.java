package com.shindra.Activites;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.R;

import java.util.ArrayList;
import java.util.Date;

import static android.content.ContentValues.TAG;



public class HoraireViewHolder extends RecyclerView.ViewHolder {

    private final TextView stop;
    private final TextView ligneTram;
    private final TextView nextHoraire;


    public HoraireViewHolder(@NonNull View itemView){
        super(itemView);

        stop = itemView.findViewById(R.id.stoptram);
        ligneTram = itemView.findViewById(R.id.ligne_tram);
        nextHoraire = itemView.findViewById(R.id.horaire);


    }

    public void onBind(TrameStop trameStop) {
        stop.setText(trameStop.nomArret);
        ligneTram.setText(trameStop.nomLine);
        nextHoraire.setText(datetoString(trameStop.horaire));
        /*datetoString(trameStop.horaire);*/

        //appliquer une conversion sur l'horaire en format sString voulu

    };

    public String datetoString (Date horaire ) {

       /* Log.d(TAG,"coucou "+ horaire);*/
        String horairestring = "" +  horaire;
        String heure = horairestring.substring(11, 13);
        String minute  = horairestring.substring(14,16);
        String horaire_format = heure + "h" + minute;
        return horaire_format;
    }


}
