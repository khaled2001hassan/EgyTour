package com.example.graduationproject2.userUi.region.ui.places

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.example.graduationproject2.R
import com.example.graduationproject2.base.BaseFragment
import com.example.graduationproject2.databinding.FragmentPlacesBinding
import com.example.graduationproject2.userUi.details.DetailsActivity
import com.example.graduationproject2.userUi.region.ui.adapters.PlaceAdapter
import com.example.graduationproject2.userUi.region.ui.base.BaseReturn
import com.example.graduationproject2.userUi.region.ui.base.PlaceWithImage

class PlacesFragment() : BaseFragment() {
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
            adapter=PlaceAdapter(it)
            adapter.onClick=object :PlaceAdapter.OnClick{
                override fun click(placeWithImage: PlaceWithImage) {
                    val intent =Intent(requireActivity(),DetailsActivity::class.java)
                    intent.putExtra("myObject", placeWithImage)
                    startActivity(intent)
                }

            }
            binding.PlaceRecycleView.adapter=adapter
        }
    }

}