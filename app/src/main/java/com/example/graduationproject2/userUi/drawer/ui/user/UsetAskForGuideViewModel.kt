package com.example.graduationproject2.userUi.drawer.ui.user

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.graduationproject2.adminUI.secondScreen.fragments.askTour.RequestForTourGuide
import com.example.graduationproject2.adminUI.secondScreen.fragments.requestToBeGuide.RequestTourGuide
import com.example.graduationproject2.base.BaseViewModel
import com.example.graduationproject2.userUi.login.base.UserInfo
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class UsetAskForGuideViewModel : BaseViewModel() {
    var governorate= ObservableField<String>()
    var errorGovernorate= ObservableField<String>()
    var phone= ObservableField<String>()
    var errorPhone= ObservableField<String>()
    var days= ObservableField<String>()
    var errorDays= ObservableField<String>()
    var language= ObservableField<String>()
    var errorLanguage= ObservableField<String>()
    val checkLiveData= MutableLiveData<Boolean>()
    fun send(myObject: UserInfo){
        if (valid()) return
        val userRequestTourGuide= RequestForTourGuide(
            userid = myObject.id,
            language = language.get(),
            governorate = governorate.get(),
            days = days.get(),
            phone = phone.get()

        )
        isLoadingLiveData.value=true
        Firebase.firestore.collection("AskForTourGuide").document(myObject.id!!)
            .set(userRequestTourGuide).addOnSuccessListener {
                checkLiveData.value=true
                isLoadingLiveData.value=false

            }.addOnFailureListener {
                checkLiveData.value=false
                isLoadingLiveData.value=false
            }
    }
    fun valid(): Boolean {
        var isValid = false
        if (governorate.get().isNullOrBlank()) {
            errorGovernorate.set("please enter your governorate")
            isValid = true
        } else {
            errorGovernorate.set(null)
        }
        if (language.get().isNullOrBlank()) {
            errorLanguage.set("please enter your language")
            isValid = true
        } else {
            errorLanguage.set(null)
        }
        if (days.get().isNullOrBlank()) {
            errorDays.set("please enter long days")
            isValid = true
        } else {
            errorDays.set(null)
        }
        if (phone.get().isNullOrBlank()) {
            errorPhone.set("please enter your governorate")
            isValid = true
        } else {
            errorPhone.set(null)
        }
        return isValid
    }
}