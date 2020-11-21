package com.shindra;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Line;

import java.util.ArrayList;
import java.util.List;

import static com.shindra.R.id.imgTram;

public class TramCardAdapter extends RecyclerView.Adapter<TramCardAdapter.TramViewHolder> {

    ArrayList<Line> listeTramCard;
    RecyclerItemClick callback;

    public static class TramViewHolder extends RecyclerView.ViewHolder {

        //TextView nomLigne;
        ImageView imgNomLigne, imgTram;
        Button  horaireButton;


        @SuppressLint("WrongViewCast")
        public TramViewHolder(@NonNull View itemView) {
            super(itemView);
           // nomLigne = itemView.findViewById(R.id.nomLigne);
            imgTram = itemView.findViewById(R.id.imgTram);
            imgNomLigne = itemView.findViewById(R.id.imgLigne);
            horaireButton = itemView.findViewById(R.id.horaireButton);



        }

        public void onBind(TramCard tramCard, RecyclerItemClick callback) {
            imgTram.setImageDrawable(tramCard.imgTram);
            imgNomLigne.setImageDrawable(tramCard.imgNomLigne);

            horaireButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callback.onHoraireButtonClick(tramCard);
                }
            });


        }


    }

    public TramCardAdapter(ArrayList<Line> listeTramCard, RecyclerItemClick callback) {
        this.listeTramCard = listeTramCard;
        this.callback = callback;
    }

    @NonNull
    @Override
    public TramViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_ligne_tram, parent, false);
        TramViewHolder tramViewHolder = new TramViewHolder(view);
        return tramViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull TramViewHolder holder, int position) {
        TramCard tramCard = listeTramCard.get(position);
        //holder.nomLigne.setText(tramCard.getNomLigne());
        holder.onBind(tramCard, callback);


    }

    @Override
    public int getItemCount() {
        return listeTramCard.size();
    }


}
