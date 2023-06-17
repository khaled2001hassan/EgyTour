package com.example.graduationproject2.userUi.login.signUp

import android.util.Log
import androidx.databinding.ObservableField
import com.example.graduationproject2.base.BaseViewModel
import com.example.graduationproject2.userUi.login.base.UserInfo
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class RegisterViewModel:BaseViewModel() {
    var firstName=ObservableField<String>()
    var lastName=ObservableField<String>()
    var email=ObservableField<String>()
    var password=ObservableField<String>()
    var errorFirstName=ObservableField<String>()
    var errorLastName=ObservableField<String>()
    var errorEmail=ObservableField<String>()
    var errorPassword=ObservableField<String>()
    var auth: FirebaseAuth = Firebase.auth

    fun createAccount() {
        Log.e("click create", "create account ")
        if (valid()) return
        isLoadingLiveData.value = true
        val auth = FirebaseAuth.getInstance()
        auth.createUserWithEmailAndPassword(email.get()!!, password.get()!!)
            .addOnSuccessListener { authResult ->
                val user = UserInfo(id = authResult.user!!.uid, firstName = firstName.get(), lastName = lastName.get())
                Firebase.firestore.collection("user").document(authResult.user!!.uid).set(user).addOnSuccessListener {
                    isLoadingLiveData.value = false
                    dialogMessageLiveData.value = "done"
                }
            }
            .addOnFailureListener { exception ->
                isLoadingLiveData.value = false
                dialogMessageLiveData.value = exception.message
                Log.e("create account ", "signInWithEmail:failure", exception)
            }
    }
    fun valid():Boolean {
        var isValid = false
        if (firstName.get().isNullOrBlank()) {
            errorFirstName.set("please enter your first name")
            isValid = true
        }else{ errorFirstName.set(null)}
        if (lastName.get().isNullOrBlank()) {
            errorLastName.set("please enter your last name")
            isValid = true
        }else{ errorLastName.set(null)}
        if (email.get().isNullOrBlank()) {
            errorEmail.set("please enter your email")
            isValid = true
        }else{ errorEmail.set(null)}
        if (password.get().isNullOrBlank()) {
            errorPassword.set("please enter your password")
            Log.e("click", "password")
            isValid = true
        }else{ errorPassword.set(null)}
        return isValid
    }
}