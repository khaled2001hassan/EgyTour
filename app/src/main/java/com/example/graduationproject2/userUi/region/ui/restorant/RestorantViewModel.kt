package com.example.graduationproject2.userUi.region.ui.restorant

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.graduationproject2.base.BaseViewModel
import com.example.graduationproject2.userUi.region.ui.base.*
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage

class RestorantViewModel : BaseViewModel() {
    var restaurantList = mutableListOf<Restaurant>()
    var dataMutableLiveData = MutableLiveData<MutableList<Restaurant>>()
    fun getRestaurant(baseReturn: BaseReturn) {
        isLoadingLiveData.value=true
        val test = Firebase.firestore.collection(baseReturn.governorate)
            .document(baseReturn.cityNameReturn)
        test.collection("restorant").get().addOnSuccessListener { collectionSnapShot ->
            collectionSnapShot.documents.forEach() { documantationSnapShot ->
                val restaurantWithoutImages: RestaurantWithoutImages = documantationSnapShot.toObject<RestaurantWithoutImages>()!!
                var imageUrl=""
                val imageRef = FirebaseStorage.getInstance().reference.child("${baseReturn.governorate}/${documantationSnapShot.id}.jpg")
                imageRef.downloadUrl.addOnSuccessListener { uri ->
                    imageUrl = uri.toString()
                    val placeWithImage = Restaurant(
                        id = documantationSnapShot.id,
                        name = restaurantWithoutImages.name,
                        description = restaurantWithoutImages.description,
                        phone = restaurantWithoutImages.phone,
                        imageURl = imageUrl
                        , location = restaurantWithoutImages.location
                    )
                    restaurantList.add(placeWithImage)
                    dataMutableLiveData.value = restaurantList
                    isLoadingLiveData.value=false

                }.addOnFailureListener { exception ->
                    Log.e("khaled",exception.message.toString())
                    isLoadingLiveData.value=false

                }

            }
        }.addOnFailureListener { exception ->
            Log.e("khaled",exception.message.toString())
            isLoadingLiveData.value=false


        }
    }
}