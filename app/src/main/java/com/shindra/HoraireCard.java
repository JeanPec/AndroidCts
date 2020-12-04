package com.shindra;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Line;
import com.shindra.ctslibrary.bo.Stop;

import java.text.SimpleDateFormat;

public class HoraireCard extends RecyclerView.ViewHolder {

    public TextView arret, horaire, nomLigne, prochainDepart;


    public HoraireCard(@NonNull View itemView) {
        super(itemView);


        this.arret = itemView.findViewById(R.id.arret);
        this.horaire = itemView.findViewById(R.id.horaire);
        this.nomLigne = itemView.findViewById(R.id.nomLigne);
        this.prochainDepart = itemView.findViewById(R.id.prochainDepart);

    }

    @SuppressLint("ResourceAsColor")
    private void setLineTextColor(String line){

        switch (line){
            case "A":
                this.nomLigne.setTextColor(itemView.getContext().getColor(R.color.color_Ligne_A));
                break;
            case "B":
                this.nomLigne.setTextColor(itemView.getContext().getColor(R.color.color_Ligne_B));
                break;
            case "C":
                this.nomLigne.setTextColor(itemView.getContext().getColor(R.color.color_Ligne_C));
                break;
            case "D":
                this.nomLigne.setTextColor(itemView.getContext().getColor(R.color.color_Ligne_D));
                break;
            case "E":
                this.nomLigne.setTextColor(itemView.getContext().getColor(R.color.color_Ligne_E));
                break;
            case "F":
                this.nomLigne.setTextColor(itemView.getContext().getColor(R.color.color_Ligne_F));
                break;
        }


    }



    public void onBind(Stop stop, String line){
        setLineTextColor(line);
        nomLigne.setText("Ligne "+line);
        horaire.setText(new SimpleDateFormat("HH:mm").format(stop.getEstimatedDepartureTime()));
        arret.setText(stop.getName());

    }
}
