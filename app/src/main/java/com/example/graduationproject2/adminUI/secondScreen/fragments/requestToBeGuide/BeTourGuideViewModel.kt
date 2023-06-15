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
        Firebase.firestore.collection("AskToBeTourGuide").get()
            .addOnSuccessListener { collection ->
                collection.forEach { documantationSnapShot ->
                    val new: BaseRequestTourGuide = documantationSnapShot.toObject()
                    val data = RequestTourGuide(
                        name = new.name,
                        userId = documantationSnapShot.id,
                        id = new.id,
                        location = new.location,
                        education = new.education,
                        language = new.language,
                        descreption = new.descreption
                    )
                    listOfRequests.add(data)

                }
                requestsLiveData.value = listOfRequests
            }.addOnFailureListener {


            }

    }

    fun accept(guide: RequestTourGuide) {
        val data = BaseRequestTourGuide(
            name = guide.name,
            descreption = guide.descreption,
            id = guide.id,
            location = guide.location,
            education = guide.education,
            language = guide.language
        )
        Firebase.firestore.collection("TourGuide").document(guide.userId!!)
            .set(data)
            .addOnSuccessListener {

            }
    }

    fun reject(guide: RequestTourGuide) {

    }
}