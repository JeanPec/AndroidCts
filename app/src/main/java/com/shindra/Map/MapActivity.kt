package com.shindra.Map

import android.app.AlertDialog
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProvider
import com.shindra.MyViewModel
import com.shindra.R
import com.shindra.Schedule.ScheduleRecyclerViewAdapter
import com.shindra.arrakis.controls.Poi
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.Coordinate
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line
import com.shindra.ctslibrary.bo.Stop
import java.sql.DriverManager
import java.util.ArrayList


class MapActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val fragmentMap = FragmentMap()

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentMap, fragmentMap);
        fragmentTransaction.commit();


        val intent = intent
        val lineID = intent.getStringExtra("LINE_ID")
        println("OUT $lineID")
        title = "Ligne $lineID"

        val loadingDialogView = LayoutInflater.from(this).inflate(R.layout.loading_dialog,null)
        val dialogBuilder = AlertDialog.Builder(this).setView(loadingDialogView)
        val loadingDialog = dialogBuilder.create()


        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        if (lineID != null) {
            model.lineWithStop(RouteType.TRAM, lineID).observe(object : ObservableListener<Line> {
                override fun onLoading() {
                    //call once we started the network called. Indicate that the network call is in progress
                    loadingDialog.show()
                }

                override fun onSuccess(data: Line) {
                    val pois = ArrayList<Poi>()
                    for (stop in data.stops!!) {
                        val poi = Poi(R.drawable.icon_maps_place_24px, getLineColor(lineID), (stop.position!!.latitude) as Double, stop.position!!.longitude as Double)
                        pois.add(poi)
                    }

                    fragmentMap.addPoi(pois)

                    loadingDialog.dismiss()
                }

                override fun onError(throwable: Throwable) {
                    //call if the network call has responded with an error
                }
            })
        }
    }

    fun  getLineColor(lineID:String) : Int {
        return when (lineID) {
            "A" -> {
                R.color.ligne_A
            }
            "B" -> {
                R.color.ligne_B
            }
            "C" -> {
                R.color.ligne_C
            }
            "D" -> {
                R.color.ligne_D
            }
            "E" -> {
                R.color.ligne_E
            }
            "F" -> {
                R.color.ligne_F
            }
            else -> {
                R.color.black
            }
        }

    }
}
