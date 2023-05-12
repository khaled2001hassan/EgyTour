package com.example.graduationproject2.ui.places

import android.util.Log
import com.example.graduationproject2.base.BaseViewModel
import com.example.graduationproject2.ui.places.ui.places.Places
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class RegionViewModel : BaseViewModel() {
    val placesList = mutableListOf<Places>()
    fun getPlaces(gov: String, city: String) {
        val test = Firebase.firestore.collection(gov).document(city)
        test.collection("places").get().addOnSuccessListener { collectionSnapShot ->
            collectionSnapShot.documents.forEach() {
                var place : Places = it.toObject<Places>()!!
                placesList.add(place)
                place.id=it.id
                Log.e("getPlaces: ", place.name)
            }
        }
    }
}