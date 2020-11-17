package com.shindra

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.shindra.ctslibrary.bo.Stop
import java.text.SimpleDateFormat
import kotlin.collections.ArrayList

class ScheduleAdapter(private val line: String, var stops: ArrayList<Stop>, private val context: FragmentActivity?) : RecyclerView.Adapter<ScheduleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val lineView = LayoutInflater.from(parent.context).inflate(R.layout.schedule_card_view, parent, false)
        return ScheduleViewHolder(lineView)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        val stop = stops[position]
        holder.textHeadline.text = stop.name
        holder.textLine.text = getLineName(line)
        holder.textLine.setTextColor(ContextCompat.getColor(holder.textLine.context,getLineColor(line)))
        if (stop.estimatedDepartureTime != null){
            holder.textHour.text = SimpleDateFormat("HH'h'mm").format(stop.estimatedDepartureTime?.time)
        }
        holder.textBody.text = context?.getString(R.string.schedule_card_view_body)
    }

    override fun getItemCount(): Int {
        return stops.size
    }

    private fun getLineColor(line: String) : Int {
        return when (line){
            "Parc des Sports - Illkirch Graffenstaden" -> R.color.LigneA
            "Lingolsheim Tiergaertel - Hoenheim Gare" -> R.color.LigneB
            "Gare Centrale - Neuhof Rodolphe Reuss" -> R.color.LigneC
            "Poteries - Port du Rhin / Kehl Rathaus" -> R.color.LigneD
            "Robertsau l'Escale - Campus d'Illkirch" -> R.color.LigneE
            "Comtes - Place d'Islande" -> R.color.LigneF
            else -> R.color.Body2
        }
    }

    private fun getLineName(line: String) : String {
        return when (line){
            "Parc des Sports - Illkirch Graffenstaden" -> "Ligne A"
            "Lingolsheim Tiergaertel - Hoenheim Gare" -> "Ligne B"
            "Gare Centrale - Neuhof Rodolphe Reuss" -> "Ligne C"
            "Poteries - Port du Rhin / Kehl Rathaus" -> "Ligne D"
            "Robertsau l'Escale - Campus d'Illkirch" -> "Ligne E"
            "Comtes - Place d'Islande" -> "Ligne F"
            else -> "Ligne Erreur"
        }
    }
}