package com.shindra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.ctslibrary.bo.Line;

import java.util.ArrayList;

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.MyViewHolder> {

    ArrayList<Line> lines;
    RecyclerItemClick callback;

    RVAdapter(ArrayList<Line> lines,RecyclerItemClick callback){
        this.lines = lines;
        this.callback = callback;
    }

    /*int images1[];
    int images2;
    Context context;




    RVAdapter(Context ct, int img1[], int img2){
        context = ct;
        images1 = img1;
        images2 = img2;
    }*/

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Line newLigne = lines.get(position);
        holder.myImg1.setImageResource(Image(newLigne));
        holder.OnBind(newLigne,callback);
    }

    @Override
    public int getItemCount() {
        return lines.size();
    }

    public void setLine(ArrayList<Line> lines)
    {
        this.lines = lines;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        ImageView myImg1, myImg2;
        Button Bhoraire;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myImg1 = itemView.findViewById(R.id.imageView);
            myImg2 = itemView.findViewById(R.id.imageView2);
            this.Bhoraire = itemView.findViewById((R.id.button));
        }
        public void OnBind(Line line, RecyclerItemClick callback)
        {
            Bhoraire.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v)
                {
                    callback.OnClick(line);
                }
            });
        }

    }

    private int Image(Line line)
    {
        switch (line.getName())
        {
            case "Parc des Sports - Illkirch Graffenstaden":
                return R.drawable.tram_a;
            case "Lingolsheim Tiergaertel - Hoenheim Gare":
                return R.drawable.tram_b;
            case "Gare Centrale - Neuhof Rodolphe Reuss":
                return R.drawable.tram_c;
            case "Poteries - Port du Rhin / Kehl Rathaus":
                return R.drawable.tram_d;
            case "Robertsau l'Escale - Campus d'Illkirch":
                return R.drawable.tram_e;
            case "Comtes - Place d'Islande":
                return R.drawable.tram_f;
            default:
                return R.drawable.tram;
        }
    }

}
