package com.example.graduationproject2.ui.places

import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.graduationproject2.R
import com.example.graduationproject2.base.Base
import com.example.graduationproject2.databinding.ActivityRegionBinding

class RegionActivity : Base<RegionViewModel, ActivityRegionBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind=DataBindingUtil.setContentView(this,R.layout.activity_region)
        viewModel=ViewModelProvider(this).get(RegionViewModel::class.java)
        val cityName = intent.getStringExtra("city")
        val governorateName = intent.getStringExtra("governorate")
        bind.CityName.text=cityName
        Log.e( "onBindViewHolder: ", cityName!!)
        viewModel.getPlaces(governorateName!!,cityName)

    }
}