package com.shindra

import android.app.Activity
import android.app.AlertDialog
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class LoadingDialog(context : Activity) : AppCompatActivity() {

    private val loadingDialogView = View.inflate(context, R.layout.loading_dialog,null)
    private val dialogBuilder = AlertDialog.Builder(context).setView(loadingDialogView)
    private val loadingDialog = dialogBuilder.create()

    fun showLoadingDialog() {
        loadingDialog.show()
    }

    fun dismissLoadingDialog() {
        loadingDialog.dismiss()
    }

}