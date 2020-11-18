package com.shindra.map

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.shindra.utilities.MyViewModel
import com.shindra.R
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line
import com.shindra.utilities.LoadingDialog

class MapLineActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        //Activity configuration
        setContentView(R.layout.map_line_activity)

        //data recovery
        val lineName = intent.getStringExtra("lineName")
        title = getLineName(lineName!!)

        //Fragment configuration
        val fragment = MapLineFragment()
        supportFragmentManager.beginTransaction().add(R.id.fragment_line_map,fragment).commit()

        //LoadingDialog creation
        val loadingDialog = LoadingDialog(this)

        //network call configuration
        val model = ViewModelProvider(this).get(MyViewModel::class.java)

        //perform network call
        model.lineWithStop(RouteType.TRAM,getTramLetter(lineName)).observe(object : ObservableListener<Line> {
            override fun onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
                //display LoadingDialog
                loadingDialog.showDialog()
            }

            override fun onSuccess(data: Line) {
                //call once the network call has responded with a success
                //MapFragment data change
                fragment.addPointOnMap(data)

                //remove LoadingDialog
                loadingDialog.dismissDialog()
            }

            override fun onError(throwable: Throwable) {
                //call if the network call has responded with an error
                //display error message
                Toast.makeText(applicationContext, R.string.loading_text_error, Toast.LENGTH_LONG).show()

                //remove LoadingDialog
                loadingDialog.dismissDialog()
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