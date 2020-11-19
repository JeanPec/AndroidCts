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
    private ImageView mImgTram;
    private ImageView mImgTramLetter;
    private Button mButtonSchedule;

    // Constructor
    public OurTramsViewHolder(@NonNull View itemView) {
        super(itemView);
        mImgTram = itemView.findViewById(R.id.cv_our_trams_imgTram);
        mImgTramLetter = itemView.findViewById(R.id.cv_our_trams_imgTramLeter);
        mButtonSchedule = itemView.findViewById(R.id.cv_our_trams_buttonSchedule);
    }

    // Methods
    public void onBind(OurTramsItem tram, OurTramsAdapter.OnItemClickListener listener)
    {
        mImgTram.setImageResource(tram.GetImgTram());
        mImgTramLetter.setImageResource(tram.GetImgTramLetter());

        // Add listener on mButtonSchedule in each ViewHolder
        mButtonSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onButtonScheduleClick(tram.GetTramLineName());
            }
        });
    }
}