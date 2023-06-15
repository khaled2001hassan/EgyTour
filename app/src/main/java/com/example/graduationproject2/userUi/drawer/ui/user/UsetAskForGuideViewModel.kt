package com.example.graduationproject2.userUi.drawer.ui.user

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import com.example.graduationproject2.base.BaseViewModel

class UsetAskForGuideViewModel : BaseViewModel() {
    var governorate= ObservableField<String>()
    var errorGovernorate= ObservableField<String>()
    var phone= ObservableField<String>()
    var errorPhone= ObservableField<String>()
    var days= ObservableField<String>()
    var errorDays= ObservableField<String>()
    var language= ObservableField<String>()
    var errorLanguage= ObservableField<String>()
   fun send(){
       if (valid()) return
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