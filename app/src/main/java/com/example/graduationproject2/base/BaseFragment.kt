package com.example.graduationproject2.base

import android.app.ProgressDialog
import androidx.fragment.app.Fragment

open class BaseFragment: Fragment() {
    var progressDialog: ProgressDialog? = null
    fun showLoading() {
        progressDialog = ProgressDialog(requireContext())
        progressDialog!!.setTitle("loading")
        progressDialog!!.setCancelable(false)
        progressDialog!!.show()
    }
    fun hideLoading() {
        progressDialog!!.hide()
    }
}