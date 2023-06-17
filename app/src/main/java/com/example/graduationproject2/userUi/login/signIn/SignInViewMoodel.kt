package com.example.graduationproject2.userUi.login.signIn


import android.util.Log
import androidx.databinding.ObservableField
import com.example.graduationproject2.base.BaseViewModel
import com.example.graduationproject2.userUi.login.base.UserInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
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
            if (it.isSuccessful) {
                val userid = auth.currentUser!!.uid
                val admins =Firebase.firestore.collection("admins").get()
                admins.addOnSuccessListener{queryone->
                    queryone.documents.forEach{documentationone->
                        Firebase.firestore.collection("user").get().addOnSuccessListener {
                            it.documents.forEach{
                                val userinfo:UserInfo=it.toObject<UserInfo>()!!
                                if (documentationone.id==userid){
                                    signinNavigator!!.goTOHomeAdminScreen(userinfo)
                                    isLoadingLiveData.value = false
                                }else{
                                    signinNavigator!!.goTOHomeScreen(userinfo)
                                    isLoadingLiveData.value = false

                                }
                            }

                        }

                    }
                }


            } else {
                dialogMessageLiveData.value = it.exception?.message
                isLoadingLiveData.value = false

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