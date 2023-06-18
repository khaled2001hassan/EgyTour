package com.example.graduationproject2.adminUI.secondScreen.fragments.problem
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.graduationproject2.R
import com.example.graduationproject2.adminUI.secondScreen.fragments.problem.adapter.problemAdapter
import com.example.graduationproject2.adminUI.secondScreen.fragments.problem.adapter.problems
import com.example.graduationproject2.databinding.FragmentProblemsBinding
class ProblemsFragment : Fragment() {

    companion object {
        fun newInstance() = ProblemsFragment()
    }

    private lateinit var viewModel: ProblemsViewModel
    lateinit var binding :FragmentProblemsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_problems, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(ProblemsViewModel::class.java)
        viewModel.getRequests()
        observation()

    }
//    override fun onResume() {
//        super.onResume()
//        observation()
//    }

    private fun observation() {
        viewModel.requestsLiveData.observe(viewLifecycleOwner){
        val problemAdapter=problemAdapter(it)
            problemAdapter.doneClick=object :problemAdapter.DoneClick{
                override fun click(proplem: problems) {
                    viewModel.done(proplem)
                }

            }
            binding.ProblemRecycleView.adapter= problemAdapter
        }
    }

}