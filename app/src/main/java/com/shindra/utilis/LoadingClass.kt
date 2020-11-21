package com.shindra.utilis

import android.app.Activity
import androidx.appcompat.app.AlertDialog
import com.shindra.R


class LoadingClass(context: Activity) : AlertDialog(context) {

    init{
        this.setView(context.layoutInflater.inflate(R.layout.loading_layout, listView))
    }

}