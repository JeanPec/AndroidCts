package com.shindra



//import com.shindra.MyViewModel.lines
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shindra.Time.TimeActivity
import com.shindra.arrakis.extension.toArrayList
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line
import java.util.*
import kotlin.collections.ArrayList


class StartActivity : AppCompatActivity(),TrainAdapter.TrainViewHolder.RecyclerLineClick {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = "Nos trams"
        val listInit = ArrayList<Line>()

        val lineRecyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        lineRecyclerView.adapter = TrainAdapter(listInit, this)
        lineRecyclerView.layoutManager = LinearLayoutManager(this)
        lineRecyclerView.setHasFixedSize(true)
        val dialog = LoadingClass(this)
        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.lines().observe(object : ObservableListener<ArrayList<Line>> {
            override fun onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
                dialog.show()
            }

            override fun onSuccess(data: ArrayList<Line>) {
                dialog.dismiss()
                //call once the network call has responded with a success
                (lineRecyclerView.adapter as TrainAdapter).lineList = data.filter{it.routeType == RouteType.TRAM }.toArrayList()!!
                (lineRecyclerView.adapter as TrainAdapter).notifyDataSetChanged()
            }

            override fun onError(throwable: Throwable) {
                //call if the network call has responded with an error
            }
        })
    }

    override fun onLineClick(line: Line) {
        val intent = Intent(this, TimeActivity::class.java)
        intent.putExtra("name", line.name)
        startActivity(intent)
    }

}