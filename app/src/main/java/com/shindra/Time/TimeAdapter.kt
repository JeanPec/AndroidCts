package com.shindra.Time

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.RecyclerView
import com.shindra.R
import com.shindra.ctslibrary.bo.Stop
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class TimeAdapter(var stopList: ArrayList<Stop>, val lineName : String?) : RecyclerView.Adapter<TimeAdapter.TimeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.stop,
                parent, false)
        return TimeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TimeViewHolder, position: Int) {
        val currentStop : Stop = stopList[position]
        holder.textStop.setText(currentStop.name)
        holder.textLine.setText("Ligne".plus(" ").plus(lineName))
        holder.textLine.setTextColor(getColor(holder.context, getColorId(lineName)))
        if (currentStop.estimatedDepartureTime != null){
            holder.textTime.text = SimpleDateFormat("HH'h'mm", Locale.FRANCE).format(currentStop.estimatedDepartureTime)
        }
    }

    override fun getItemCount() = stopList.size

    class TimeViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val textStop : TextView = itemView.findViewById(R.id.text_stop)
        val textLine : TextView = itemView.findViewById(R.id.text_body_line)
        val textTime : TextView = itemView.findViewById(R.id.text_time)
        val context : Context = itemView.context
        }
}

private fun getColorId( lineName : String?) : Int
{
    when (lineName) {
        "A" -> return R.color.Ligne_A
        "B" -> return R.color.Ligne_B
        "C" -> return R.color.Ligne_C
        "D" -> return R.color.Ligne_D
        "E" -> return R.color.Ligne_E
        "F" -> return R.color.Ligne_F
        else -> {
            return R.color.Primary
        }
    }
}
