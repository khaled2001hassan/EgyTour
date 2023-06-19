package com.example.graduationproject2.userUi.drawer.ui.guide

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.graduationproject2.adminUI.secondScreen.fragments.requestToBeGuide.RequestTourGuide
import com.example.graduationproject2.base.BaseViewModel
import com.example.graduationproject2.userUi.login.base.UserInfo
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class AskToBeTourGuideViewModel : BaseViewModel() {
    var name= ObservableField<String>()
    var errorName= ObservableField<String>()
    var description= ObservableField<String>()
    var errorDescription= ObservableField<String>()
    var iD= ObservableField<String>()
    var errorID= ObservableField<String>()
    var locationText= ObservableField<String>()
    var errorLocation= ObservableField<String>()
    var education= ObservableField<String>()
    var errorEducation= ObservableField<String>()
    var language= ObservableField<String>()
    var errorLanguage= ObservableField<String>()
    val checkLiveData=MutableLiveData<Boolean>()
    fun send(myObject: UserInfo){

        if (valid()) return

        val requestTourGuide= RequestTourGuide(
         userId= myObject.id,
         name = name.get(),
         descreption = description.get(),
         id =iD.get()!!,
         location = locationText.get(),
         education = education.get(),
         language = language.get()
        )
        isLoadingLiveData.value=true
        Firebase.firestore.collection("AskToBeTourGuide").document(myObject.id!!)
            .set(requestTourGuide).addOnSuccessListener {
                checkLiveData.value=true
                isLoadingLiveData.value=false

        }.addOnFailureListener {
                checkLiveData.value=false
                isLoadingLiveData.value=false
            }
    }

    fun valid(): Boolean {
        var isValid = false
        if (name.get().isNullOrBlank()) {
            errorName.set("please enter your name")
            isValid = true
        } else {
            errorName.set(null)
        }
        if (description.get().isNullOrBlank()) {
            errorDescription.set("please enter long description")
            isValid = true
        } else {
            errorDescription.set(null)
        }
        if (locationText.get().isNullOrBlank()) {
            errorLocation.set("please enter your location")
            isValid = true
        } else {
            errorLocation.set(null)
        }
        if (iD.get().isNullOrBlank()) {
            errorID.set("please enter your National ID")
            isValid = true
        } else {
            errorID.set(null)
        }
        if (language.get().isNullOrBlank()) {
            errorLanguage.set("please enter your language")
            isValid = true
        } else {
            errorLanguage.set(null)
        }
        if (education.get().isNullOrBlank()) {
            errorEducation.set("please enter your education")
            isValid = true
        } else {
            errorEducation.set(null)
        }

        return isValid
    }
}