package com.shindra.Map

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.shindra.Dialogs.LoadingDialog
import com.shindra.MyViewModel
import com.shindra.R
import com.shindra.arrakis.controls.Poi
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line

class MapFragment(_Context: Context, _LineName: String?) : com.shindra.arrakis.controls.MapFragment() {

    public val SelectedTramLine = _LineName
    private val LayoutContext = _Context

    override fun onStart() {
        super.onStart()

        //Creation du dialogue de chargement
        val LoadingDialog = LoadingDialog()
        LoadingDialog.CreateDialog(LayoutContext)

        val model = ViewModelProvider(this).get(MyViewModel::class.java)

        model.lineWithStop(RouteType.TRAM, SelectedTramLine.toString()).observe(object : ObservableListener<Line> {
            override fun onLoading() {
                //call once we started the network called. Indicate that the network call is in progress

                //Lancement du dialogue de chargement
                LoadingDialog.ShowDialog()
            }

            override fun onSuccess(data: Line) {
                //data retourne ici la liste des arrets avec leurs latitudes en longitudes
                val Stops = data.stops
                val StopsPins = ArrayList<Poi>()

                //Comme on peut le voir dans com.shindra.arrakis.controls.MapFragment(), on crée un objet Poi, pour chaque arret, composé de l'image du curseur, de la couleur de la ligne, et des coordonnées de l'arret
                Stops?.forEach {
                    var StopLatitude = it.position?.latitude as Double
                    var StopLongitude = it.position?.longitude as Double

                    val MapPoint = Poi(R.drawable.icon_maps_place_24px, GetLineColor(SelectedTramLine), StopLatitude, StopLongitude)

                    StopsPins.add(MapPoint)
                }

                //La fonction addPoi de com.shindra.arrakis.controls.MapFragment() attends une liste de Poi, on a juste besoin de lui passer et il s'occupe de l'affichage et du centrage de la carte
                addPois(StopsPins)

                LoadingDialog.CloseDialog()
            }

            override fun onError(throwable: Throwable) {
                //call if the network call has responded with an error

                //Fermer le dialogue de chargement en cas d'erreur
                LoadingDialog.CloseDialog()
            }
        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        //Inflate the layout for this fragment
        val FragmentView = inflater.inflate(R.layout.fragment_gmaps, container, false);
        return FragmentView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    private fun GetLineColor(TramLine: String?) : Int
    {
        return when (TramLine) {
            "A" -> R.color.LigneA
            "B" -> R.color.LigneB
            "C" -> R.color.LigneC
            "D" -> R.color.LigneD
            "E" -> R.color.LigneE
            "F" -> R.color.LigneF
            else -> {
                R.color.black
            }
        }
    }
}