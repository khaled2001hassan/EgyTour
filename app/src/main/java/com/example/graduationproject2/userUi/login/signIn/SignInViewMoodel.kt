package com.example.graduationproject2.userUi.login.signIn


import androidx.databinding.ObservableField
import com.example.graduationproject2.base.BaseViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class SignInViewMoodel : BaseViewModel() {
    var email = ObservableField<String>()
    var password = ObservableField<String>()
    var errorEmail = ObservableField<String>()
    var errorPassword = ObservableField<String>()
    var auth: FirebaseAuth = Firebase.auth
    var signinNavigator: SigninNavigator? = null
    fun logIn() {
        if (valid()) return
        isLoadingLiveData.value = true
        auth.signInWithEmailAndPassword(email.get()!!, password.get()!!).addOnCompleteListener {
            isLoadingLiveData.value = false
            if (it.isSuccessful) {
               signinNavigator!!.goTOHomeScreen()

            } else {
                dialogMessageLiveData.value = it.exception?.message

            }
        }
    }

    fun valid(): Boolean {
        var isValid = false
        if (email.get().isNullOrBlank()) {
            errorEmail.set("please enter your email")
            isValid = true
        } else {
            errorEmail.set(null)
        }
        if (password.get().isNullOrBlank()) {
            errorPassword.set("please enter your password")
            isValid = true
        } else {
            errorPassword.set(null)
        }
        return isValid
    }
}