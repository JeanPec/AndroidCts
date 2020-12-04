package com.shindra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;
import com.shindra.ctslibrary.bo.Stop;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Objects;

public class RecyclerViewHorairesAdapter extends RecyclerView.Adapter<RecyclerViewHorairesAdapter.TimeViewHolder> {

    //On crée une liste des arrêts
    private ArrayList<Stop> ListeArrets;
    //On définit le nom de la ligne
    private String NomLigne;


    //Le constructeur récupère la liste des arrêts et le nom de la ligne en entrée
    public RecyclerViewHorairesAdapter(ArrayList<Stop> ListeArretsEnEntree, String NomLigneEnEntree) {

        this.ListeArrets = ListeArretsEnEntree;
        this.NomLigne = NomLigneEnEntree;
    }

    @Override
    public TimeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        //On crée une vue
        View Vue = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardview_horaires, parent, false);
        return new TimeViewHolder(Vue);
    }

    //Méthode pour lier les données aux cardviews dans la recycler view en fonction de la position
    @Override
    public void onBindViewHolder(TimeViewHolder holder, int position) {

        Stop arret = ListeArrets.get(position);
        holder.arret.setText(arret.getName());
        holder.ligne.setText("Ligne " + NomLigne);

        //On affiche l'horaire au formal HHmm
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH'h'mm");
        holder.horaire.setText(simpleDateFormat.format(Objects.requireNonNull(arret.getEstimatedArrivalTime())));
    }

    //Stock les vues au fur et à mesure qu'on scroll
    public class TimeViewHolder extends RecyclerView.ViewHolder {

        public TextView horaire;
        public TextView ligne;
        public TextView arret;

        public TimeViewHolder(View pItemView) {

            super(pItemView);
            horaire = itemView.findViewById(R.id.horaire);
            ligne = itemView.findViewById(R.id.ligne);
            arret = itemView.findViewById(R.id.arret);
        }
    }

    //Nombre total d'éléments
    @Override
    public int getItemCount() {

        return ListeArrets.size();
    }
}