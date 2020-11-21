package com.shindra.Lignes

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.shindra.Dialogs.LoadingDialog
import com.shindra.MyViewModel
import com.shindra.R
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line

class TramsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Changement du titre de l'activité
        setTitle(getString(R.string.startup_header))

        //Creation du dialogue de chargement
        val LoadingDialog = LoadingDialog()
        LoadingDialog.CreateDialog(this)

        val model = ViewModelProvider(this).get(MyViewModel::class.java)

        model.lines().observe(object : ObservableListener<ArrayList<Line>> {
            override fun onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
                LoadingDialog.ShowDialog()
            }

            override fun onSuccess(data: ArrayList<Line>) {
                //call once the network call has responded with a succes
                val DataList = data.filter { it.routeType == RouteType.TRAM }
                var TramLinesNames : List<String>

                TramLinesNames = DataList.map{it.name}

                //Appeler et afficher le fragment des lignes de trams
                var TramsFragment = TramsFragment()
                TramsFragment.TramLinesNames = TramLinesNames as MutableList<String>
                supportFragmentManager.beginTransaction()
                        .replace(R.id.MainFrameLayout, TramsFragment)
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