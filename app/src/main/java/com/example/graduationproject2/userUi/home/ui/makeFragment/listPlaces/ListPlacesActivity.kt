package com.example.graduationproject2.userUi.home.ui.makeFragment.listPlaces

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.graduationproject2.R
import com.example.graduationproject2.base.Base
import com.example.graduationproject2.base.rooms.MyDataBase
import com.example.graduationproject2.databinding.ActivityListPlacesBinding
import com.example.graduationproject2.userUi.details.DetailsActivity
import com.example.graduationproject2.userUi.region.ui.adapters.PlaceAdapter
import com.example.graduationproject2.userUi.region.ui.base.BaseReturn
import com.example.graduationproject2.userUi.region.ui.base.PlaceWithImage
import com.example.graduationproject2.userUi.region.ui.places.PlacesViewModel
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
            adapter.onClick=object :PlaceAdapter.OnClick{
                override fun click(placeWithImage: PlaceWithImage) {
                    val intent = Intent(this@ListPlacesActivity, DetailsActivity::class.java)
                    intent.putExtra("myObject", placeWithImage)
                    startActivity(intent)
                }
            }
            bind.PlacesRecyclerView.adapter=adapter
//               Log.e("khaled",it.name!!)

        }
    }
     fun selectPlaces(placesList: MutableList<PlaceWithImage>?, badget: Int):MutableList<PlaceWithImage> {
         val returnPlaces= mutableListOf<PlaceWithImage>()
         val baseList = placesList
         var newbadget=badget
         if (baseList.isNullOrEmpty())return returnPlaces
         baseList.forEach(){
             if (it.ticket==0){
                 returnPlaces.add(it)
                 baseList.remove(it)
             }
         }
         while(newbadget >= 0 && !baseList.isEmpty()){
             val randomPlace = baseList.random()
             if (randomPlace.ticket!! <= newbadget){
                 returnPlaces.add(randomPlace)
                 newbadget -= randomPlace.ticket!!
                 baseList.remove(randomPlace)
             }else{
             baseList.remove(randomPlace)
             }
         }
         return returnPlaces

    }


}