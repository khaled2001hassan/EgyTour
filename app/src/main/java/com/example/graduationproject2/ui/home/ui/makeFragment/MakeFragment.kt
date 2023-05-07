package com.example.graduationproject2.ui.home.ui.makeFragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.graduationproject2.R

class MakeFragment : Fragment() {

    companion object {
        fun newInstance() = MakeFragment()
    }

    private lateinit var viewModel: MakeViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_make, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MakeViewModel::class.java)
        // TODO: Use the ViewModel
    }

}