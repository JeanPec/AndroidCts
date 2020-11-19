package com.shindra.map

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.shindra.utilities.MyViewModel
import com.shindra.R
import com.shindra.arrakis.observable.ObservableListener
import com.shindra.arrakis.observable.observe
import com.shindra.ctslibrary.apibo.RouteType
import com.shindra.ctslibrary.bo.Line
import com.shindra.utilities.LoadingDialog

class MapLineActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)

        //Activity configuration
        setContentView(R.layout.map_line_activity)

        //data recovery
        val lineName = intent.getStringExtra("lineName")
        title = getLineName(lineName!!)

        //Fragment configuration
        val fragment = MapLineFragment()
        supportFragmentManager.beginTransaction().add(R.id.fragment_line_map,fragment).commit()

        //LoadingDialog creation
        val loadingDialog = LoadingDialog(this)

        //network call configuration
        val model = ViewModelProvider(this).get(MyViewModel::class.java)

        //perform network call
        model.lineWithStop(RouteType.TRAM,lineName).observe(object : ObservableListener<Line> {
            override fun onLoading() {
                //call once we started the network called. Indicate that the network call is in progress
                //display LoadingDialog
                loadingDialog.showDialog()
            }

            override fun onSuccess(data: Line) {
                //call once the network call has responded with a success
                //MapFragment data change
                fragment.addPointOnMap(data)

                //remove LoadingDialog
                loadingDialog.dismissDialog()
            }

            override fun onError(throwable: Throwable) {
                //call if the network call has responded with an error
                //display error message
                Toast.makeText(applicationContext, R.string.loading_text_error, Toast.LENGTH_LONG).show()

                //remove LoadingDialog
                loadingDialog.dismissDialog()
            }
        })
    }

    private fun getLineName(line: String) : String {
        return when (line){
            getString(R.string.line_a) -> getString(R.string.line_a_display)
            getString(R.string.line_b) -> getString(R.string.line_b_display)
            getString(R.string.line_c) -> getString(R.string.line_c_display)
            getString(R.string.line_d) -> getString(R.string.line_d_display)
            getString(R.string.line_e) -> getString(R.string.line_e_display)
            getString(R.string.line_f) -> getString(R.string.line_f_display)
            else -> getString(R.string.line_error_display)
        }
    }
}