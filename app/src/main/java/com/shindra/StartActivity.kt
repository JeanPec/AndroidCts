package com.shindra

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Changement du titre de l'activité
        setTitle("Nos trams")

        //Lancement du dialogue de chargement
        val DialogBuilder=AlertDialog.Builder(this)
        val dialogView=layoutInflater.inflate(R.layout.loading_dialog,null)
        DialogBuilder.setView(dialogView)
        DialogBuilder.setCancelable(false)
        val Dialog = DialogBuilder.create()
        Dialog.show()


        val model = ViewModelProvider(this).get(MyViewModel::class.java)

        model.lines().observe(object : ObservableListener<ArrayList<Line>> {
            override fun onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
            }

            override fun onSuccess(data: ArrayList<Line>) {
                //call once the network call has responded with a success
                Dialog.dismiss()
                val DataList = model.lineWithStop(RouteType.TRAM, "A" )
                val test=1
            }

            override fun onError(throwable: Throwable) {
                //call if the network call has responded with an error
            }
        })
    }
}