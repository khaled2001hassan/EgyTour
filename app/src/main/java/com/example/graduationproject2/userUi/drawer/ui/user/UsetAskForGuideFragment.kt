package com.example.graduationproject2.userUi.drawer.ui.user

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
import com.example.graduationproject2.databinding.FragmentUsetAskForGuideBinding
import com.example.graduationproject2.userUi.home.HomeActivity
import com.example.graduationproject2.userUi.login.base.UserInfo

class UsetAskForGuideFragment(val myObject: UserInfo) : BaseFragment() {
    private lateinit var viewModel: UsetAskForGuideViewModel
    lateinit var binding :FragmentUsetAskForGuideBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_uset_ask_for_guide, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(UsetAskForGuideViewModel::class.java)
        binding.vm=viewModel
        binding.ButtonRequest.setOnClickListener {
            viewModel.send(myObject)
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
                val intent= Intent(requireActivity(), HomeActivity::class.java)
                intent.putExtra("myObject", myObject)
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }else{
                Toast.makeText(requireContext(), "please try again", Toast.LENGTH_SHORT).show()
            }
        }

    }

}