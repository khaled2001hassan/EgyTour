package com.example.graduationproject2.adminUI.secondScreen.fragments.askTour

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.example.graduationproject2.R
import com.example.graduationproject2.adminUI.secondScreen.fragments.askTour.adapter.AskForTourGuideAdapter
import com.example.graduationproject2.base.BaseFragment
import com.example.graduationproject2.databinding.FragmentAskForTourGuideBinding

class AskForTourGuideFragment : BaseFragment() {

    companion object {
        fun newInstance() = AskForTourGuideFragment()
    }

    private lateinit var viewModel: AskForTourGuideViewModel
    lateinit var binding:FragmentAskForTourGuideBinding
    var askForTourGuideAdapter=AskForTourGuideAdapter(mutableListOf())
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_ask_for_tour_guide, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(AskForTourGuideViewModel::class.java)
        viewModel.getRequests()
        observation()

    }

    private fun observation() {
        viewModel.requestsLiveData.observe(viewLifecycleOwner){
            if(it.isNullOrEmpty()){
                binding.NoItemImageView.isVisible=true
            }else{
                binding.NoItemImageView.isVisible=false

            }
           askForTourGuideAdapter.changePlace(it)
            binding.UserRequestsRecycle.adapter= askForTourGuideAdapter
            askForTourGuideAdapter.accept=object :AskForTourGuideAdapter.Accept{
                override fun acceptAction(request: RequestForTourGuide) {
                    viewModel.accept(request)

                }

            }
            askForTourGuideAdapter.reject=object :AskForTourGuideAdapter.Reject{
                override fun rejectAction(request: RequestForTourGuide) {
                    viewModel.reject(request)

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

    override fun onResume() {
        super.onResume()
        viewModel.requestsLiveData.observe(viewLifecycleOwner){
            askForTourGuideAdapter.changePlace(it)
            binding.UserRequestsRecycle.adapter= askForTourGuideAdapter
            askForTourGuideAdapter.accept=object :AskForTourGuideAdapter.Accept{
                override fun acceptAction(request: RequestForTourGuide) {
                    viewModel.accept(request)
                }

            }
            askForTourGuideAdapter.reject=object :AskForTourGuideAdapter.Reject{
                override fun rejectAction(request: RequestForTourGuide) {
                    viewModel.reject(request)

                }


            }

        }
    }

}