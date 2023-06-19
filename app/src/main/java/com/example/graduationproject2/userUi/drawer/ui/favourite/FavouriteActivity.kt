package com.example.graduationproject2.userUi.drawer.ui.favourite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.example.graduationproject2.R
import com.example.graduationproject2.base.rooms.MyDataBase
import com.example.graduationproject2.databinding.ActivityDetailsBinding
import com.example.graduationproject2.databinding.ActivityFavouriteBinding
import com.example.graduationproject2.userUi.details.DetailsActivity
import com.example.graduationproject2.userUi.region.ui.adapters.PlaceAdapter
import com.example.graduationproject2.userUi.region.ui.base.PlaceWithImage

class FavouriteActivity : AppCompatActivity() {
    var adapter = PlaceAdapter(mutableListOf())
    lateinit var binding: ActivityFavouriteBinding
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == 1){
            getPlaces()
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_favourite)
//        setContentView(R.layout.activity_favourite)
        getPlaces()

    }
    fun getPlaces(){
        MyDataBase.getInstance(this).getPlaceDao().getPlace()
        val adapterItem = MyDataBase.getInstance(this).getPlaceDao().getPlace()
        if(adapterItem.isEmpty()){
            binding.NoItemImageView.isVisible=true
        }else{
            binding.NoItemImageView.isVisible=false

        }
         adapter.changeData(adapterItem)
//        Log.e("khaled",adapterItem.get(0).id)
        adapter.onClick=object :PlaceAdapter.OnClick{
            override fun click(placeWithImage: PlaceWithImage) {
                val intent = Intent(this@FavouriteActivity, DetailsActivity::class.java)
                intent.putExtra("myObject", placeWithImage)
                startActivity(intent)
            }
        }
        binding.FavouritePlaceRecycleView.adapter=adapter
    }

    override fun onResume() {
        super.onResume()
        getPlaces()
    }
}