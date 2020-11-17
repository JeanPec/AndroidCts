package com.shindra

import android.app.Activity
import android.app.AlertDialog

class LoadingDialog(val context: Activity) {

    private val alertDialog: AlertDialog

    init{
        val dialogBuilder = AlertDialog.Builder(context)
        val temp = dialogBuilder.create()
        alertDialog=temp

        val inflater = context.layoutInflater
        val dialogView = inflater.inflate(R.layout.load_popup, null)
    }

    fun showDialog(){
        alertDialog.show()
    }

    fun dismissDialog(){
        alertDialog.dismiss()
    }
}