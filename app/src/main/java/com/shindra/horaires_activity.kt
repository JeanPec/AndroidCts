package com.shindra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*
import kotlin.collections.ArrayList

class horaires_activity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horaires)

        val SelectedTramLine: String? = intent.extras?.getString("Ligne")
        setTitle("Horaires - Ligne " + SelectedTramLine)

        //Lancement du dialogue de chargement
        val DialogBuilder= AlertDialog.Builder(this)
        val dialogView=layoutInflater.inflate(R.layout.loading_dialog,null)
        DialogBuilder.setView(dialogView)
        DialogBuilder.setCancelable(false)
        val Dialog = DialogBuilder.create()
        val model = ViewModelProvider(this).get(MyViewModel::class.java)

        model.lineWithEstimatedTimeTable(RouteType.TRAM, SelectedTramLine.toString(),0).observe(object : ObservableListener<Line> {
            override fun onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
                Dialog.show()
            }

            override fun onSuccess(data: Line) {
                //call once the network call has responded with a succes

                val LineStops = data.stops
                var StopsNames : MutableList<String> = ArrayList()
                var EstimatedDepartureTimes : MutableList<String> = ArrayList()

                LineStops?.forEach {
                    if(it.name != "" && it.estimatedDepartureTime != null) {
                        StopsNames.add(it.name.toString())
                        EstimatedDepartureTimes.add(SimpleDateFormat("HH'h'mm").format(it.estimatedDepartureTime?.time))
                    }
                }

                //Appeler et afficher le fragment uniquement quand on a fini l'appel réseau, pour éviter les conflits entre les différentes instances du fragment
                var HorairesFragment = HorairesFragment()
                HorairesFragment.StopsNames = StopsNames
                HorairesFragment.DeparturesTimes = EstimatedDepartureTimes
                HorairesFragment.LineName = SelectedTramLine.toString()

                supportFragmentManager.beginTransaction()
                        .replace(R.id.Horaires_FrameLayout, HorairesFragment)
                        .commitAllowingStateLoss()

                //Fermer le dialogue de chargement à la fin de la fonction
                Dialog.dismiss()
            }

            override fun onError(throwable: Throwable) {
                //call if the network call has responded with an error
            }
        })
    }
}