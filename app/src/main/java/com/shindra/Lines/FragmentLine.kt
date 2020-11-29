package com.shindra.Lines

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shindra.LoadingDialog
import com.shindra.MyViewModel
import com.shindra.R
import com.shindra.Schedule.ScheduleActivity
import com.shindra.ScheduleClick
import com.shindra.arrakis.extension.toArrayList
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line
import java.util.ArrayList

class FragmentLine : Fragment(), ScheduleClick {

    val lines = ArrayList<Line>()
    private lateinit var viewOfLayout: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        viewOfLayout = inflater.inflate(R.layout.fragment_lines, container, false)
        return viewOfLayout
    }

    override fun onStart() {
        super.onStart()

        val loadingDialog = LoadingDialog(this.activity as Activity)

        val tramRecyclerList = viewOfLayout.findViewById<RecyclerView>(R.id.cardListLines)
        tramRecyclerList.layoutManager = LinearLayoutManager(this.context)
        tramRecyclerList.adapter = LinesRecyclerViewAdapter(lines, this)

        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.lines().observe(object : ObservableListener<ArrayList<Line>> {
            override fun onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
                loadingDialog.showLoadingDialog()
            }

            override fun onSuccess(data: ArrayList<Line>) {
                //call once the network call has responded with a success
                val lines = data.filter{it.routeType == RouteType.TRAM}.toArrayList()

                lines?.let {
                    (tramRecyclerList.adapter as LinesRecyclerViewAdapter).lines = it
                }
                (tramRecyclerList.adapter as LinesRecyclerViewAdapter).notifyDataSetChanged()

                loadingDialog.dismissLoadingDialog()
            }

            override fun onError(throwable: Throwable) {
                //call if the network call has responded with an error
            }
        })
    }

    override fun onScheduleClick(line: Line?) {
        val intent = Intent(this.activity, ScheduleActivity::class.java)
        intent.putExtra(getString(R.string.line_to_schedule_activity_intent_name), line?.name)
        startActivity(intent)
    }
}