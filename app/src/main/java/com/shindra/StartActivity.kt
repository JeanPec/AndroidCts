package com.shindra

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.bo.Line
import java.util.*

class StartActivity : AppCompatActivity() {


    @SuppressLint("WrongConstant")
    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerViewTram)

        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayout.VERTICAL, false)

        val trams = ArrayList<RecyclerTram>()

        trams.add(RecyclerTram(R.drawable.ic_tram_a))
        trams.add(RecyclerTram(R.drawable.ic_tram_b))
        trams.add(RecyclerTram(R.drawable.ic_tram_c))
        trams.add(RecyclerTram(R.drawable.ic_tram_d))
        trams.add(RecyclerTram(R.drawable.ic_tram_e))
        trams.add(RecyclerTram(R.drawable.ic_tram_f))

        val adapter = RecyclerTramAdapter(trams)
        recyclerView.adapter = adapter


        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.lines().observe(object : ObservableListener<ArrayList<Line>> {
            override fun onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
            }

            override fun onSuccess(data: ArrayList<Line>) {
                //call once the network call has responded with a success
            }

            override fun onError(throwable: Throwable) {
                //call if the network call has responded with an error
            }
        })
    }
}