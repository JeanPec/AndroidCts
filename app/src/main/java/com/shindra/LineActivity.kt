package com.shindra

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line
import java.util.*

class LineActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.line_activity)
        setTitle(R.string.line_activity_name)

        //temporary
        val tramList = findViewById<RecyclerView>(R.id.line_list)
        tramList.layoutManager = LinearLayoutManager(this)
        tramList.adapter = LineAdapter(listOfTram)

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

    private val listOfTram: ArrayList<Line>
        get() {
            val tramArray = ArrayList<Line>()
            tramArray.add(Line("Ligne A", RouteType.TRAM, null))
            tramArray.add(Line("Ligne B", RouteType.TRAM, null))
            tramArray.add(Line("Ligne C", RouteType.TRAM, null))
            tramArray.add(Line("Ligne D", RouteType.TRAM, null))
            tramArray.add(Line("Ligne E", RouteType.TRAM, null))
            tramArray.add(Line("Ligne F", RouteType.TRAM, null))
            return tramArray
        }
}