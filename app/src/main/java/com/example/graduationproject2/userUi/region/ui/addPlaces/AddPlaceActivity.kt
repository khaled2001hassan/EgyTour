package com.example.graduationproject2.userUi.region.ui.addPlaces

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.graduationproject2.R
import com.example.graduationproject2.base.Base
import com.example.graduationproject2.databinding.ActivityAddPlaceBinding
import com.example.graduationproject2.databinding.ActivityRegionBinding

class AddPlaceActivity : Base<AddPlaceViewModel,ActivityAddPlaceBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_add_place)
        bind=DataBindingUtil.setContentView(this,R.layout.activity_add_place)
        viewModel=ViewModelProvider(this).get(AddPlaceViewModel::class.java)
        val cityName = intent.getStringExtra("city")
        val governorateName = intent.getStringExtra("governorate")

    }
}