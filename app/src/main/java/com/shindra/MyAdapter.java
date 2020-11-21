package com.shindra;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.shindra.ctslibrary.bo.Line;
import java.util.ArrayList;

//herite of mother class RecyclerView.Adapter<T>
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    //If a new line is created a picture of the imageTramLine need to be added
    //one idea is to add some other images to imageTramLine
    //and depending on the texTramLine size we display the available lines
    ArrayList<Line> lineAPI;
    RecyclerHoraireClick callBack;

    public MyAdapter(ArrayList<Line> lin, RecyclerHoraireClick cal){
        lineAPI = lin;
        callBack = cal;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            //last parameter false for recycler view
            View view = inflater.inflate(R.layout.row_tram, parent, false);
            //return the view depending on the layout we gave
            return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //for the tram line letter (TRAM A to F)
        switch (lineAPI.get(position).getName()){
            case "A":
                holder.onBind(R.drawable.nouveau_tram_strasbourg,R.drawable.tram_a,lineAPI.get(position),callBack);
                break;
            case "B":
                holder.onBind(R.drawable.nouveau_tram_strasbourg,R.drawable.tram_b,lineAPI.get(position),callBack);
                break;
            case "C":
                holder.onBind(R.drawable.nouveau_tram_strasbourg,R.drawable.tram_c,lineAPI.get(position),callBack);
                break;
            case "D":
                holder.onBind(R.drawable.nouveau_tram_strasbourg,R.drawable.tram_d,lineAPI.get(position),callBack);
                break;
            case "E":
                holder.onBind(R.drawable.nouveau_tram_strasbourg,R.drawable.tram_e,lineAPI.get(position),callBack);
                break;
            case "F":
                holder.onBind(R.drawable.nouveau_tram_strasbourg,R.drawable.tram_f,lineAPI.get(position),callBack);
                break;
            default:
                holder.onBind(R.drawable.nouveau_tram_strasbourg,R.drawable.tram,lineAPI.get(position),callBack);
        }
    }

    @Override
    public int getItemCount() {
        return lineAPI.size();
    }

    /*
    Each element of the list is represented by a ViewHolder
    A ViewHolder is responsible of displaying his view in the list
    They are managed by the Adapter
     */

    //herite of the mother class RecyclerView.ViewHolder
    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewTram,imageViewLine;
        Button BtHoraire;

        //constructor take as parameter a view
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewTram = itemView.findViewById(R.id.imageViewTram);
            imageViewLine = itemView.findViewById(R.id.imageViewLine);
            BtHoraire = itemView.findViewById(R.id.button1);
        }


        //The onBind() binds the data to the view that we pass in parameter of the constructor
        public void onBind(int PicTramStrasbourg,int PicLine, Line selectedLine, RecyclerHoraireClick callb){
            imageViewLine.setImageResource(PicLine);
            imageViewTram.setImageResource(PicTramStrasbourg);
            BtHoraire.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callb.onHoraireClick(selectedLine);
                }
            });
        }
    }

    //interface for the button listener
    public interface RecyclerHoraireClick {
        void onHoraireClick(Line line);
    }
}
