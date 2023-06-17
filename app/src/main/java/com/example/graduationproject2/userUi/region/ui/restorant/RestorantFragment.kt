package com.example.graduationproject2.userUi.region.ui.restorant

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.graduationproject2.R
import com.example.graduationproject2.databinding.FragmentRestorantBinding
import com.example.graduationproject2.databinding.FragmentUsetAskForGuideBinding
import com.example.graduationproject2.userUi.region.ui.adapters.RestaurantAdapter
import com.example.graduationproject2.userUi.region.ui.base.BaseReturn

class RestorantFragment : Fragment() {
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
        viewModel.dataMutableLiveData.observe(viewLifecycleOwner){
            restaurantAdapter=RestaurantAdapter(it)
            binding.RestaurantRecycleView.adapter=restaurantAdapter

        }

    }

}