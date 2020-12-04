package com.shindra

import android.app.Activity
import android.app.AlertDialog

class Chargement(activity: Activity): AlertDialog(activity) {
    init{
        val inflater = activity.layoutInflater
        this.setView(inflater.inflate(R.layout.chargement, null))
        this.setCancelable(false)
        this.create()
    }

}