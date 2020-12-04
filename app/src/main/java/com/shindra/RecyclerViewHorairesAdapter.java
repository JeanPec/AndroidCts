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

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.time_cardview, parent, false);
        return new TimeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TimeViewHolder pHolder, int pPosition) {

        Stop mStop = ListeArrets.get(pPosition);
        pHolder.mTimeStop.setText(mStop.getName());
        pHolder.mTimeLine.setText(R.string.Ligne + NomLigne);

        switch (NomLigne) {
            case "A":
                pHolder.mTimeLine.setTextColor(ContextCompat.getColor(pHolder.mTimeLine.getContext(), R.color.LineA));
                break;
            case "B":
                pHolder.mTimeLine.setTextColor(ContextCompat.getColor(pHolder.mTimeLine.getContext(), R.color.LineB));
                break;
            case "C":
                pHolder.mTimeLine.setTextColor(ContextCompat.getColor(pHolder.mTimeLine.getContext(), R.color.LineC));
                break;
            case "D":
                pHolder.mTimeLine.setTextColor(ContextCompat.getColor(pHolder.mTimeLine.getContext(), R.color.LineD));
                break;
            case "E":
                pHolder.mTimeLine.setTextColor(ContextCompat.getColor(pHolder.mTimeLine.getContext(), R.color.LineE));
                break;
            case "F":
                pHolder.mTimeLine.setTextColor(ContextCompat.getColor(pHolder.mTimeLine.getContext(), R.color.LineF));
                break;
            default:
                pHolder.mTimeLine.setTextColor(ContextCompat.getColor(pHolder.mTimeLine.getContext(), R.color.Body2));
                break;
        }

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH'h'mm");
        pHolder.mTime.setText(simpleDateFormat.format(Objects.requireNonNull(mStop.getEstimatedArrivalTime())));
    }

    public class TimeViewHolder extends RecyclerView.ViewHolder {

        public TextView mTime;
        public TextView mTimeLine;
        public TextView mTimeStop;

        public TimeViewHolder(View pItemView) {

            super(pItemView);
            mTime = itemView.findViewById(R.id.Time);
            mTimeLine = itemView.findViewById(R.id.TimeLine);
            mTimeStop = itemView.findViewById(R.id.TimeStop);
        }
    }

    @Override
    public int getItemCount() {

        return ListeArrets.size();
    }
}