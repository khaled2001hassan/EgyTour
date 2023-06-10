package com.example.graduationproject2.adminUI.secondScreen.fragments.RequestToBeGuide

import android.app.Activity
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.graduationproject2.R
import com.example.graduationproject2.adminUI.secondScreen.fragments.AskTour.adapter.AskForTourGuideAdapter
import com.example.graduationproject2.adminUI.secondScreen.fragments.RequestToBeGuide.adapter.BeTourGuideAdapter
import com.example.graduationproject2.databinding.FragmentBeTourGuideBinding

class BeTourGuideFragment : Fragment() {
lateinit var binding :FragmentBeTourGuideBinding
val adapter = BeTourGuideAdapter(mutableListOf())
    companion object {
        fun newInstance() = BeTourGuideFragment()
    }

    private lateinit var viewModel: BeTourGuideViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_be_tour_guide, container, false)
        return binding.root
//        return inflater.inflate(R.layout.fragment_be_tour_guide, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(BeTourGuideViewModel::class.java)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(BeTourGuideViewModel::class.java)
        viewModel.getRequests()
        observation()


    }

    private fun observation() {
        viewModel.requestsLiveData.observe(viewLifecycleOwner){
            val beTourGuideAdapter=BeTourGuideAdapter(it)
            binding.RequestsRecycleView.adapter=beTourGuideAdapter
            beTourGuideAdapter.accept=object : BeTourGuideAdapter.Accept{
                override fun acceptAction(guide: RequestTourGuide) {
                    viewModel.accept(guide)
                }

            }
            beTourGuideAdapter.reject=object : BeTourGuideAdapter.Reject{
                override fun rejectAction(guide: RequestTourGuide) {
                    viewModel.reject(guide)
                }


            }
        }

    }

}