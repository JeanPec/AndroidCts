//Ajout 18/11/2020
package com.shindra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Line;

import java.util.ArrayList;

public class NosTramsAdapter extends RecyclerView.Adapter<NosTramsAdapter.NosTramsViewHolder> {

    private ArrayList <Line> nosTramsList;


    public static class NosTramsViewHolder extends RecyclerView.ViewHolder{

        // La seul référence d'image qu'il faut changer et celle des trams.
        public final ImageView ligneTram;
        //public final ImageView imageTram;

        public NosTramsViewHolder(@NonNull View itemView) {
            super(itemView);
            ligneTram = itemView.findViewById(R.id.imageLigneTram);
            //imageTram = itemView.findViewById(R.id.imageTram);
        }
        //La réponse de l'appel réseau permet de choisir l'image du tram à afficher
        public void TramsOnBind(Line image)
        {

            switch (image.getName())
            {
                case "A":
                    ligneTram.setImageResource(R.drawable.tram_a);
                    break;

                case "B":
                    ligneTram.setImageResource(R.drawable.tram_b);
                    break;

                case "C":
                    ligneTram.setImageResource(R.drawable.tram_c);
                    break;

                case "D":
                    ligneTram.setImageResource(R.drawable.tram_d);
                    break;

                case "E":
                    ligneTram.setImageResource(R.drawable.tram_e);
                    break;

                case "F":
                    ligneTram.setImageResource(R.drawable.tram_f);
                    break;

                default:
                    ligneTram.setImageResource(R.drawable.tram);
                    break;
            }
        }


    }

    public NosTramsAdapter (ArrayList<Line> nosTramsList) {
        this.nosTramsList = nosTramsList;
    }

    @NonNull
    @Override
    public NosTramsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View tramV = LayoutInflater.from(parent.getContext()).inflate(R.layout.nos_trams_item,parent,false);
        NosTramsViewHolder ntvh = new NosTramsViewHolder (tramV);
        return ntvh;
    }

    @Override
    public void onBindViewHolder(@NonNull NosTramsViewHolder holder, int position) {
        Line currentTram = nosTramsList.get(position);
        holder.TramsOnBind(currentTram);

    }

    @Override
    public int getItemCount() {
        return nosTramsList.size();
    }

}
