package com.shindra

import android.app.Activity
import android.app.AlertDialog
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity

class LoadingDialog(context : Activity) : AppCompatActivity() {


    private val loadingDialogView = LayoutInflater.from(context).inflate(R.layout.loading_dialog,null)
    private val dialogBuilder = AlertDialog.Builder(context).setView(loadingDialogView)
    private val loadingDialog = dialogBuilder.create()

    public fun showLoadingDialog() {
        loadingDialog.show()
    }

    public fun dismissLoadingDialog() {
        loadingDialog.dismiss()
    }

}