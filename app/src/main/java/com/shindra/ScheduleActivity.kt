package com.shindra

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line

class ScheduleActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.schedule_activity)
        setTitle(R.string.schedule_activity_name)
        val lineName = intent.getStringExtra("lineName")

        val bundle = Bundle()
        bundle.putString("lineName", lineName)

        val fragmentStop = ScheduleFragmentStop()
        fragmentStop.arguments = bundle
        supportFragmentManager.beginTransaction().add(R.id.fragment_stop, fragmentStop).commit()

        val loadingDialog = LoadingDialog(this)

        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.lineWithEstimatedTimeTable(RouteType.TRAM, getTramLetter(lineName),0).observe(object : ObservableListener<Line> {
            override fun onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
                loadingDialog.showDialog()
            }

            override fun onSuccess(data: Line) {
                //call once the network call has responded with a success
                fragmentStop.addStop(data)
                loadingDialog.dismissDialog()
            }

            override fun onError(throwable: Throwable) {
                //call if the network call has responded with an error
            }
        })
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