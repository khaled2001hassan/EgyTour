package com.example.graduationproject2.userUi.drawer.ui.problem

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.graduationproject2.R
import com.example.graduationproject2.base.BaseFragment
import com.example.graduationproject2.databinding.FragmentWriteProblemBinding
import com.example.graduationproject2.userUi.home.HomeActivity
import com.example.graduationproject2.userUi.login.base.UserInfo

class WriteProblemFragment(val myObject: UserInfo) : BaseFragment() {


    private lateinit var viewModel: WriteProblemViewModel
    lateinit var binding: FragmentWriteProblemBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_write_problem, container, false)
        return binding.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(WriteProblemViewModel::class.java)
        observation(myObject)
        binding.vm=viewModel
        binding.info=myObject
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