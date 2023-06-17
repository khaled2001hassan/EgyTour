package com.example.graduationproject2.userUi.region.ui.hospital

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.graduationproject2.userUi.region.ui.base.BaseReturn
import com.example.graduationproject2.userUi.region.ui.base.Hospital
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class HospitalViewModel : ViewModel() {
    var placesList = mutableListOf<Hospital>()
    var dataMutableLiveData = MutableLiveData<MutableList<Hospital>>()
    fun getHospital(baseReturn: BaseReturn) {
        val test = Firebase.firestore.collection(baseReturn.governorate)
            .document(baseReturn.cityNameReturn)
        test.collection("hospitals").get().addOnSuccessListener { collectionSnapShot ->
            collectionSnapShot.documents.forEach() { documantationSnapShot ->
                val hospital: Hospital = documantationSnapShot.toObject<Hospital>()!!
                hospital.id = documantationSnapShot.id
                placesList.add(hospital)
                dataMutableLiveData.value = placesList
            }

        }.addOnFailureListener { exception ->
            Log.e("khaled", exception.message.toString())

        }
    }
}