package com.shindra

import android.app.Activity
import android.app.AlertDialog
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class ErrorDialog (context : Activity) : AppCompatActivity() {

    private val errorDialogView = View.inflate(context, R.layout.error_dialog, null)
    private val errorBuilder = AlertDialog.Builder(context).setView(errorDialogView)
    private val errorDialog = errorBuilder.create()

    fun showErrorDialog() {
        errorDialog.show()
    }

    fun dismissErrorDialog() {
        errorDialog.dismiss()
    }

}