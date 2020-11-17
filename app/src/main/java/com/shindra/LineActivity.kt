package com.shindra

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line

class LineActivity : AppCompatActivity(), LineViewHolder.OnLineClickListener {

    private var tramList = ArrayList<Line>()

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.line_activity)
        setTitle(R.string.line_activity_name)

        val tramRecyclerList = findViewById<RecyclerView>(R.id.line_list)
        tramRecyclerList.layoutManager = LinearLayoutManager(this)
        tramRecyclerList.adapter = LineAdapter(tramList, this)

        val loadingDialog = LoadingDialog(this)

        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.lines().observe(object : ObservableListener<ArrayList<Line>> {

            override fun onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
                loadingDialog.showDialog()
            }

            override fun onSuccess(data: ArrayList<Line>) {
                //call once the network call has responded with a success
                for (line in data) {
                    if (line.routeType == RouteType.TRAM) {
                        tramList.add(line)
                    }
                }
                (tramRecyclerList.adapter as LineAdapter).lines = tramList
                (tramRecyclerList.adapter as LineAdapter).notifyDataSetChanged()
                loadingDialog.dismissDialog()
            }

            override fun onError(throwable: Throwable) {
                //call if the network call has responded with an error
            }
        })
    }

    override fun onItemClick(line: Line) {
        val intent = Intent(this, ScheduleActivity::class.java)
        intent.putExtra("lineName", line.name)
        startActivity(intent)
    }
}