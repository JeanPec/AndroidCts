package com.shindra

import com.shindra.arrakis.observable.observe
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line
import java.util.ArrayList

class StartActivity : AppCompatActivity(), RecyclerItemClick {
    val lines = ArrayList<Line>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_lines)
        title = "Nos Trams"

        val tramRecyclerList = findViewById<RecyclerView>(R.id.cardListLines)
        tramRecyclerList.layoutManager = LinearLayoutManager(this)
        tramRecyclerList.adapter = LinesRecyclerViewAdapter(lines, this)

        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.lines().observe(object : ObservableListener<ArrayList<Line>> {
            override fun onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
            }

            override fun onSuccess(data: ArrayList<Line>) {
                //call once the network call has responded with a success
                for (line in data) {
                    if (line.routeType === RouteType.TRAM) {
                        lines.add(line)
                    }
                }

                (tramRecyclerList.adapter as LinesRecyclerViewAdapter).lines = lines
                (tramRecyclerList.adapter as LinesRecyclerViewAdapter).notifyDataSetChanged()

            }

            override fun onError(throwable: Throwable) {
                //call if the network call has responded with an error
            }
        })
    }

    override fun onScheduleClick(line: Line?) {
        val intent = Intent(this, ScheduleActivity::class.java)
        println(line?.name)
        intent.putExtra("LINE", line?.name)
        startActivity(intent)
    }
}