package com.shindra.Utils

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.shindra.R

 class ProcessDialog()
{
    var dialog : AlertDialog? = null

     fun initialize (context : Context?)
    {
        val builder = AlertDialog.Builder(context)
        builder
                .setView(LayoutInflater.from(context).inflate(R.layout.loading_layout,null))
                .setCancelable(false)
        dialog = builder.create()
    }
    fun show()
    {
        dialog?.show()
    }
    fun dismiss()
    {
        dialog?.dismiss()
    }


}