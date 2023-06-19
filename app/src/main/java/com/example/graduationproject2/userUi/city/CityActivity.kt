package com.example.graduationproject2.userUi.city
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.graduationproject2.R
import com.example.graduationproject2.base.Base
import com.example.graduationproject2.databinding.ActivityCityBinding
import com.example.graduationproject2.userUi.city.Adapter.CityAdapter
import com.example.graduationproject2.userUi.region.RegionActivity
class CityActivity : Base<CityViewModel,ActivityCityBinding>() {
     var adapter= CityAdapter(listOf())
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_city)
        bind=DataBindingUtil.setContentView(this,R.layout.activity_city)
        viewModel=ViewModelProvider(this).get(CityViewModel::class.java)
        val governorateName= intent.getStringExtra("governorate")
        bind.GovName.text=governorateName
        viewModel.getCity(governorateName!!)
        observation(governorateName)

    }
    fun observation(governorateName:String){
        viewModel.isLoadingLiveData.observe(this){
            if (it){
                showLoading()
            }else{
                hideLoading()
            }
        }
        viewModel.cityNameMutableList.observe(this){
            adapter= CityAdapter(it.toList())
            bind.CityRecycleView.adapter=adapter
            adapter.clickItem=object :CityAdapter.ClickItem{
                override fun clickCity(name: String) {
                    val intent=Intent(this@CityActivity,RegionActivity::class.java)
                    intent.putExtra("governorate",governorateName)
                    intent.putExtra("city",name)
                    startActivity(intent)
                }

            }


        }
    }
}