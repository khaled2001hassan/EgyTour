package com.example.graduationproject2.ui.region.ui.places

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.bumptech.glide.Glide
import com.example.graduationproject2.R
import com.example.graduationproject2.databinding.FragmentPlacesBinding
import com.example.graduationproject2.ui.region.ui.adapters.PlaceAdapter
import com.example.graduationproject2.ui.region.ui.base.BaseReturn
import com.example.graduationproject2.ui.region.ui.base.PlaceWithImage
class PlacesFragment() : Fragment() {
    var baseReturn: BaseReturn? = null
    var adapter=PlaceAdapter(mutableListOf())
    companion object {
        fun getInstance(baseReturn: BaseReturn):PlacesFragment{
            val placesFragment = PlacesFragment()
            placesFragment.baseReturn=baseReturn
            return placesFragment
        }
    }
    private lateinit var viewModel: PlacesViewModel
    lateinit var binding: FragmentPlacesBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_places, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(PlacesViewModel::class.java)
        viewModel.getPlaces(baseReturn!!)
        binding.PlaceRecycleView.adapter=adapter

        viewModel.dataMutableLiveData.observe(viewLifecycleOwner){
            adapter=PlaceAdapter(it)
            binding.PlaceRecycleView.adapter=adapter
        }
    }

}