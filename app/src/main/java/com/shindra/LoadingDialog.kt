package com.shindra

import android.app.Activity
import android.app.AlertDialog

class LoadingDialog(activity: Activity){
    private val loadingDialog: AlertDialog

    init {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.loading_dialog, null))
        builder.setCancelable(false)
        loadingDialog = builder.create()
    }

    fun showLoadingScreen() {
        loadingDialog.show()
    }

    fun hideLoadingScreen() {
        loadingDialog.dismiss()
    }
}