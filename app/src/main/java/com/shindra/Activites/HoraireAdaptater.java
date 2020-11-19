package com.shindra.Activites;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.shindra.R;
import java.util.ArrayList;

public class HoraireAdaptater extends RecyclerView.Adapter<HoraireViewHolder> {

    private ArrayList<TrameStop> TramStop;

    public HoraireAdaptater(ArrayList<TrameStop> StopsInfo){
        this.TramStop = StopsInfo ;
    }

    @NonNull
    @Override
    public HoraireViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View horaireView = LayoutInflater.from(parent.getContext())
        .inflate(R.layout.view_line_horaire_layout,parent, false);

        return new HoraireViewHolder(horaireView);
    }

    @Override
    public void onBindViewHolder(@NonNull HoraireViewHolder holder, int position) {
        holder.onBind(TramStop.get(position));
    }


    @Override
    public int getItemCount() {return TramStop.size();}
}
