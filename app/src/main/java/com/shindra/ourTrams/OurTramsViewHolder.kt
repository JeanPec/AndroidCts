package com.shindra.ourTrams

import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.shindra.HelperLine
import com.shindra.R

class OurTramsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    // Attributes
    private val mImgTramLetter: ImageView = itemView.findViewById(R.id.cv_our_trams_imgTramLeter)
    private val mButtonSchedule: Button = itemView.findViewById(R.id.cv_our_trams_buttonSchedule)

    // Methods
    fun onBind(tram: OurTramsItem, listener: OurTramsAdapter.OnItemClickListener){
        mImgTramLetter.setImageResource(HelperLine.getTramLetterImg(tram.mTramLineLetter))

        /* Add listener on mButtonSchedule in each ViewHolder. Allows us to retrieve
           the ViewHolder which has been clicked to the Activity part */
        mButtonSchedule.setOnClickListener {
            listener.onButtonScheduleClick(tram.mTramLineLetter)
        }
    }
}