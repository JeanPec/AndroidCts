package com.shindra.Line

import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.shindra.R
import com.shindra.ctslibrary.bo.Line

class LineViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)
{
    var tramIcon: ImageView = itemView.findViewById(R.id.tramIcon);
    var scheduleButton : Button = itemView.findViewById(R.id.scheduleButton);

    fun onBind(line : Line, callback : ILineClickable)
    {
        scheduleButton.setOnClickListener {callback.onLineClick(line)}
    }
}