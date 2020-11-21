package com.shindra

import android.app.Activity
import android.app.AlertDialog
import android.util.Log

class LoadingDialog(activity: Activity): AlertDialog(activity) {
    init {
        this.setView(activity.layoutInflater.inflate(R.layout.loading, listView))
        this.setCancelable(false)
        this.create()
    }
}