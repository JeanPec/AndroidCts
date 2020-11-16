package com.shindra

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat.getColor
import androidx.core.graphics.toColor
import androidx.recyclerview.widget.RecyclerView
import com.shindra.ctslibrary.bo.Stop
import java.text.SimpleDateFormat


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class TimeAdapter(private val stopList: List<Stop>, val lineName : String?) : RecyclerView.Adapter<TimeAdapter.TimeViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.horraire,
                parent, false)
        return TimeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: TimeViewHolder, position: Int) {
        val currentStop : Stop = stopList[position]
        holder.textStop.setText(currentStop.name)
        holder.textLine.setText(getLineName(lineName))
        holder.textLine.setTextColor(getColor(holder.context, getColorId(lineName)))
        holder.textTime.setText(SimpleDateFormat("HH'h'mm").format(currentStop.estimatedDepartureTime))
    }

    override fun getItemCount() = stopList.size

    class TimeViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        val textStop : TextView = itemView.findViewById(R.id.text_headline)
        val textLine : TextView = itemView.findViewById(R.id.text_body_line)
        val textTime : TextView = itemView.findViewById(R.id.text_time)
        val context : Context = itemView.context
    }
}

private fun getColorId( lineName : String?) : Int
{
    when (lineName) {
        "tram_a" -> return R.color.Ligne_A
        "tram_b" -> return R.color.Ligne_B
        "tram_c" -> return R.color.Ligne_C
        "tram_d" -> return R.color.Ligne_D
        "tram_e" -> return R.color.Ligne_E
        "tram_f" -> return R.color.Ligne_F
        else -> {
            return R.color.Primary
        }
    }
}

private fun getLineName( lineName : String?) : String
{
    when (lineName) {
        "tram_a" -> return "Ligne A"
        "tram_b" -> return "Ligne B"
        "tram_c" -> return "Ligne C"
        "tram_d" -> return "Ligne D"
        "tram_e" -> return "Ligne E"
        "tram_f" -> return "Ligne F"
        else -> {
            return "Ligne "
        }
    }
}
