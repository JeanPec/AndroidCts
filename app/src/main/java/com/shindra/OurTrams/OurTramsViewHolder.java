package com.shindra.OurTrams;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.CT;
import com.shindra.R;

public class OurTramsViewHolder extends RecyclerView.ViewHolder{

    // Attributes
    private ImageView mImgTramLetter;
    private Button mButtonSchedule;

    // Constructor
    public OurTramsViewHolder(@NonNull View itemView) {
        super(itemView);
        mImgTramLetter = itemView.findViewById(R.id.cv_our_trams_imgTramLeter);
        mButtonSchedule = itemView.findViewById(R.id.cv_our_trams_buttonSchedule);
    }

    // Methods
    public void onBind(OurTramsItem tram, OurTramsAdapter.OnItemClickListener listener)
    {
        mImgTramLetter.setImageResource(CT.GetTramLetterImg(tram.GetTramLineLetter()));

        // Add listener on mButtonSchedule in each ViewHolder
        mButtonSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onButtonScheduleClick(tram.GetTramLineLetter());
            }
        });
    }
}