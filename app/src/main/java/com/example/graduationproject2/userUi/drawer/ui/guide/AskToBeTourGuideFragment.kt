package com.example.graduationproject2.userUi.drawer.ui.guide

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.graduationproject2.R
import com.example.graduationproject2.databinding.FragmentAskToBeTourGuideBinding

class AskToBeTourGuideFragment : Fragment() {


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
            viewModel.send()
        }
    }
}