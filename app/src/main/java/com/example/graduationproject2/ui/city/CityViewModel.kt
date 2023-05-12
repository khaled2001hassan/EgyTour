package com.example.graduationproject2.ui.city

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.graduationproject2.base.BaseViewModel
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class CityViewModel:BaseViewModel() {
    val db = Firebase.firestore
    var citynames= mutableListOf<String>()
     var cityNameMutableList=MutableLiveData<MutableList<String>>()

    fun getCity(gov:String){
       val test= db.collection(gov).get().addOnSuccessListener { result ->
           for (document in result) {

               citynames.add(document.id)
           }
           cityNameMutableList.value=citynames
       }
           .addOnFailureListener { exception ->
               Log.e( "Error getting documents", exception.message!!)
           }


    }
}