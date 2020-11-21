package com.shindra

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
import java.nio.file.Files.lines


class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewTram)
        recyclerView.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val trams = ArrayList<Line>()
        val adapter = RecyclerTramAdapter(trams)
        recyclerView.adapter = adapter

        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.lines().observe(object : ObservableListener<ArrayList<Line>> {

            override fun onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
            }

            override fun onSuccess(data: ArrayList<Line>) {
                //call once the network call has responded with a success

                Log.i("data", data.toString())

                val trams = ArrayList<Line>()

                for (Ligne in data) {
                    if (Ligne.routeType === RouteType.TRAM) {
                        trams.add(Ligne)
                    }
                }
                Log.i("ligne", trams.toString())

                val adapter = RecyclerTramAdapter(trams)
                recyclerView.adapter = adapter

            }

            override fun onError(throwable: Throwable) {
                //call if the network call has responded with an error
            }
        })

    }
}