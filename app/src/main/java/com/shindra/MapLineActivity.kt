package com.shindra

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line
import java.util.ArrayList

class MapLineActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.map_line_activity)
        val lineName = intent.getStringExtra("lineName")
        title = getLineName(lineName!!)

        val fragment = MapLineFragment()
        supportFragmentManager.beginTransaction().add(R.id.fragment_line_map,fragment).commit()

        val loadingDialog = LoadingDialog(this)

        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.lineWithStop(RouteType.TRAM,getTramLetter(lineName)).observe(object : ObservableListener<Line> {

            override fun onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
                loadingDialog.showDialog()
            }

            override fun onSuccess(data: Line) {
                //call once the network call has responded with a success

                fragment.addPointOnMap(data)

                loadingDialog.dismissDialog()
            }

            override fun onError(throwable: Throwable) {
                //call if the network call has responded with an error
            }
        })
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

    private fun getTramLetter(line: String?) : String {
        return when (line){
            "Parc des Sports - Illkirch Graffenstaden" -> "A"
            "Lingolsheim Tiergaertel - Hoenheim Gare" -> "B"
            "Gare Centrale - Neuhof Rodolphe Reuss" -> "C"
            "Poteries - Port du Rhin / Kehl Rathaus" -> "D"
            "Robertsau l'Escale - Campus d'Illkirch" -> "E"
            "Comtes - Place d'Islande" -> "F"
            else -> "X"
        }
    }
}