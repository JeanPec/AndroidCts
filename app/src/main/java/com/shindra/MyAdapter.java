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

//heritage of mother class RecyclerView.Adapter<T>
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    //If a new line is created a picture of the imageTramLine need to be added
    //one idea is to add some other images to imageTramLine
    //and depending on the texTramLine size we display the available lines
    String linesFromAPI[];
    int nbTram;
    ArrayList<Line> lineAPI;
    RecyclerHoraireClick callBack;

    public MyAdapter(int siz, ArrayList<Line> lin, RecyclerHoraireClick cal){
        nbTram = siz;
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
        holder.onBindPicTram(R.drawable.nouveau_tram_strasbourg);
        //holder.onBindPicLine(imagesTramLine[position]);
        //holder.onBindString(lineAPI.get(position).getName());

        switch (lineAPI.get(position).getName()){
            case "A":
                holder.onBindPicLine(R.drawable.tram_a);
                break;
            case "B":
                holder.onBindPicLine(R.drawable.tram_b);
                break;
            case "C":
                holder.onBindPicLine(R.drawable.tram_c);
                break;
            case "D":
                holder.onBindPicLine(R.drawable.tram_d);
                break;
            case "E":
                holder.onBindPicLine(R.drawable.tram_e);
                break;
            case "F":
                holder.onBindPicLine(R.drawable.tram_f);
                break;
            default:
                holder.onBindPicLine(R.drawable.tram);
        }

        holder.onBindHoraire(lineAPI.get(position),callBack);

        /*
        for (int j = 0; j < lineAPI.size() ; j++) {
            linesFromAPI[j] = lineAPI.get(j).getName().toString();
        }
    /*
        //set on click listener : click on "horaire" button to open the other activity
        //this has to be changed because it is not the best way to do it
        //however I tried but I could not do it like the it is shown on the lesson powerpoint
        holder.HoraireTram.setOnClickListener(new View.OnClickListener() {
            @Override
                public void onClick(View v) {
                Intent intent = new Intent(context, SecondActivity.class);
                //dynamicaly get data from the string array
                intent.putExtra("textTramLine", textTramLine[position]);
                //intent.putExtra("imagesTramLine",imagesTramLine);
                context.startActivity(intent); //open the TimeActivity with the intent
                }
        });
     */

    }

    @Override
    public int getItemCount() {
        return nbTram;
    }

    /*
    Each element of the list is represented by a ViewHolder
    A ViewHolder is responsible of displaying his view in the list
    They are managed by the Adapter
     */

    //heritage of the mother class RecyclerView.ViewHolder
    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageViewTram,imageViewLine;
        Button BtHoraire;
        TextView textLines;
        //constructor take as parameter a view
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            imageViewTram = itemView.findViewById(R.id.imageViewTram);
            imageViewLine = itemView.findViewById(R.id.imageViewLine);
            BtHoraire = itemView.findViewById(R.id.button1);
            textLines = itemView.findViewById(R.id.textView);
        }


        //The onBind() binds the data to the view
        //that we pass in parameter of the constructor
        public  void onBindPicTram(int PicTramStrasbourg){ imageViewTram.setImageResource(PicTramStrasbourg); }

        public void onBindPicLine(int PicLine){ imageViewLine.setImageResource(PicLine); }

        public void onBindString(String s){ textLines.setText(s);}

        public void onBindHoraire(Line selectedLine, RecyclerHoraireClick callb){
            BtHoraire.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    callb.onHoraireClick(selectedLine);
                }
            });
        }
    }

    public interface RecyclerHoraireClick {
        void onHoraireClick(Line line);
    }
}
