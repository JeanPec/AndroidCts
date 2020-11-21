package com.shindra.Horaires

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.shindra.Dialogs.LoadingDialog
import com.shindra.MyViewModel
import com.shindra.R
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line
import java.text.SimpleDateFormat
import kotlin.collections.ArrayList

class HorairesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_horaires)

        val SelectedTramLine: String? = intent.extras?.getString("Ligne")
        setTitle(getString(R.string.horaires_header) + " " + SelectedTramLine)

        //Creation du dialogue de chargement
        val LoadingDialog = LoadingDialog()
        LoadingDialog.CreateDialog(this)

        val model = ViewModelProvider(this).get(MyViewModel::class.java)

        model.lineWithEstimatedTimeTable(RouteType.TRAM, SelectedTramLine.toString(),0).observe(object : ObservableListener<Line> {
            override fun onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
                LoadingDialog.ShowDialog()
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

                //Appeler et afficher le fragment des horaires
                var HorairesFragment = HorairesFragment()
                HorairesFragment.StopsNames = StopsNames
                HorairesFragment.DeparturesTimes = EstimatedDepartureTimes
                HorairesFragment.LineName = SelectedTramLine.toString()

                supportFragmentManager.beginTransaction()
                        .replace(R.id.Horaires_FrameLayout, HorairesFragment)
                        .commitAllowingStateLoss()

                //Fermer le dialogue de chargement à la fin de la fonction
                LoadingDialog.CloseDialog()
            }

            override fun onError(throwable: Throwable) {
                //call if the network call has responded with an error

                //Fermer le dialogue de chargement en cas d'erreur
                LoadingDialog.CloseDialog()
            }
        })
    }
}