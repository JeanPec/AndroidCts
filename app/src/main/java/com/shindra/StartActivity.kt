package com.shindra

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shindra.StartActivityAdapter.RecyclerItemClick
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line
import java.util.*

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar!!.title = getString(R.string.tramLinesActivityName)
        val window = loadingWindow(this)

        //recycler View configuration
        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view_tram)
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager

        //network request
        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.lines().observe(object : ObservableListener<ArrayList<Line>> {
            override fun onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
                window.displayLoadingWindow()
            }

            override fun onSuccess(data: ArrayList<Line>) {
                //call once the network call has responded with a success
                val listOfLines = ArrayList<Line>()
                window.dismissLoadingWindow()

                //transfer each tram lines from data (containing all lines like bus, tram, etc..) into listOfLines
                for (lineData in data) {
                    if (lineData.routeType === RouteType.TRAM) {
                        listOfLines.add(lineData)
                    }
                }

                //creation of the adapter
                val adapter: RecyclerView.Adapter<*> = StartActivityAdapter(listOfLines, object : RecyclerItemClick {
                    override fun onDiaryButtonClick(tram: Line?) {
                        //Create an Intent to pass from StartActivity to stationActivity
                        val intent = Intent(this@StartActivity, stationActivity::class.java)
                        intent.putExtra("TRAM_LINE", tram?.name)
                        startActivity(intent)
                    }
                })

                //Set the adapter in the recycler view
                recyclerView.adapter = adapter
            }

            override fun onError(throwable: Throwable) {
                //call if the network call has responded with an error
            }
        })
    }
}