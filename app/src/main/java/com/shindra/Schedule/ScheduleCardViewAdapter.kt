package com.shindra.Schedule

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.shindra.R
import com.shindra.RecyclerItemClick
import com.shindra.ctslibrary.bo.Line
import com.shindra.ctslibrary.bo.Stop
import java.util.*

class ScheduleCardViewAdapter(line: Line?, callback: RecyclerItemClick):
    RecyclerView.Adapter<ScheduleCardViewAdapter.ScheduleCardViewHolder>() {
    var line:Line? = line
    var callback : RecyclerItemClick = callback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleCardViewHolder {
        //1- Charger la vue en xml.
        val rootView = LayoutInflater.from(parent.context).inflate(R.layout.schedule_cardview, parent, false)
        //2- Créer ViewHolder pour controler la vue.
        val holder = ScheduleCardViewHolder(rootView, parent.context)
        //3- Retourner le ViewHolder.
        return holder
    }

    override fun onBindViewHolder(holder: ScheduleCardViewHolder, position: Int) {
        holder.onBind(line, line?.stops?.get(position))
    }

    override fun getItemCount(): Int
    {
        var int :Int= line?.stops!!.size
        return int
    }
    fun setStops(lineWithStops: Line?)
    {
        line = lineWithStops
    }
    class ScheduleCardViewHolder(itemView: View, context: Context):RecyclerView.ViewHolder(itemView)
    {
        var context : Context = context
        var stopTextView : TextView = itemView.findViewById(R.id.stopTextView)
        var lineTextView : TextView = itemView.findViewById(R.id.lineTextView)
        var hoursTextView : TextView = itemView.findViewById(R.id.hoursTextView)
        var indicationTextView : TextView = itemView.findViewById(R.id.indicationTextView)
        public fun  onBind(line: Line?, stop: Stop?)
        {
            lineTextView.setTextColor(ContextCompat.getColor(context,when(line?.name)
            {
                "A"-> R.color.LigneA
                "B"-> R.color.LigneB
                "C"-> R.color.LigneC
                "D"-> R.color.LigneD
                "E"-> R.color.LigneE
                "F"-> R.color.LigneF
                else->
                {
                    R.color.black
                }
            }))
            stopTextView.setText(stop?.name.toString())
            lineTextView.setText("Ligne " + line?.name)
            val date = stop?.estimatedArrivalTime
            val calendar = Calendar.getInstance()
            calendar.time = date
            val hours = calendar.get(Calendar.HOUR_OF_DAY)
            val minutes = String.format("%02d", calendar.get(Calendar.MINUTE))
            hoursTextView.setText("$hours" + 'h' + "$minutes")
            indicationTextView.setText("Prochain départ")
        }
    }

}