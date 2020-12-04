package com.shindra

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line



class StartActivity : AppCompatActivity(), ViewHolderTram.RecyclerItemClick {

    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerViewTram)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)

        val ecranChargement = Chargement(this)

        val ligneDetram = ArrayList<Line>()
        recyclerView.adapter = RecyclerTramAdapter(ligneDetram, this)

        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.lines().observe(object : ObservableListener<ArrayList<Line>> {

            override fun onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
                ecranChargement.show()
            }

            override fun onSuccess(data: ArrayList<Line>) {
                //call once the network call has responded with a success
                ecranChargement.dismiss()
                Log.i("data", data.toString())

               // val trams = ArrayList<Line>()

                for (Ligne in data) {
                    if (Ligne.routeType === RouteType.TRAM) {
                        ligneDetram.add(Ligne)
                    }
                }
                Log.i("ligne", ligneDetram.toString())

                val adapter = RecyclerTramAdapter(ligneDetram, this@StartActivity)
                recyclerView.adapter = adapter
            }

            override fun onError(throwable: Throwable) {
                //call if the network call has responded with an error
                ecranChargement.dismiss()
            }
        })
    }

    override fun onHoraireClick(tram: Line) {
        val intent = Intent(this, HoraireActivity::class.java)
        intent.putExtra("nomligne", tram.name)
        startActivity(intent)
    }
}