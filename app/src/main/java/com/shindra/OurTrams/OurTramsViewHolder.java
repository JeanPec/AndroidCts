package com.shindra.OurTrams;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.OurTrams.OurTramsAdapter;
import com.shindra.OurTrams.OurTramsItem;
import com.shindra.R;

public class OurTramsViewHolder extends RecyclerView.ViewHolder{

    // Attributes
    public ImageView mImgTram;
    public ImageView mImgTramLetter;
    public Button mButtonSchedule;

    // Constructor
    public OurTramsViewHolder(@NonNull View itemView, OurTramsAdapter.OnItemClickListener listener) {
        super(itemView); // calls base class constructor: this.itemView = itemView
        mImgTram = itemView.findViewById(R.id.cv_our_trams_imgTram);
        mImgTramLetter = itemView.findViewById(R.id.cv_our_trams_imgTramLeter);
        mButtonSchedule = itemView.findViewById(R.id.cv_our_trams_buttonSchedule);

        mButtonSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(listener != null) {
                    int position = getAdapterPosition();
                    if(position != RecyclerView.NO_POSITION){
                        listener.onButtonScheduleClick(position);
                    }
                }
            }
        });
    }

    // Methods
    public void onBind(OurTramsItem tram)
    {
        mImgTram.setImageResource(tram.getmImgTram());
        mImgTramLetter.setImageResource(tram.getmImgTramLetter());
    }
}