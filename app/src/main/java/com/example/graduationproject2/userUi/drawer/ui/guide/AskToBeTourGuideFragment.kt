package com.example.graduationproject2.userUi.drawer.ui.guide

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.graduationproject2.R
import com.example.graduationproject2.base.BaseFragment
import com.example.graduationproject2.databinding.FragmentAskToBeTourGuideBinding
import com.example.graduationproject2.userUi.home.HomeActivity
import com.example.graduationproject2.userUi.login.base.UserInfo

class AskToBeTourGuideFragment(val myObject:UserInfo) : BaseFragment() {


    private lateinit var viewModel: AskToBeTourGuideViewModel
    lateinit var bind : FragmentAskToBeTourGuideBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = DataBindingUtil.inflate(inflater,R.layout.fragment_ask_to_be_tour_guide, container, false)
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(AskToBeTourGuideViewModel::class.java)
        bind.vm=viewModel
        bind.ButtonApply.setOnClickListener {
            viewModel.send( myObject )
        }
        observation(myObject)
    }

    private fun observation(myObject:UserInfo) {
        viewModel.isLoadingLiveData.observe(viewLifecycleOwner){
            if (it){
                showLoading()
            }else{
                hideLoading()
            }
        }
        viewModel.checkLiveData.observe(viewLifecycleOwner){
            if (it){
                val intent=Intent(requireActivity(), HomeActivity::class.java)
                intent.putExtra("myObject", myObject)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }else{
                Toast.makeText(requireContext(), "please try again", Toast.LENGTH_SHORT).show()
            }
        }

    }
}