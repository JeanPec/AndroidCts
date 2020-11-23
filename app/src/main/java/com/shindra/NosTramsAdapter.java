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

    interface RecyclerTimetableClick
    {
        void onTimetableClick (Line image);
    }

    private ArrayList <Line> nosTramsList;
    private RecyclerTimetableClick callBack;


    public static class NosTramsViewHolder extends RecyclerView.ViewHolder{

        public final ImageView ligneTram;


        public NosTramsViewHolder(@NonNull View itemView) {
            super(itemView);
            ligneTram = itemView.findViewById(R.id.imageLigneTram);
        }
        public void TramsOnBind(Line image, RecyclerTimetableClick callback)
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

            itemView.setOnClickListener (new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    callback.onTimetableClick(image);
                }
            });



        }
    }

    public NosTramsAdapter (ArrayList<Line> nosTramsList, RecyclerTimetableClick callBack) {
        this.nosTramsList = nosTramsList;
        this.callBack = callBack;
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
        holder.TramsOnBind(currentTram,callBack);
    }
    @Override
    public int getItemCount() {
        return nosTramsList.size();
    }
}
