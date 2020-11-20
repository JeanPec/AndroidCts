package com.shindra.schedules

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.shindra.R
import com.shindra.ctslibrary.bo.Stop
import java.text.SimpleDateFormat
import java.util.*

class ScheduleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    private val textHeadline: TextView = itemView.findViewById(R.id.text_headline)
    private val textLine: TextView = itemView.findViewById(R.id.text_line)
    private val textHour: TextView = itemView.findViewById(R.id.text_hour)
    private val textBody: TextView = itemView.findViewById(R.id.text_body)

    fun onBind(line: String, stop: Stop) {
        //set ScheduleViewHolder data
        textHeadline.text = stop.name
        textLine.text = getLineName(line)
        textLine.setTextColor(ContextCompat.getColor(textLine.context,getLineColor(line)))
        textHour.text = stop.estimatedDepartureTime?.let{ SimpleDateFormat("HH'h'mm", Locale.FRANCE).format(stop.estimatedDepartureTime?.time) }
        textBody.text = itemView.context.getString(R.string.schedule_card_view_body)
    }

    private fun getLineColor(line: String) : Int {
        return when (line){
            itemView.context.getString(R.string.line_a) -> R.color.LigneA
            itemView.context.getString(R.string.line_b) -> R.color.LigneB
            itemView.context.getString(R.string.line_c) -> R.color.LigneC
            itemView.context.getString(R.string.line_d) -> R.color.LigneD
            itemView.context.getString(R.string.line_e) -> R.color.LigneE
            itemView.context.getString(R.string.line_f) -> R.color.LigneF
            else -> R.color.Body2
        }
    }

    private fun getLineName(line: String) : String {
        return when (line){
            itemView.context.getString(R.string.line_a) -> itemView.context.getString(R.string.line_a_display)
            itemView.context.getString(R.string.line_b) -> itemView.context.getString(R.string.line_b_display)
            itemView.context.getString(R.string.line_c) -> itemView.context.getString(R.string.line_c_display)
            itemView.context.getString(R.string.line_d) -> itemView.context.getString(R.string.line_d_display)
            itemView.context.getString(R.string.line_e) -> itemView.context.getString(R.string.line_e_display)
            itemView.context.getString(R.string.line_f) -> itemView.context.getString(R.string.line_f_display)
            else -> itemView.context.getString(R.string.line_error_display)
        }
    }
}