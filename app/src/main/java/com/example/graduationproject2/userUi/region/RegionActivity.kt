package com.example.graduationproject2.userUi.region

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.graduationproject2.R
import com.example.graduationproject2.base.Base
import com.example.graduationproject2.databinding.ActivityRegionBinding
import com.example.graduationproject2.userUi.region.ui.base.BaseReturn
import com.example.graduationproject2.userUi.region.ui.hospital.HospitalFragment
import com.example.graduationproject2.userUi.region.ui.hotel.HotelFragment
import com.example.graduationproject2.userUi.region.ui.places.PlacesFragment
import com.example.graduationproject2.userUi.region.ui.restorant.RestorantFragment

class RegionActivity : Base<RegionViewModel, ActivityRegionBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bind=DataBindingUtil.setContentView(this,R.layout.activity_region)
        viewModel=ViewModelProvider(this).get(RegionViewModel::class.java)
        val cityName = intent.getStringExtra("city")
        val governorateName = intent.getStringExtra("governorate")
        bind.CityName.text=cityName
        val baseReturn=BaseReturn(governorateName!!,cityName!!)
        initListener(baseReturn)



    }
    fun initListener(baseReturn:BaseReturn){
        showFragment(PlacesFragment.getInstance(baseReturn))
        bind.RegionBottomNavigation.setOnItemSelectedListener {
           if (it.itemId==R.id.PlacesRegion){
               showFragment(PlacesFragment.getInstance(baseReturn))
           }else if (it.itemId==R.id.HospitalRegion){
               showFragment(HospitalFragment.newInstance(baseReturn))
           }else if (it.itemId==R.id.HotelRegion){
               showFragment(HotelFragment.newInstance(baseReturn))

           }else if (it.itemId==R.id.RestaurantRegion){
               showFragment(RestorantFragment.newInstance(baseReturn))
           }
            return@setOnItemSelectedListener true
        }
    }
  fun showFragment(fragment: Fragment){
      supportFragmentManager.beginTransaction().replace(R.id.RegionFragment,fragment).commit()
  }
}