package com.shindra

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line

class stationFragment : Fragment() {
    var recycler: RecyclerView? = null
    var button: Button? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val v = inflater.inflate(R.layout.fragment_station, container, false)
        recycler = v.findViewById(R.id.recycler_view_station)
        button = v.findViewById(R.id.buttonToMapActivity)
        val recyclerV = recycler
        if (recyclerV != null) {
            recyclerV.setLayoutManager(LinearLayoutManager(context))
            recyclerV.setHasFixedSize(true)
        }
        return v
    }

    override fun onStart() {
        super.onStart()
        val window = loadingWindow(context)
        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.lineWithEstimatedTimeTable(RouteType.TRAM, arguments?.getString("lineName")!!, 0)
            .observe(object : ObservableListener<Line> {
                override fun onError(throwable: Throwable) {}
                override fun onSuccess(data: Line) {
                    window.dismissLoadingWindow()
                    val _adapter: RecyclerView.Adapter<*> = stationAdapter(data)
                    recycler!!.adapter = _adapter
                    button!!.setOnClickListener {
                        val intent = Intent(activity, mapActivity::class.java)
                        intent.putExtra("LINE_NAME", data.name)
                        startActivity(intent)
                    }
                }

                override fun onLoading() {
                    window.displayLoadingWindow()
                }
            })
    }
}