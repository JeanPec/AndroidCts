package com.shindra

import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context

class loadingWindow(context: Context?) : Dialog(context!!) {
    var _activity: Activity? = null
    var _loadingWindow: AlertDialog? = null
    fun displayLoadingWindow() {
        val builder = AlertDialog.Builder(context)
        val inflater = layoutInflater
        builder.setView(inflater.inflate(R.layout.cardview_loading_window, null))
        builder.setCancelable(true)
        _loadingWindow = builder.create()
        _loadingWindow!!.show()
    }

    fun dismissLoadingWindow() {
        _loadingWindow!!.dismiss()
    }
}