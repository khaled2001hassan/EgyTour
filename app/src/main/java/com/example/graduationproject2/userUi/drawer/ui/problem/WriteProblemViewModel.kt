package com.example.graduationproject2.userUi.drawer.ui.problem

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.example.graduationproject2.adminUI.secondScreen.fragments.problem.adapter.problems
import com.example.graduationproject2.base.BaseViewModel
import com.example.graduationproject2.userUi.login.base.UserInfo
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class WriteProblemViewModel : BaseViewModel() {
    var problem= ObservableField<String>()
    var errorProblem= ObservableField<String>()
    var solve= ObservableField<String>()
    var errorsolve= ObservableField<String>()
    val checkLiveData= MutableLiveData<Boolean>()
    fun send(myObject: UserInfo){
        if (valid()) return
        val proplem= problems( userID=myObject.id, name=myObject.firstName,
         solution=solve.get(), problem=problem.get())
        isLoadingLiveData.value=true
        Firebase.firestore.collection("Problems").document(myObject.id!!)
            .set(proplem).addOnSuccessListener {
                checkLiveData.value=true
                isLoadingLiveData.value=false

            }.addOnFailureListener {
                checkLiveData.value=false
                isLoadingLiveData.value=false
            }
    }
    fun valid(): Boolean {
        var isValid = false
        if (problem.get().isNullOrBlank()) {
            errorProblem.set("please enter your problem")
            isValid = true
        } else {
            errorProblem.set(null)
        }
        if (solve.get().isNullOrBlank()) {
            errorsolve.set("please enter solve")
            isValid = true
        } else {
            errorsolve.set(null)
        }
        return isValid
    }
}