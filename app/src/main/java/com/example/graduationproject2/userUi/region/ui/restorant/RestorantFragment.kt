package com.example.graduationproject2.userUi.region.ui.restorant

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.example.graduationproject2.R
import com.example.graduationproject2.base.BaseFragment
import com.example.graduationproject2.databinding.FragmentRestorantBinding
import com.example.graduationproject2.userUi.region.ui.adapters.RestaurantAdapter
import com.example.graduationproject2.userUi.region.ui.base.BaseReturn

class RestorantFragment : BaseFragment() {
var basereturn :BaseReturn?=null
    companion object {
        fun newInstance(basereturn:BaseReturn):RestorantFragment{
            val restorantFragment = RestorantFragment()
            restorantFragment.basereturn=basereturn
            return restorantFragment
        }
    }

    private lateinit var viewModel: RestorantViewModel
    lateinit var binding: FragmentRestorantBinding
    var restaurantAdapter=RestaurantAdapter(mutableListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_restorant, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(RestorantViewModel::class.java)
        viewModel.getRestaurant(basereturn!!)
        observation()
    }

    private fun observation() {
        viewModel.isLoadingLiveData.observe(this){
            if (it){
                showLoading()
            }else{
                hideLoading()
            }
        }
        viewModel.dataMutableLiveData.observe(viewLifecycleOwner){
            if(it==null){
                binding.NoItemImageView.isVisible=true
            }else{
                binding.NoItemImageView.isVisible=false
            }
            restaurantAdapter=RestaurantAdapter(it)
            binding.RestaurantRecycleView.adapter=restaurantAdapter

        }

    }

}