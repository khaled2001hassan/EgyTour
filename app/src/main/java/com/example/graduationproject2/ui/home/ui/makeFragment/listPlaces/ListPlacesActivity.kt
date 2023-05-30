package com.example.graduationproject2.ui.home.ui.makeFragment.listPlaces

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.graduationproject2.R
import com.example.graduationproject2.base.Base
import com.example.graduationproject2.databinding.ActivityListPlacesBinding
import com.example.graduationproject2.ui.region.ui.adapters.PlaceAdapter
import com.example.graduationproject2.ui.region.ui.base.BaseReturn
import com.example.graduationproject2.ui.region.ui.base.PlaceWithImage
import com.example.graduationproject2.ui.region.ui.places.PlacesViewModel
class ListPlacesActivity : Base<PlacesViewModel, ActivityListPlacesBinding>() {
    var adapter=PlaceAdapter(mutableListOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list_places)
        bind = DataBindingUtil.setContentView(this, R.layout.activity_list_places)
        viewModel = ViewModelProvider(this).get(PlacesViewModel::class.java)
        val cityName = intent.getStringExtra("city")
        val governorateName = intent.getStringExtra("governorate")
        val badget = intent.getStringExtra("budget")
        val baseReturn=BaseReturn(governorateName!!,cityName!!)
        viewModel.getPlaces(baseReturn)
        observation(badget!!.toInt())

    }
     fun observation(badget:Int) {
        viewModel.dataMutableLiveData.observe(this){
           adapter = PlaceAdapter(selectPlaces(it , badget))
            bind.PlacesRecyclerView.adapter=adapter
//               Log.e("khaled",it.name!!)

        }
    }
     fun selectPlaces(placesList: MutableList<PlaceWithImage>?, badget: Int):MutableList<PlaceWithImage> {
         val returnPlaces= mutableListOf<PlaceWithImage>()
         var newbadget=badget
         if (placesList.isNullOrEmpty())return returnPlaces
         placesList.forEach(){
             if (it.ticket==0){
                 returnPlaces.add(it)
             }
         }
         placesList.forEach(){
             if (it.ticket!! <= newbadget){
                 returnPlaces.add(it)
                 newbadget -= it.ticket!!
             }
         }

         return returnPlaces

    }

}