package com.example.graduationproject2.ui.login.signUp

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.graduationproject2.R
import com.example.graduationproject2.base.Base
import com.example.graduationproject2.databinding.ActivityRegisterBinding

class RegisterActivity : Base<RegisterViewModel, ActivityRegisterBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_register)
        viewModel = ViewModelProvider(this).get(RegisterViewModel::class.java)
        bind.vm = viewModel
        observation()
    }
    fun observation(){
        viewModel.isLoadingLiveData.observe(this){
            if (it){
                showLoading()
            }else{
                hideLoading()
            }
        }
        viewModel.dialogMessageLiveData.observe(this){
            if (it=="done"){
                finish()
            }else{
                showMessage(title = it, posButtonTitle = "ok")
            }
        }
    }
}