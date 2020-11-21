package com.shindra.time

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
        holder.onBind(currentStop,lineName)
    }

    override fun getItemCount() = stopList.size

    class TimeViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val textStop : TextView = itemView.findViewById(R.id.text_stop)
        val textLine : TextView = itemView.findViewById(R.id.text_body_line)
        val textTime : TextView = itemView.findViewById(R.id.text_time)
        val context : Context = itemView.context

        fun onBind(current_stop : Stop, line_name : String?){
            textStop.setText(current_stop.name)
            textLine.setText(context.getString(R.string.line).plus(" ").plus(line_name))
            textLine.setTextColor(getColor(context, getColorId(line_name)))
            current_stop.estimatedDepartureTime?.let{ textTime.text = SimpleDateFormat("HH'h'mm", Locale.FRANCE).format(current_stop.estimatedDepartureTime)}
        }

    }
}

private fun getColorId( lineName : String?) : Int
{
    return when (lineName) {
        "A" -> R.color.Ligne_A
        "B" -> R.color.Ligne_B
        "C" -> R.color.Ligne_C
        "D" -> R.color.Ligne_D
        "E" -> R.color.Ligne_E
        "F" -> R.color.Ligne_F
        else -> {
            R.color.Primary
        }
    }
}
