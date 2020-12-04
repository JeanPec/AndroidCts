package com.shindra.Trams

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shindra.*
import com.shindra.Schedule.ScheduleActivity
import com.shindra.Utils.ProcessDialog
import com.shindra.arrakis.extension.toArrayList
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line


/**
 * A simple [Fragment] subclass.
 * Use the [TramFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TramFragment : Fragment() {

    private  var _tramList : ArrayList<Line>? = ArrayList()
    private var  recyclerView : RecyclerView? = null
    private var cardViewAdapter : CardViewAdapter? = null
    private var progressLayout : View? =  null
    private var processDialog  : ProcessDialog? = null

    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View?
    {
        // Inflate the layout for this fragment
       var view : View = inflater.inflate(R.layout.fragment_tram, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressLayout = view.findViewById(R.id.loading_layout)
        recyclerView = view.findViewById(R.id.recyclerViewTram)
        cardViewAdapter = CardViewAdapter(_tramList, object : RecyclerItemClick {
            override fun onScheduleClick(tram: Line) {
                val intent: Intent = Intent(context, ScheduleActivity::class.java)
                intent.putExtra("line", tram.name)
                startActivity(intent)
            }
        })
        recyclerView?.layoutManager = LinearLayoutManager(activity)
        recyclerView?.adapter = cardViewAdapter
        progressLayout?.visibility = View.VISIBLE
    }

    override fun onStart() {
        super.onStart()
        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.lines().observe(object : ObservableListener<ArrayList<Line>> {
            override fun onLoading() {
                //call once we started the network called. Indicate that the network call is in progress

                processDialog = ProcessDialog()
                processDialog?.initialize(context)
                processDialog?.show()
            }


            override fun onSuccess(data: ArrayList<Line>) {
                //call once the network call has responded with a success
                _tramList = data.filter{ it.routeType == RouteType.TRAM}.toArrayList()
                cardViewAdapter?.setTram(_tramList)
                processDialog?.dismiss()
                cardViewAdapter?.notifyDataSetChanged()
                //progressLayout?.visibility = View.GONE

            }

            override fun onError(throwable: Throwable) {
                //call if the network call has responded with an error

            }
        })
    }
}