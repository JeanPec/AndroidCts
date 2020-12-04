package com.shindra.Timetable;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.R;
import com.shindra.ctslibrary.bo.Stop;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimetableViewHolder extends RecyclerView.ViewHolder {

    //Création des variables correspondant aux widgets de la vue Horaire.xml
    public TextView nomArret;
    public TextView nomLigne;
    public TextView heureTram;
    public TextView indicationDepart;


    public TimetableViewHolder(@NonNull View itemView) {
        super(itemView);
        //Lier les variables créée au-dessus avec les références des widgets de la vue
        nomArret = itemView.findViewById(R.id.nomArret);
        nomLigne = itemView.findViewById(R.id.nomLigne);
        heureTram = itemView.findViewById(R.id.HeureTram);
        indicationDepart = itemView.findViewById(R.id.IndicationDepart);
    }
    //Dans onBind on a besoin de faire appel à la classe Stop qui donne les infos Horaires, nom de l'arrêt etc et du nom de la ligne
    public void onBind (Stop infoStop, String Ligne){

        //Configuration de la textView contenant le nom de l'arrêt
        nomArret.setText(infoStop.getName());

        //Configuration de la textview Ligne
        Integer colorTram = itemView.getContext().getColor(GetColorTram(Ligne));
        nomLigne.setTextColor(colorTram);
        nomLigne.setText("Ligne" + Ligne);

        //Configuration de la textview Horaire
        heureTram.setText(ConvDateToString(infoStop.getEstimatedDepartureTime()));

    }
    //méthode permettant de déterminer quelle couleur doit prendre le nom de la ligne sur la vue
    private int GetColorTram (String LigneTram)
    {
        int ColorTram;

        switch (LigneTram)
        {
            case "A":
                ColorTram = R.color.LigneA;
                break;

            case "B":
                ColorTram = R.color.LigneB;
                break;

            case "C":
                ColorTram = R.color.LigneC;
                break;

            case "D":
                ColorTram = R.color.LigneD;
                break;

            case "E":
                ColorTram = R.color.LigneE;
                break;

            case "F":
                ColorTram = R.color.LigneF;
                break;

            default:
                ColorTram = R.color.black;
                break;
        }
        return ColorTram;
    }

    //Conversion de la date en string en l'adaptant au format d'affichage voulue
    private String ConvDateToString (Date Heure){
        SimpleDateFormat conv = new SimpleDateFormat("HH:mm");
        return conv.format(Heure);
    }
}
