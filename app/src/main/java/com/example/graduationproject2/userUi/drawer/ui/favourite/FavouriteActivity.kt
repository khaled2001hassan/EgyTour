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
    lateinit var adapter : PlaceAdapter
    lateinit var binding: ActivityFavouriteBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_favourite)
//        setContentView(R.layout.activity_favourite)
        MyDataBase.getInstance(this).getPlaceDao().getPlace()
        val adapterItem = MyDataBase.getInstance(this).getPlaceDao().getPlace()
        if(adapterItem==null){
            binding.NoItemImageView.isVisible=false
        }else{
            binding.NoItemImageView.isVisible=true
        }
        adapter = PlaceAdapter(adapterItem)
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
}