package com.shindra.LineTram

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shindra.Utilities.ErrorDialog
import com.shindra.Utilities.LoadingDialog
import com.shindra.MyViewModel
import com.shindra.R
import com.shindra.Schedule.ScheduleActivity
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line
import java.util.*

class LineTramActivity : AppCompatActivity(), LineTramViewHolder.OnClickListener {

    private  var listOfLines: List<Line> = mutableListOf()
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.line_tram_activity)
        setTitle(R.string.tram_title)
        // Initializing the components dialog
        val loadingDialog = LoadingDialog(this)
        val errorDialog = ErrorDialog(this)

        // Setting up the View
        recyclerView = findViewById(R.id.LineTramRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = LineTramAdapter(listOfLines, this)

        // Calling the API
        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.lines().observe(object : ObservableListener<ArrayList<Line>> {
            override fun onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
                loadingDialog.show()
            }

            override fun onSuccess(data: ArrayList<Line>) {
                //call once the network call has responded with a success
                loadingDialog.dismiss()
                // Updating the view with the new data
                listOfLines = data.filter{ it.routeType == RouteType.TRAM }
                recyclerView.adapter = LineTramAdapter(listOfLines, this@LineTramActivity)
            }

            override fun onError(throwable: Throwable) {
                //call if the network call has responded with an error
                loadingDialog.dismiss()
                errorDialog.show()
            }
        })

    }

    // Handling button click
    override fun onClick(lineTram: Line) {
        val intent = Intent(this, ScheduleActivity::class.java)
        intent.putExtra("lineTramName", lineTram.name)
        // Redirecting to Schedule
        startActivity(intent)
    }

}