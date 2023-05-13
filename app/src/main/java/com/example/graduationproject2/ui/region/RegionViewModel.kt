package com.example.graduationproject2.ui.region

import android.util.Log
import com.example.graduationproject2.base.BaseViewModel
import com.example.graduationproject2.ui.region.ui.base.Places
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class RegionViewModel : BaseViewModel() {
    val placesList = mutableListOf<Places>()
    fun getPlaces(gov: String, city: String) {

    }
}