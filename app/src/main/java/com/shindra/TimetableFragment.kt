package com.shindra

import android.content.Intent
import android.os.Bundle
import android.util.Log
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

class TimetableFragment : Fragment() {
    private val logTag = "TramsSchedulesFragment"
    companion object{
        const val LINENAME_MESSAGE = "com.souf.ctsfip3a.LINENAME"
    }
    private var recyclerView: RecyclerView? = null
    var associatedLinename: String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(logTag, "Inflating fragment...")

        val v = inflater.inflate(R.layout.fragment_timetable, container, false)

        v?.findViewById<Button>(R.id.showGPSButton)?.setOnClickListener { onShowGPSButtonClicked() }

        return v
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(logTag, "onCreate")
    }


    fun requestTimetable(lineName: String) {
        Log.i(logTag, "RequestTimetable")

        associatedLinename = lineName

        val loadingDialog = LoadingDialog(requireActivity())

        //Call api for available lines
        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.lineWithEstimatedTimeTable(RouteType.TRAM, lineName, 0).observe(object :
            ObservableListener<Line> {
            override fun onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
                Log.i(logTag, "Waiting for answer...")
                loadingDialog.showLoadingScreen()
            }

            override fun onSuccess(data: Line) {
                //call once the network call has responded with a success
                Log.i(logTag, "Received data from network :: " + data.name)
                loadingDialog.hideLoadingScreen()

                setUpRecyclerView(data)
            }

            override fun onError(throwable: Throwable) {
                //call if the network call has responded with an error
                Log.e(logTag, "API error")
            }
        })
    }

    fun onShowGPSButtonClicked(){
        Log.i(logTag, "button clicked")

        val intent = Intent(this.activity, StopsMapActivity::class.java).apply {
            putExtra(LINENAME_MESSAGE, associatedLinename)
        }
        startActivity(intent)
    }

    private fun setUpRecyclerView(lineWithTimetable: Line): RecyclerView? {
        Log.i(logTag, "setUpRecyclerView")

        recyclerView = view?.findViewById(R.id.rvTimetable)
        recyclerView?.layoutManager = LinearLayoutManager(context)
        recyclerView?.adapter = TimetableRecyclerViewAdaptater(lineWithTimetable, requireContext())

        return recyclerView
    }
}