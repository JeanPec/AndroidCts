package com.shindra.arrakis.controls

import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.ColorRes
import androidx.annotation.DrawableRes
import androidx.core.content.ContextCompat
import androidx.core.graphics.drawable.DrawableCompat
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import com.google.android.gms.maps.model.*
import com.shindra.arrakis.ArrakisApplication
import com.shindra.arrakis.R
import java.lang.Exception


abstract class MapFragment : Fragment() {

    protected var mapView: MapView? = null
    protected var gMapsView: GoogleMap? = null

    private val DEFAULT_PARIS_LOCATION = LatLng(48.8534, 2.3488)
    private val DEFAULT_ZOOM = 2f

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_gmaps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView = view.findViewById(R.id.mapView)
        mapView?.onCreate(savedInstanceState)
        mapView?.getMapAsync { gMaps ->
            gMapsView = gMaps
            gMaps.moveCamera(CameraUpdateFactory.newLatLngZoom(getDefaultPosition(), getDefaultZoom()))
        }
    }

    override fun onStart() {
        super.onStart()
        mapView?.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView?.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView?.onPause()
    }

    override fun onStop() {
        super.onStop()
        mapView?.onStop()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView?.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView?.onLowMemory()
    }

    protected fun getDefaultPosition() = DEFAULT_PARIS_LOCATION
    protected fun getDefaultZoom() = DEFAULT_ZOOM


    protected fun addPois(pois: ArrayList<Poi>) {
        gMapsView?.clear()

        pois.forEach {
            addPoiToMap(it)
        }
        centerOnMap(pois[0].latitude, pois[0].longitude, pois)
    }

    private fun addPoiToMap(poi: Poi) {
        val latlgt = LatLng(poi.latitude, poi.longitude)
        gMapsView?.addMarker(MarkerOptions().position(latlgt).icon(getMarkerIconFromDrawable(poi)))
    }

    private fun getMarkerIconFromDrawable(poi: Poi): BitmapDescriptor? {
        val unwrappedDrawable = ContextCompat.getDrawable(context
            ?: ArrakisApplication.arrakisContext, poi.imageRes)

        return BitmapDescriptorFactory.fromBitmap(unwrappedDrawable?.let {
            val wrappedDrawable = DrawableCompat.wrap(it)
            DrawableCompat.setTint(wrappedDrawable, ContextCompat.getColor(context
                ?: ArrakisApplication.arrakisContext, poi.imageTint))
            val canvas = Canvas()
            val bitmap = Bitmap.createBitmap(wrappedDrawable.intrinsicWidth, wrappedDrawable.intrinsicHeight, Bitmap.Config.ARGB_8888)
            canvas.setBitmap(bitmap)
            wrappedDrawable.setBounds(0, 0, wrappedDrawable.intrinsicWidth, wrappedDrawable.intrinsicHeight)
            wrappedDrawable.draw(canvas)
            bitmap
        })
    }

    private fun centerOnMap(lat: Double, long: Double, pois: ArrayList<Poi>) {
        var latSpan: Double = 0.0
        var longSpan: Double = 0.0

        pois.forEach {
            latSpan = Math.max(Math.abs(lat - it.latitude), latSpan)
            longSpan = Math.max(Math.abs(long - it.longitude), longSpan)
        }

        val southWest = LatLng(lat - latSpan, long - longSpan)
        val northWest = LatLng(lat + latSpan, long + longSpan)
        val bounds = LatLngBounds(southWest, northWest)
        val cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, context?.resources?.displayMetrics?.density?.toInt()
            ?: 0)

        try {
            gMapsView?.animateCamera(cameraUpdate)
        } catch (e: Exception) {

        }
    }


}


data class Poi(
    @DrawableRes val imageRes: Int,
    @ColorRes val imageTint: Int,
    val latitude: Double,
    val longitude: Double)



