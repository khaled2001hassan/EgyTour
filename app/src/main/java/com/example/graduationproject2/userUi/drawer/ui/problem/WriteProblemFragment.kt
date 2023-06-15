package com.example.graduationproject2.userUi.drawer.ui.problem

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.graduationproject2.R

class WriteProblemFragment : Fragment() {

    companion object {
        fun newInstance() = WriteProblemFragment()
    }

    private lateinit var viewModel: WriteProblemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_write_problem, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(WriteProblemViewModel::class.java)
        // TODO: Use the ViewModel
    }

}