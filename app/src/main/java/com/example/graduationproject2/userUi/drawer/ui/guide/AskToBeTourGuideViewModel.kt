package com.example.graduationproject2.userUi.drawer.ui.guide

import androidx.databinding.ObservableField
import com.example.graduationproject2.base.BaseViewModel

class AskToBeTourGuideViewModel : BaseViewModel() {
    var name= ObservableField<String>()
    var errorName= ObservableField<String>()
    var description= ObservableField<String>()
    var errorDescription= ObservableField<String>()
    var iD= ObservableField<String>()
    var errorID= ObservableField<String>()
    var location= ObservableField<String>()
    var errorLocation= ObservableField<String>()
    var education= ObservableField<String>()
    var errorEducation= ObservableField<String>()
    var language= ObservableField<String>()
    var errorLanguage= ObservableField<String>()
    fun send(){
        if (valid()) return
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
        if (location.get().isNullOrBlank()) {
            errorLocation.set("please enter your location")
            isValid = true
        } else {
            location.set(null)
        }
        if (iD.get().isNullOrBlank()) {
            errorID.set("please enter your ID")
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