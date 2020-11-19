package com.shindra

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.ViewModelProvider
import com.shindra.arrakis.controls.Poi
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line
import okhttp3.internal.notifyAll

class MapActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_map)

        val SelectedTramLine: String? = intent.extras?.getString("Ligne")
        setTitle("Carte - Ligne " + SelectedTramLine)

        //Appeler et afficher le fragment de la carte
        var _MapFragment = MapFragment()
        supportFragmentManager.beginTransaction()
            .replace(R.id.Map_FrameLayout, _MapFragment)
            .commitAllowingStateLoss()

        //Lancement du dialogue de chargement
        val DialogBuilder= AlertDialog.Builder(this)
        val dialogView=layoutInflater.inflate(R.layout.loading_dialog,null)
        DialogBuilder.setView(dialogView)
        DialogBuilder.setCancelable(false)
        val Dialog = DialogBuilder.create()

        val model = ViewModelProvider(this).get(MyViewModel::class.java)
            model.lineWithStop(RouteType.TRAM, SelectedTramLine.toString()).observe(object : ObservableListener<Line> {
                override fun onLoading() {
                    //call once we started the network called. Indicate that the network call is in progress
                    Dialog.show()
                }

                override fun onSuccess(data: Line) {

                    //data retourne ici la liste des arrets avec leurs latitudes en longitudes
                    val Stops = data.stops
                    val StopsPins = ArrayList<Poi>()

                    //Comme on peut le voir dans com.shindra.arrakis.controls.MapFragment(), on crée un objet Poi, pour chaque arret, composé de l'image du curseur, de la couleur de la ligne, et des coordonnées de l'arret
                    Stops?.forEach {
                        var StopLatitude = it.position!!.latitude as Double
                        var StopLongitude = it.position!!.longitude as Double

                        val MapPoint = Poi(R.drawable.icon_maps_place_24px, GetLineColor(SelectedTramLine.toString()), StopLatitude, StopLongitude)

                        StopsPins.add(MapPoint)
                    }

                    //La fonction addPoi de com.shindra.arrakis.controls.MapFragment() attends une liste de Poi, on a juste besoin de lui passer et il s'occupe de l'affichage et du centrage de la carte
                    _MapFragment.AddPinsOnMap(StopsPins)

                    Dialog.dismiss()
                }

                override fun onError(throwable: Throwable) {
                    //call if the network call has responded with an error
                }
            })
        }

    fun GetLineColor(TramLine : String) : Int
    {
        return when (TramLine) {
            "A" -> R.color.LigneA
            "B" -> R.color.LigneB
            "C" -> R.color.LigneC
            "D" -> R.color.LigneD
            "E" -> R.color.LigneE
            "F" -> R.color.LigneF
            else -> {
                R.color.LigneA
            }
        }
    }
}


