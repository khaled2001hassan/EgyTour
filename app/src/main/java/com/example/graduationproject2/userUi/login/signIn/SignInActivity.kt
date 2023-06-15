package com.example.graduationproject2.userUi.login.signIn

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.graduationproject2.R
import com.example.graduationproject2.adminUI.home.AdminHomeActivity
import com.example.graduationproject2.base.Base
import com.example.graduationproject2.databinding.ActivitySignInBinding
import com.example.graduationproject2.userUi.home.HomeActivity
import com.example.graduationproject2.userUi.login.signUp.RegisterActivity

class SignInActivity  : Base<SignInViewMoodel, ActivitySignInBinding>(),SigninNavigator{
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        bind = DataBindingUtil.setContentView(this@SignInActivity , R.layout.activity_sign_in)
        viewModel= ViewModelProvider(this).get(SignInViewMoodel::class.java)
        bind.vm=viewModel
        viewModel.signinNavigator=this
        bind.signUpTextView.setOnClickListener {
            val intent= Intent(this@SignInActivity, RegisterActivity::class.java)
            startActivity(intent)
        }
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
            showMessage(title = it, posButtonTitle = "ok")
        }
    }

    override fun goTOHomeScreen() {
        val intent=Intent(this,HomeActivity::class.java)
        startActivity(intent)
    }

    override fun goTOHomeAdminScreen() {
        val intent=Intent(this,AdminHomeActivity::class.java)
        startActivity(intent)
    }


}
