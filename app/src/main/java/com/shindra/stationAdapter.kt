package com.shindra

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.shindra.ctslibrary.bo.Line
import com.shindra.ctslibrary.bo.Stop
import com.shindra.stationAdapter.stationDiaryViewHolder
import java.text.SimpleDateFormat
import java.util.*

class stationAdapter(private val _line: Line) :
    RecyclerView.Adapter<stationDiaryViewHolder>() {
    private val _listOfStop: ArrayList<Stop>

    class stationDiaryViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var stationNameView: TextView
        var lineNameView: TextView
        var scheduledTimeView: TextView
        fun onBind(line: Line, stop: Stop) {
            stationNameView.text = stop.name
            scheduledTimeView.text = SimpleDateFormat("HH'h'mm").format(stop.estimatedArrivalTime)
            lineNameView.text = lineNameView.context.getString(R.string.ligneText) + line.name
            lineNameView.setTextColor(
                ContextCompat.getColor(
                    lineNameView.context,
                    getLineColorFromLine(line)
                )
            )
        }

        init {
            stationNameView = itemView.findViewById(R.id.stationName)
            lineNameView = itemView.findViewById(R.id.tramLine)
            scheduledTimeView = itemView.findViewById(R.id.scheduledTime)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): stationDiaryViewHolder {
        val stationCardView = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardview_station, parent, false)
        return stationDiaryViewHolder(stationCardView)
    }

    override fun onBindViewHolder(
        holder: stationDiaryViewHolder,
        position: Int
    ) {
        val currentStop = _listOfStop[position]
        holder.onBind(_line, currentStop)
    }

    override fun getItemCount(): Int {
        return _listOfStop.size
    }

    private fun fillStopListFromLine(data: Line): ArrayList<Stop> {
        val tmpList = ArrayList<Stop>()
        for (stop in data.stops!!) {
            tmpList.add(stop)
        }
        return tmpList
    }

    companion object {
        fun getLineColorFromLine(line: Line): Int {
            return when (line.name) {
                "A" -> R.color.ligneA
                "B" -> R.color.ligneB
                "C" -> R.color.ligneC
                "D" -> R.color.ligneD
                "E" -> R.color.ligneE
                "F" -> R.color.ligneF
                else -> R.color.black
            }
        }
    }

    init {
        _listOfStop = fillStopListFromLine(_line)
    }
}