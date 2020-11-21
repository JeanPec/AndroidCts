package com.shindra.tram



//import com.shindra.utilis.MyViewModel.lines
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shindra.utilis.LoadingClass
import com.shindra.utilis.MyViewModel
import com.shindra.R
import com.shindra.time.TimeActivity
import com.shindra.arrakis.extension.toArrayList
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line
import kotlin.collections.ArrayList


class StartActivity : AppCompatActivity(), TramAdapter.TramViewHolder.RecyclerLineClick {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        title = getString(R.string.tram)
        val listInit = ArrayList<Line>()

        val lineRecyclerView = findViewById<RecyclerView>(R.id.recycler_view)
        lineRecyclerView.adapter = TramAdapter(listInit, this)
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
                (lineRecyclerView.adapter as TramAdapter).lineList = data.filter{it.routeType == RouteType.TRAM }.toArrayList()!!
                (lineRecyclerView.adapter as TramAdapter).notifyDataSetChanged()
            }

            override fun onError(throwable: Throwable) {
                //call if the network call has responded with an error
            }
        })
    }

    override fun onLineClick(line: Line) {
        //val intent = TimeActivity.newInstance(this,line.name)
        val intent = Intent(this, TimeActivity ::class.java)
        intent.putExtra("name", line.name)
        startActivity(intent)
    }

}