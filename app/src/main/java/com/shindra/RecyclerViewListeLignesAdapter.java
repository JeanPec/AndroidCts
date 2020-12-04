package com.shindra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.shindra.ctslibrary.bo.Line;

import java.util.ArrayList;

public class RecyclerViewListeLignesAdapter extends RecyclerView.Adapter<RecyclerViewListeLignesAdapter.ViewHolder> {

    //On crée la liste des lignes
    private ArrayList<Line> ListeLignes;

    //On crée un Item Click pour utiliser la fonctionnalité declick sur un objet
    private RecyclerViewItemClick Click;

    //On passe les données des lignes en entrée dans le constructeur ainsi que le item click
    RecyclerViewListeLignesAdapter(ArrayList<Line> ListeEnEntree, RecyclerViewItemClick ClickEnEntree) {
        this.ListeLignes = ListeEnEntree;
        this.Click = ClickEnEntree;
    }

    //On définit le view holder
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vue = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_ligne_tram, parent, false);
        return new ViewHolder(vue);
    }

    //Méthode pour lier les données aux cardviews dans la recycler view en fonction de la position
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Line LigneATraiter = ListeLignes.get(position);
        //En fonction du nom de la ligne on associe l'image correspondante
        switch (LigneATraiter.getName()) {
            case ("A"): {
                holder.ImageViewNomLigne.setImageResource(R.drawable.tram_a);
            }
            break;
            case ("B"): {
                holder.ImageViewNomLigne.setImageResource(R.drawable.tram_b);
            }
            break;
            case ("C"): {
                holder.ImageViewNomLigne.setImageResource(R.drawable.tram_c);
            }
            break;
            case ("D"): {
                holder.ImageViewNomLigne.setImageResource(R.drawable.tram_d);
            }
            break;
            case ("E"): {
                holder.ImageViewNomLigne.setImageResource(R.drawable.tram_e);
            }
            break;
            case ("F"): {
                holder.ImageViewNomLigne.setImageResource(R.drawable.tram_f);
            }
            break;

            default:
                holder.ImageViewNomLigne.setImageResource(R.drawable.tram);
                break;
        }
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return ListeLignes.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView ItemViewNomLigne;
        Button BoutonHoraires;

        //On implémente le constructeur
        ViewHolder(View itemView) {
            super(itemView);
            Ligne = itemView.findViewById(R.id.imageTextTram);
            BoutonHoraires = itemView.findViewById(R.id.button);
        }

        //On implémente la fonction qui permet de catch un click sur le bouton des horaires
        @Override
        public void onBind(Line Ligne, RecyclerViewItemClick Click) {
            BoutonHoraires.setOnClickListener(v -> Click.onTimeButton);
        }
    }
}
