package com.example.graduationproject2.userUi.drawer.ui.user

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.graduationproject2.R
import com.example.graduationproject2.databinding.FragmentUsetAskForGuideBinding

class UsetAskForGuideFragment : Fragment() {
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
            viewModel.send()
        }

    }

}