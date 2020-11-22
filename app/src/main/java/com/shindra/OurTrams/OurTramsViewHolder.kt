package com.shindra.OurTrams

import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.shindra.Others.HelperLine
import com.shindra.R

class OurTramsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    // Attributes
    private var mImgTramLetter: ImageView = itemView.findViewById(R.id.cv_our_trams_imgTramLeter)
    private var mButtonSchedule: Button = itemView.findViewById(R.id.cv_our_trams_buttonSchedule)

    // Methods
    fun onBind(tram: OurTramsItem, listener: OurTramsAdapter.OnItemClickListener){
        mImgTramLetter.setImageResource(HelperLine.GetTramLetterImg(tram.getTramLineLetter()))

        /* Add listener on mButtonSchedule in each ViewHolder. Allows us to retrieve
           the ViewHolder which has been clicked to the Activity part */
        mButtonSchedule.setOnClickListener {
            listener.onButtonScheduleClick(
                    tram.getTramLineLetter()
            ) }
    }
}