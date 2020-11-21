package com.shindra.Stop

import android.app.Activity
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
import com.shindra.Map.MapActivity
import com.shindra.Misc.ErrorDialog
import com.shindra.Misc.LoadingDialog
import com.shindra.Misc.MyViewModel
import com.shindra.R
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line
import com.shindra.ctslibrary.bo.Stop
import java.util.*
import kotlin.collections.ArrayList

class StopFragment() : Fragment()
{
    private var stops: RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.fragment_stop, container, false)

        val lineName = arguments?.getString("lineName")

        stops = view.findViewById(R.id.stops)
        stops?.layoutManager = LinearLayoutManager(context)
        stops?.adapter = StopAdapter(ArrayList(), lineName)

        var seeOnMapButton : Button = view.findViewById(R.id.seeOnMapButton)
        seeOnMapButton.setOnClickListener(View.OnClickListener {
            val intent = Intent(activity, MapActivity::class.java)
            intent.putExtra("lineName", lineName)
            startActivity(intent)
        })

        val loadingDialog = LoadingDialog(activity as Activity)
        val errorDialog = ErrorDialog(activity as Activity)

        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.lineWithEstimatedTimeTable(RouteType.TRAM, lineName!!, 0).observe(object : ObservableListener<Line> {

            override fun onLoading()
            {
                //call once we started the network called. Indicate that the network call is in progress
                //Show the loading dialog
                loadingDialog.show()
            }

            override fun onSuccess(data: Line) {
                //call once the network call has responded with a success
                //Dismiss the loading dialog
                loadingDialog.dismiss()

                //Only keep stops with a no-null departure time
                val stopWithDeparture = data.stops!!.filter { ref -> ref.estimatedDepartureTime != null }

                //Update the recycler view through the adapter
                updateWidgets(ArrayList(stopWithDeparture))
            }

            override fun onError(throwable: Throwable)
            {
                //call if the network call has responded with an error
                //Dismiss the loading dialog and show the error dialog
                loadingDialog.dismiss()
                errorDialog.show()
            }
        })

        return view
    }

    fun updateWidgets(data: ArrayList<Stop>)
    {
        (stops?.adapter as? StopAdapter?)?.stops = data
        stops?.adapter?.notifyDataSetChanged()
    }

    companion object
    {
        fun newInstance(lineName : String?) : StopFragment
        {
            val fragment = StopFragment()
            val args = Bundle()
            args.putString("lineName", lineName)
            fragment.arguments = args
            return  fragment
        }
    }
}