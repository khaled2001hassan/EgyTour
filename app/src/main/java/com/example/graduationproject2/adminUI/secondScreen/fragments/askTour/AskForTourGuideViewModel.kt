package com.example.graduationproject2.adminUI.secondScreen.fragments.askTour

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.graduationproject2.adminUI.secondScreen.fragments.askTour.adapter.BasicrequestForTourGuide
import com.example.graduationproject2.base.BaseViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class AskForTourGuideViewModel : BaseViewModel() {
    var listOfRequests = mutableListOf<RequestForTourGuide>()
    var requestsLiveData = MutableLiveData<MutableList<RequestForTourGuide>>()
    fun getRequests() {
        isLoadingLiveData.value=true
        Firebase.firestore.collection("AskForTourGuide").get()
            .addOnSuccessListener { collection ->
                collection.forEach { documantationSnapShot ->
                    val new: BasicrequestForTourGuide = documantationSnapShot.toObject()
                    val data = RequestForTourGuide(
                        userid = documantationSnapShot.id, governorate = new.governorate,
                        days = new.days,
                        language = new.language,
                        phone = new.phone
                    )
                    listOfRequests.add(data)
                }
                requestsLiveData.value = listOfRequests
                isLoadingLiveData.value=false
            }.addOnFailureListener {
                isLoadingLiveData.value=false
            }

    }

    fun accept(request:RequestForTourGuide) {
        isLoadingLiveData.value=true
        Firebase.firestore.collection("waitList").document(request.userid!!).set(request).addOnSuccessListener {
            Firebase.firestore.collection("AskForTourGuide").document(request.userid!!).delete() .addOnSuccessListener {
                isLoadingLiveData.value=false
            }.addOnFailureListener {
                isLoadingLiveData.value=false
            }

        }.addOnFailureListener {
            isLoadingLiveData.value=false
        }

    }

    fun reject(request:RequestForTourGuide) {
        isLoadingLiveData.value=true
        Firebase.firestore.collection("AskForTourGuide").document(request.userid!!).delete()
            .addOnSuccessListener {
            isLoadingLiveData.value=false
        }.addOnFailureListener {
            isLoadingLiveData.value=false
        }
    }
}