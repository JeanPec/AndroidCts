package com.shindra.OurTrams;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.shindra.HelperLine;
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
        mImgTramLetter.setImageResource(HelperLine.GetTramLetterImg(tram.GetTramLineLetter()));

        /* Add listener on mButtonSchedule in each ViewHolder. Allows us to retrieve
           the ViewHolder which has been clicked to the Activity part */
        mButtonSchedule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onButtonScheduleClick(tram.GetTramLineLetter());
            }
        });
    }
}