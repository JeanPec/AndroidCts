package com.shindra.Time

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.shindra.LoadingClass
import com.shindra.MyViewModel
import com.shindra.R
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line
import com.shindra.ctslibrary.bo.Stop
import java.util.ArrayList

class TimeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.time_fragment_holder)
        title = "Horaire"

        val line_name : String? = intent.getStringExtra("name")
        val dialog = LoadingClass(this)
        val listStop = ArrayList<Stop>()

        val bundle = Bundle()
        bundle.putString("name",line_name)
        val fragment = TimeFragment()
        fragment.arguments = bundle
        supportFragmentManager.beginTransaction().add(R.id.time_frameLayout,fragment).commit()

        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.lineWithEstimatedTimeTable(RouteType.TRAM, getLineRef(line_name),0).observe(object : ObservableListener<Line> {
            override fun onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
                dialog.show()
                Log.i("Loading","Loading")
            }

            override fun onSuccess(data: Line) {
                dialog.dismiss()
                Log.i("Loading","onSuccess")
                //call once the network call has responded with a success
                for (stop in data.stops!!) {
                    if(stop.name == null){
                        // handle null result, eg. throw exception, return default, etc
                    }  else {
                        listStop+=(stop)
                    }
                }
                fragment.setListStop(listStop)
            }

            override fun onError(throwable: Throwable) {
                //call if the network call has responded with an error
            }
    })

    }
}




fun getLineRef(line_name : String?):String{
    when (line_name) {
        "Parc des Sports - Illkirch Graffenstaden" -> return "A"
        "Lingolsheim Tiergaertel - Hoenheim Gare" -> return "B"
        "Gare Centrale - Neuhof Rodolphe Reuss" -> return "C"
        "Poteries - Port du Rhin / Kehl Rathaus" -> return "D"
        "Robertsau l'Escale - Campus d'Illkirch" -> return "E"
        "Comtes - Place d'Islande" -> return "F"
        else -> {
            return "A"
        }
    }
}