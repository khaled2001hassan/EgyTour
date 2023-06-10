package com.example.graduationproject2.adminUI.secondScreen.fragments.problem

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.graduationproject2.adminUI.secondScreen.fragments.RequestToBeGuide.RequestTourGuide
import com.example.graduationproject2.adminUI.secondScreen.fragments.problem.adapter.ProplemWithoutID
import com.example.graduationproject2.adminUI.secondScreen.fragments.problem.adapter.problems
import com.example.graduationproject2.base.BaseViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class ProblemsViewModel : BaseViewModel() {
    var listOfRequests= mutableListOf<problems>()
    var requestsLiveData= MutableLiveData<MutableList<problems>>()
    fun getRequests(){
        Firebase.firestore.collection("Problems").get()
            .addOnSuccessListener { collection->
                collection.forEach{documantationSnapShot ->
                    val new:ProplemWithoutID=documantationSnapShot.toObject()
                    val data =problems(userID = documantationSnapShot.id, name = new.name, problem = new.problem
                    , solution = new.solution)
                    listOfRequests.add(data)

                }
                requestsLiveData.value=listOfRequests
            }.addOnFailureListener {


            }

    }
}