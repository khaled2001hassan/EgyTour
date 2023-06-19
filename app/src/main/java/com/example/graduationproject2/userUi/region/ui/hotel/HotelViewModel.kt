package com.example.graduationproject2.userUi.region.ui.hotel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.graduationproject2.base.BaseViewModel
import com.example.graduationproject2.userUi.region.ui.base.BaseReturn
import com.example.graduationproject2.userUi.region.ui.base.PlaceWithImage
import com.example.graduationproject2.userUi.region.ui.base.Places
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class HotelViewModel : BaseViewModel() {
    var placesList = mutableListOf<PlaceWithImage>()
    var dataMutableLiveData = MutableLiveData<MutableList<PlaceWithImage>>()
    fun getHotels(baseReturn: BaseReturn) {
        isLoadingLiveData.value=true
        val test = Firebase.firestore.collection(baseReturn.governorate)
            .document(baseReturn.cityNameReturn)
        test.collection("hotels").get().addOnSuccessListener { collectionSnapShot ->
            collectionSnapShot.documents.forEach() { documantationSnapShot ->
                val place: Places = documantationSnapShot.toObject<Places>()!!
                var imageUrl=""
                val imageRef = FirebaseStorage.getInstance().reference.child("${baseReturn.governorate}/${documantationSnapShot.id}.jpg")
                imageRef.downloadUrl.addOnSuccessListener { uri ->
                    imageUrl = uri.toString()
                    val placeWithImage = PlaceWithImage(
                        id = documantationSnapShot.id, name = place.name,
                        description = place.description, ticket = place.ticket, imageId = imageUrl
                    )
                    placesList.add(placeWithImage)
                    dataMutableLiveData.value = placesList
                    isLoadingLiveData.value=false

                }.addOnFailureListener { exception ->
                    isLoadingLiveData.value=false

                }

            }
        }.addOnFailureListener { exception ->
            isLoadingLiveData.value=false


        }
    }

    }

