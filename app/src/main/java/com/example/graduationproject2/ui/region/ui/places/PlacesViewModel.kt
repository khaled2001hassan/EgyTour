package com.example.graduationproject2.ui.region.ui.places
import androidx.lifecycle.MutableLiveData
import com.example.graduationproject2.base.BaseViewModel
import com.example.graduationproject2.ui.region.ui.base.BaseReturn
import com.example.graduationproject2.ui.region.ui.base.PlaceWithImage
import com.example.graduationproject2.ui.region.ui.base.Places
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
class PlacesViewModel : BaseViewModel() {
    var placesList = mutableListOf<PlaceWithImage>()
    var dataMutableLiveData = MutableLiveData<MutableList<PlaceWithImage>>()
//    var imageUrl:String = "https://firebasestorage.googleapis.com/v0/b/egytour-492d1.appspot.com/o/minya%2FBahnasa.jpg?alt=media&token=736d25ca-1b62-42fe-b95b-e53253e13992"
fun getPlaces(baseReturn: BaseReturn) {
    val test = Firebase.firestore.collection(baseReturn.governorate)
        .document(baseReturn.cityNameReturn)
    test.collection("places").get().addOnSuccessListener { collectionSnapShot ->
        collectionSnapShot.documents.forEach() { documantationSnapShot ->
            val place: Places = documantationSnapShot.toObject<Places>()!!
            var imageUrl=""
            val imageRef = FirebaseStorage.getInstance().reference.child("${baseReturn.governorate}/${place.name}.jpg")
            imageRef.downloadUrl.addOnSuccessListener { uri ->
                imageUrl = uri.toString()
                val placeWithImage = PlaceWithImage(
                    id = documantationSnapShot.id, name = place.name,
                    description = place.description, ticket = place.ticket, imageId = imageUrl
                )
                placesList.add(placeWithImage)
                dataMutableLiveData.value = placesList
            }.addOnFailureListener { exception ->
            }
        }
    }
}
}
