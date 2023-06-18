package com.example.graduationproject2.adminUI.secondScreen.fragments.requestToBeGuide

import androidx.lifecycle.MutableLiveData
import com.example.graduationproject2.adminUI.secondScreen.fragments.requestToBeGuide.adapter.BaseRequestTourGuide
import com.example.graduationproject2.base.BaseViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class BeTourGuideViewModel : BaseViewModel() {
    var listOfRequests = mutableListOf<RequestTourGuide>()
    var requestsLiveData = MutableLiveData<MutableList<RequestTourGuide>>()
    fun getRequests() {
        isLoadingLiveData.value=true
        Firebase.firestore.collection("AskToBeTourGuide").get()
            .addOnSuccessListener { collection ->
                collection.forEach { documantationSnapShot ->
                    val new: BaseRequestTourGuide = documantationSnapShot.toObject()
                    val data = RequestTourGuide(
                        name = new.name,
                        userId = documantationSnapShot.id,
                        id = new.id.toString(),
                        location = new.location,
                        education = new.education,
                        language = new.language,
                        descreption = new.descreption
                    )
                    listOfRequests.add(data)

                }
                requestsLiveData.value = listOfRequests
                isLoadingLiveData.value=false

            }.addOnFailureListener {
                isLoadingLiveData.value=false
            }

    }

    fun accept(guide: RequestTourGuide) {
        val data = BaseRequestTourGuide(
            name = guide.name,
            descreption = guide.descreption,
            id = guide.id.toString(),
            location = guide.location,
            education = guide.education,
            language = guide.language
        )
        isLoadingLiveData.value=true
        Firebase.firestore.collection("TourGuide").document(guide.userId!!)
            .set(data)
            .addOnSuccessListener {
                Firebase.firestore.collection("AskToBeTourGuide").document(guide.userId!!).delete() .addOnSuccessListener {
                    isLoadingLiveData.value=false
                }.addOnFailureListener {
                    isLoadingLiveData.value=false
                }
            }.addOnFailureListener {
                isLoadingLiveData.value=false
            }

    }

    fun reject(guide: RequestTourGuide) {
        isLoadingLiveData.value=true
        Firebase.firestore.collection("AskToBeTourGuide").document(guide.userId!!).delete()
            .addOnSuccessListener {
            isLoadingLiveData.value=false
        }.addOnFailureListener {
            isLoadingLiveData.value=false
        }
    }
}