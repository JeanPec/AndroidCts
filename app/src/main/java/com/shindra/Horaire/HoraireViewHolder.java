package com.shindra.Horaire;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.R;

public class HoraireViewHolder extends RecyclerView.ViewHolder
{
    //Widjets de la vue "Nos Trams"
    public final TextView arret;
    public final TextView horaire;
    public final TextView depart;
    public final TextView ligne;


    public HoraireViewHolder(@NonNull View itemView)
    {
        super(itemView);
        arret = itemView.findViewById(R.id.arret);
        horaire = itemView.findViewById(R.id.horaire);
        depart = itemView.findViewById(R.id.depart);
        ligne = itemView.findViewById(R.id.ligne);
    }

//    // Retourne le ViewHolder des données sur lequels elles sont liées / Appelé pour chaque elements dans la liste
//    public void onBind(Stop stop, String lettreLigne)
//    {
//        arret.setText(stop.getName());
//        horaire.setText(DateToString(stop.getEstimatedDepartureTime()));
//        depart.setText("Prochain départ");
//        ligne.setText("Ligne " + lettreLigne);
//        ligne.setTextColor(GetLineColor(lettreLigne));
//    }
//
//    // Permet la converssion d'un format date en un format string
//    private String DateToString(Date date)
//    {
//        SimpleDateFormat Conv = new SimpleDateFormat("HH'h'mm");
//        return Conv.format(date);
//    }
//
//    //Permet d'obtenir la couleur en fonction de la ligne
//    private int GetLineColor(String LineLetter)
//    {
//        int color;
//
//        switch (LineLetter)
//        {
//            case "A":
//                color = R.color.TramLineA;
//            break;
//
//            case "B":
//                color = R.color.TramLineB;
//            break;
//
//            case "C":
//                color = R.color.TramLineC;
//            break;
//
//            case "D":
//                color = R.color.TramLineD;
//            break;
//
//            case "E":
//                color = R.color.TramLineE;
//            break;
//
//            case "F":
//                color = R.color.TramLineF;
//            break;
//
//            default:
//                color = R.color.black;
//            break;
//        }
//        return color;
//    }
}
