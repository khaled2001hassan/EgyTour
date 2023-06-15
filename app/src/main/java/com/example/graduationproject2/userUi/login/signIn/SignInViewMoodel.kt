package com.example.graduationproject2.userUi.login.signIn


import android.util.Log
import androidx.databinding.ObservableField
import com.example.graduationproject2.base.BaseViewModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
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
       val test = auth.signInWithEmailAndPassword(email.get()!!, password.get()!!)
        test.addOnCompleteListener {
            isLoadingLiveData.value = false
            if (it.isSuccessful) {
                val user = auth.currentUser!!.uid
                val admins =Firebase.firestore.collection("admins").get()
                admins.addOnSuccessListener{
                    it.documents.forEach{
                        if (it.id==user){
                            signinNavigator!!.goTOHomeAdminScreen()
                        }else{
                            signinNavigator!!.goTOHomeScreen()

                        }
                    }
                }


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