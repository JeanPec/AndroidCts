package com.shindra.Line;

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.shindra.Misc.ErrorDialog
import com.shindra.Misc.LoadingDialog
import com.shindra.Misc.MyViewModel
import com.shindra.R
import com.shindra.Stop.StopActivity
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line
import java.util.*

class LineFragment : Fragment()
{
    private var lines : RecyclerView? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View?
    {
        val view = inflater.inflate(R.layout.fragment_line, container, false)

        lines = view.findViewById(R.id.lines)
        lines?.layoutManager = LinearLayoutManager(context)
        lines?.adapter = LineAdapter(ArrayList(), object : ILineClickable
        {
            override fun onLineClick(line: Line)
            {
                val intent = Intent(activity, StopActivity::class.java)
                intent.putExtra("lineName", line.name)
                startActivity(intent)
            }
        })

        val loadingDialog = LoadingDialog(activity)
        val errorDialog = ErrorDialog(activity)

        val model = ViewModelProvider(this).get(MyViewModel::class.java)
        model.lines().observe(object : ObservableListener<ArrayList<Line>> {

            override fun onLoading()
            {
                //call once we started the network called. Indicate that the network call is in progress
                //Show the loading dialog
                loadingDialog.show()
            }

            override fun onSuccess(data: ArrayList<Line>)
            {
                //call once the network call has responded with a success
                //Dismiss the loading dialog
                loadingDialog.dismiss()

                //Only keep Tram lines
                val tramLines = ArrayList<Line>()
                for (item in data)
                    if (item.routeType === RouteType.TRAM)
                        tramLines.add(item)

                //Update the recycler view through the adapter
                updateWidgets(tramLines)
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

    fun updateWidgets(tramLines : ArrayList<Line>)
    {
        (lines?.adapter as LineAdapter).lines = tramLines
        lines?.adapter?.notifyDataSetChanged()
    }
}
