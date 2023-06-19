package com.example.graduationproject2.adminUI.secondScreen.fragments.requestToBeGuide

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.example.graduationproject2.R
import com.example.graduationproject2.adminUI.secondScreen.fragments.requestToBeGuide.adapter.BeTourGuideAdapter
import com.example.graduationproject2.base.BaseFragment
import com.example.graduationproject2.databinding.FragmentBeTourGuideBinding

class BeTourGuideFragment : BaseFragment() {
    lateinit var binding: FragmentBeTourGuideBinding
    val adapter = BeTourGuideAdapter(mutableListOf())
    private lateinit var viewModel: BeTourGuideViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_be_tour_guide, container, false)
        return binding.root
//        return inflater.inflate(R.layout.fragment_be_tour_guide, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(BeTourGuideViewModel::class.java)
        viewModel.getRequests()
        observation()



    }

    private fun observation() {
        viewModel.requestsLiveData.observe(viewLifecycleOwner) {
            if(it.isNullOrEmpty()){
                binding.NoItemImageView.isVisible=true
            }else{
                binding.NoItemImageView.isVisible=false

            }
            val beTourGuideAdapter = BeTourGuideAdapter(it)
            binding.RequestsRecycleView.adapter = beTourGuideAdapter
            beTourGuideAdapter.accept = object : BeTourGuideAdapter.Accept {
                override fun acceptAction(guide: RequestTourGuide) {
                    viewModel.accept(guide)
                }

            }
            beTourGuideAdapter.reject = object : BeTourGuideAdapter.Reject {
                override fun rejectAction(guide: RequestTourGuide) {
                    viewModel.reject(guide)
                }


            }
        }
        viewModel.isLoadingLiveData.observe(viewLifecycleOwner){
            if (it){
                showLoading()
            }else{
                hideLoading()
            }
        }

    }

}