package com.shindra

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.shindra.ctslibrary.bo.Line
import java.util.*

class StartActivityAdapter(private val _lineList: ArrayList<Line>, private val _callBack: RecyclerItemClick) : RecyclerView.Adapter<StartActivityAdapter.tramViewHolder>() {

    interface RecyclerItemClick {
        fun onDiaryButtonClick(tram: Line?)
    }

    val obj = object : RecyclerItemClick {
        override fun onDiaryButtonClick(tram: Line?) {}
    }

    class tramViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tramLineView: ImageView
        var tramIllustrationView: ImageView
        var buttonToStationDiary: Button
        fun onBind(tram: Line?, callback: RecyclerItemClick) {
            tramIllustrationView.setImageResource(R.drawable.nouveau_tram_strasbourg)
            buttonToStationDiary.setOnClickListener { callback.onDiaryButtonClick(tram) }
        }

        init {
            tramLineView = itemView.findViewById(R.id.tramLinePicture)
            tramIllustrationView = itemView.findViewById(R.id.illustrationPicture)
            buttonToStationDiary = itemView.findViewById(R.id.diaryStationButton)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): tramViewHolder {
        val tramCardView = LayoutInflater.from(parent.context).inflate(R.layout.cardview_tram, parent, false)
        return tramViewHolder(tramCardView)
    }

    override fun onBindViewHolder(holder: tramViewHolder, position: Int) {
        val currentTram = _lineList[position]
        when (currentTram.name) {
            "A" -> holder.tramLineView.setImageResource(R.drawable.tram_a)
            "B" -> holder.tramLineView.setImageResource(R.drawable.tram_b)
            "C" -> holder.tramLineView.setImageResource(R.drawable.tram_c)
            "D" -> holder.tramLineView.setImageResource(R.drawable.tram_d)
            "E" -> holder.tramLineView.setImageResource(R.drawable.tram_e)
            "F" -> holder.tramLineView.setImageResource(R.drawable.tram_f)
            else -> holder.tramLineView.setImageResource(R.drawable.tram)
        }

        //holder.tramIllustrationView.setImageResource(R.drawable.nouveau_tram_strasbourg);
        holder.onBind(currentTram, _callBack)
    }

    override fun getItemCount(): Int {
        return _lineList.size
    }

}