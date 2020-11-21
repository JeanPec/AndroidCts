package com.shindra;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.shindra.ctslibrary.bo.Stop;
import android.content.Context;

import java.text.SimpleDateFormat;

public class HoraireViewHolder extends RecyclerView.ViewHolder {
    public TextView Headline1;
    public TextView Ligne;
    public TextView Time;
    public Context context;


    public HoraireViewHolder(@NonNull View itemView) {
        super(itemView);
        Headline1 = itemView.findViewById(R.id.Headline1);
        Ligne = itemView.findViewById(R.id.Ligne);
        Time = itemView.findViewById(R.id.Time);
        context = itemView.getContext();
    }

    public void OnBind(Stop stop, String lineRef)
    {
        Headline1.setText(stop.getName());
        Ligne.setText(context.getResources().getString(R.string.ligne, lineRef));
        Ligne.setTextColor(ContextCompat.getColor(Ligne.getContext(), getColor(lineRef)));
        SimpleDateFormat fmt = new SimpleDateFormat("HH'h'mm");
        Time.setText(fmt.format(stop.getEstimatedArrivalTime()));
    }

    private int getColor(String lineRef)
    {
        switch (lineRef)
        {
            case "A":
                return R.color.ligne_a;
            case "B":
                return R.color.ligne_b;
            case "C":
                return R.color.ligne_c;
            case "D":
                return R.color.ligne_d;
            case "E":
                return R.color.ligne_e;
            case "F":
                return R.color.ligne_f;
            default:
                return R.color.black;
        }
    }


}
