package com.example.graduationproject2.adminUI.secondScreen.fragments.AskTour

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.graduationproject2.R
import com.example.graduationproject2.adminUI.secondScreen.fragments.AskTour.adapter.AskForTourGuideAdapter
import com.example.graduationproject2.adminUI.secondScreen.fragments.RequestToBeGuide.adapter.BeTourGuideAdapter
import com.example.graduationproject2.databinding.FragmentAskForTourGuideBinding

class AskForTourGuideFragment : Fragment() {

    companion object {
        fun newInstance() = AskForTourGuideFragment()
    }

    private lateinit var viewModel: AskForTourGuideViewModel
    lateinit var binding:FragmentAskForTourGuideBinding

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
            var askForTourGuideAdapter=AskForTourGuideAdapter(it)
            binding.UserRequestsRecycle.adapter= askForTourGuideAdapter
            askForTourGuideAdapter.accept=object :AskForTourGuideAdapter.Accept{
                override fun acceptAction(userID: String) {
                    viewModel.accept(userID)
                }

            }
            askForTourGuideAdapter.reject=object :AskForTourGuideAdapter.Reject{
                override fun rejectAction(userID: String) {
                    viewModel.reject(userID)
                }

            }

        }
    }

}