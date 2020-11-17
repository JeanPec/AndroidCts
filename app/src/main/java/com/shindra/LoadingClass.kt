package com.shindra

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog


class LoadingClass(context: Activity) {

    val alertDialog: AlertDialog

    init{
        val builder = AlertDialog.Builder(context)
        builder.setView(R.layout.loading_layout)
        val alertDialogInit = builder.create()
        alertDialog = alertDialogInit
    }

    fun show(){
        alertDialog.show()
    }
    fun dismiss(){
        alertDialog.dismiss()
    }

}