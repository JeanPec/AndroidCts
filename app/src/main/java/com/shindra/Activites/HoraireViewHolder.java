package com.shindra.Activites;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.R;
import com.shindra.ctslibrary.bo.Line;

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
       /* ligneTram.setTextColor(get().getColorStateList(R.color.LigneA));*/

        /*ligneTram.setTextColor(Color.parseColor("#EF2B3E"));*/
        nextHoraire = itemView.findViewById(R.id.horaire);


    }

    public void onBind(TrameStop trameStop) {
        stop.setText(trameStop.nomArret);
        ligneTram.setText(trameStop.nomLine);


        switch(trameStop.getNameLine()){
            case  "Ligne A" :
                ligneTram.setTextColor(Color.parseColor("#EF2B3E"));
                break;
            case  "Ligne B" :
                ligneTram.setTextColor(Color.parseColor("#00A3DA"));
                break;
            case  "Ligne C" :
                ligneTram.setTextColor(Color.parseColor("#EB8B2D"));;
                break;
            case  "Ligne D" :
                ligneTram.setTextColor(Color.parseColor("#009F4A"));
                break;
            case  "Ligne E" :
                ligneTram.setTextColor(Color.parseColor("#7F78AB"));
                break;
            case  "Ligne F" :
                ligneTram.setTextColor(Color.parseColor("#84BF41"));
                break;
        }
        /*ligneTram.setTextColor(Color.parseColor("#EF2B3E"));*/
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
