package com.example.graduationproject2.userUi.region.ui.hospital

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.graduationproject2.R
import com.example.graduationproject2.databinding.FragmentHospitalBinding
import com.example.graduationproject2.databinding.FragmentHotelBinding
import com.example.graduationproject2.userUi.region.ui.adapters.HospitalAdapter
import com.example.graduationproject2.userUi.region.ui.adapters.PlaceAdapter
import com.example.graduationproject2.userUi.region.ui.base.BaseReturn
import com.example.graduationproject2.userUi.region.ui.base.Hospital
import com.example.graduationproject2.userUi.region.ui.hotel.HotelFragment
import com.example.graduationproject2.userUi.region.ui.hotel.HotelViewModel

class HospitalFragment : Fragment() {
    var basereturn: BaseReturn? = null

    companion object {
        fun newInstance(basereturn: BaseReturn): HospitalFragment {
            val hospitalFragment = HospitalFragment()
            hospitalFragment.basereturn = basereturn
            return hospitalFragment
        }
    }

    var hospitalAdapter = HospitalAdapter(mutableListOf())
    lateinit var binding: FragmentHospitalBinding
    private lateinit var viewModel: HospitalViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_hospital, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(HospitalViewModel::class.java)
        viewModel.getHospital(basereturn!!)
        observation()
    }

    private fun observation() {
        viewModel.dataMutableLiveData.observe(viewLifecycleOwner){
            hospitalAdapter=HospitalAdapter(it)
            binding.HospitalRecycleView.adapter=hospitalAdapter

        }
    }

}