package com.shindra.Horaires

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.shindra.R

class HorairesViewHolder (GenericCardView: View, _Context:Context) : RecyclerView.ViewHolder(GenericCardView){

val Stop_TextView: TextView = GenericCardView.findViewById(R.id.textView_Stop);
val Line_TextView: TextView = GenericCardView.findViewById(R.id.textView_Line);
val Time_TextView: TextView = GenericCardView.findViewById(R.id.textView_Time);
val context = _Context;

    fun PutTimeInfoIntoCardView(StopName : String, DepartureTime : String, LineName : String) {

        Stop_TextView.setText(StopName)
        Time_TextView.setText(DepartureTime)
        Line_TextView.setText("Ligne " + LineName)

        //En fonction du nom de la ligne, on doit afficher le texte de la bonne couleur
        Line_TextView.setTextColor(ContextCompat.getColor(context,  when (LineName) {
            "A" -> R.color.LigneA
            "B" -> R.color.LigneB
            "C" -> R.color.LigneC
            "D" -> R.color.LigneD
            "E" -> R.color.LigneE
            "F" -> R.color.LigneF
            else -> {
                R.color.black
            }
        }))
    }

}