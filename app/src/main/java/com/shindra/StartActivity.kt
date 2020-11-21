package com.shindra

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line
import io.reactivex.rxjava3.core.Scheduler
import java.util.*

class StartActivity : AppCompatActivity() {
    private var availableTramlines: ArrayList<Line>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val recyclerView = SetUpRecyclerView()
        val loadingDialog = LoadingDialog(this)


        val model = ViewModelProvider(this).get(MyViewModel::class.java) //ObservableListener<ArrayList<Line?>?>
        model.lines().observe(object : ObservableListener<ArrayList<Line>> {
            override fun onLoading() {
                loadingDialog.showDialog()
            }


            override fun onError(throwable: Throwable) {

            }

            override fun onSuccess(data: ArrayList<Line>) {
                loadingDialog.dismissDialog()

                if (data != null) {
                    for (l in data) {
                        if (l != null) {
                            if (l.routeType === RouteType.TRAM) availableTramlines!!.add(l)
                        }
                    }
                }

                //Fill the recycler view with received tramlines
                recyclerView.adapter!!.notifyDataSetChanged()
            }


        })
    }

    private fun SetUpRecyclerView(): RecyclerView {
        availableTramlines = ArrayList() //empty list to initialize recycler view
        val recyclerView = findViewById<RecyclerView>(R.id.tram_recyclerview)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = Tram_RecyclerViewAdapter(availableTramlines!!) { l: Line -> OnTramlineClick(l) }
        return recyclerView
    }

    fun OnTramlineClick(l: Line) {

        val intent = Intent(this, SchedulesActivity::class.java)
        startActivity(intent)
    }


}