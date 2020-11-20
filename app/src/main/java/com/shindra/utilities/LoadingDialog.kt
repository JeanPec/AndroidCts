package com.shindra.utilities

import android.app.Activity
import android.app.AlertDialog
import com.shindra.R

class LoadingDialog(context: Activity): AlertDialog(context) {

    init{
        this.setView(context.layoutInflater.inflate(R.layout.loading_dialog, listView))
    }
}