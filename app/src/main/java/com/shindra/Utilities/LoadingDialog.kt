package com.shindra.Utilities

import android.app.Activity
import android.app.AlertDialog
import com.shindra.R

class LoadingDialog(activity: Activity): AlertDialog(activity) {
    init {
        this.setView(activity.layoutInflater.inflate(R.layout.loading, listView))
        this.setCancelable(false)
        this.create()
    }
}