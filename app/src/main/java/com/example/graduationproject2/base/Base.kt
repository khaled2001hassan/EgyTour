package com.example.graduationproject2.base

import android.app.AlertDialog
import android.app.ProgressDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

open class Base<v : ViewModel, b : ViewDataBinding> : AppCompatActivity() {
    lateinit var bind: b
    lateinit var viewModel: v
    var progressDialog: ProgressDialog? = null
    fun showLoading() {
        progressDialog = ProgressDialog(this)
        progressDialog!!.setTitle("loading")
        progressDialog!!.setCancelable(false)
        progressDialog!!.show()
    }
    fun hideLoading() {
        progressDialog!!.hide()
    }
    fun showMessage(
        title: String = "",
        message: String = "",
        posButtonTitle: String? = null,
        posButtonAction: DialogInterface.OnClickListener? = null,
        negButtonTitle: String? = null,
        negButtonAction: DialogInterface.OnClickListener? = null
    ) {
        val builder=AlertDialog.Builder(this)
        val defaultAction=object :DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, p1: Int) {
                dialog!!.dismiss()
            }

        }
        builder.setTitle(title)
        builder.setMessage(message)
        if (posButtonTitle!=null){
            builder.setPositiveButton(
                posButtonTitle,posButtonAction?:defaultAction
            )
        }
        if (negButtonTitle!=null){
            builder.setNegativeButton(
                negButtonTitle,negButtonAction?:defaultAction
            )
        }
        builder.show()

    }
}