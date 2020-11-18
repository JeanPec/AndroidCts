package com.shindra.lines

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shindra.utilities.LoadingDialog
import com.shindra.utilities.MyViewModel
import com.shindra.R
import com.shindra.schedules.ScheduleActivity
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line

class LineActivity : AppCompatActivity(), LineViewHolder.OnLineClickListener {
    private var lineList = ArrayList<Line>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Activity configuration
        setContentView(R.layout.line_activity)
        setTitle(R.string.line_activity_name)

        //RecyclerView configuration
        val lineRecyclerList = findViewById<RecyclerView>(R.id.line_list)
        lineRecyclerList.layoutManager = LinearLayoutManager(this)
        lineRecyclerList.adapter = LineAdapter(lineList, this)

        //LoadingDialog creation
        val loadingDialog = LoadingDialog(this)

        //network call configuration
        val model = ViewModelProvider(this).get(MyViewModel::class.java)

        //perform network call
        model.lines().observe(object : ObservableListener<ArrayList<Line>> {
            override fun onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
                //display LoadingDialog
                loadingDialog.showDialog()
            }

            override fun onSuccess(data: ArrayList<Line>) {
                //call once the network call has responded with a success
                //RouteType check
                for (line in data) {
                    if (line.routeType == RouteType.TRAM) {
                        lineList.add(line)
                    }
                }

                //adapter data change
                (lineRecyclerList.adapter as LineAdapter).lines = lineList
                (lineRecyclerList.adapter as LineAdapter).notifyDataSetChanged()

                //remove LoadingDialog
                loadingDialog.dismissDialog()
            }

            override fun onError(throwable: Throwable) {
                //call if the network call has responded with an error
                //display error message
                Toast.makeText(applicationContext, R.string.loading_text_error, Toast.LENGTH_LONG).show()

                //remove LoadingDialog
                loadingDialog.dismissDialog()
            }
        })
    }

    //callback function for button
    override fun onItemClick(line: Line) {
        //Intent creation and new Activity launch
        val intent = Intent(this, ScheduleActivity::class.java)
        intent.putExtra("lineName", line.name)
        startActivity(intent)
    }
}