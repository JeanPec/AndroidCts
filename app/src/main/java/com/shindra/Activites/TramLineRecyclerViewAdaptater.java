package com.shindra.Activites;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.R;
import com.shindra.ctslibrary.bo.Line;

import java.util.ArrayList;

public class TramLineRecyclerViewAdaptater extends RecyclerView.Adapter<TramLineViewHolder> {
    private ArrayList<Tram> Tram;
    private TramLineViewHolder.RecyclerItemClick callBack;

    TramLineRecyclerViewAdaptater(ArrayList<Tram> Lignes, TramLineViewHolder.RecyclerItemClick callBack){
        this.Tram = Lignes;
        this.callBack = callBack;
    }

    @NonNull
    @Override
    public TramLineViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Create a new view, which defines the UI of the list item
        View trameView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trame_recycler_cell_view,parent, false);
        return new TramLineViewHolder(trameView);
        // retourne la vue qu'il devra afficher et que l'adaptater gère
    }
    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(@NonNull TramLineViewHolder holder, int position) {
        // Get element from your dataset at this position and replace the
        // contents of the view with that element
         holder.onBind(Tram.get(position), callBack);

    }
    // Return the size of your dataset (invoked by the layout manager)
     @Override
     public int getItemCount() {
            return Tram.size();
        }

}
