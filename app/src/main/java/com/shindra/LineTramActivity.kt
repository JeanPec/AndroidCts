package com.shindra

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line
import java.util.*

class LineTramActivity : AppCompatActivity(), LineTramViewHolder.OnClickListener {

    private  var listOfLines: MutableList<Line> = mutableListOf()
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.line_tram_activity)
        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        val context = this

        recyclerView = findViewById(R.id.LineTramRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = LineTramAdapter(listOfLines, this)

        model.lines().observe(object : ObservableListener<ArrayList<Line>> {
            override fun onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
            }

            override fun onSuccess(data: ArrayList<Line>) {
                //call once the network call has responded with a success

                for(item: Line in data) {
                    if(item.routeType == RouteType.TRAM)
                        listOfLines.add(item)
                }

                recyclerView.adapter = LineTramAdapter(listOfLines, context)
            }

            override fun onError(throwable: Throwable) {
                //call if the network call has responded with an error
            }
        })

    }

    override fun onClick(lineTram: Line) {
        val intent = Intent(this, ScheduleActivity::class.java)
        intent.putExtra("lineTramName", lineTram.name)
        startActivity(intent)
    }

}