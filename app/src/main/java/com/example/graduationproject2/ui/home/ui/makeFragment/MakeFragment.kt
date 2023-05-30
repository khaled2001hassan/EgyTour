package com.example.graduationproject2.ui.home.ui.makeFragment

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.text.isDigitsOnly
import androidx.databinding.DataBindingUtil
import com.example.graduationproject2.R
import com.example.graduationproject2.databinding.FragmentMakeBinding
import com.example.graduationproject2.ui.home.ui.makeFragment.listPlaces.ListPlacesActivity

class MakeFragment : Fragment() {
    lateinit var binding:FragmentMakeBinding
    private lateinit var viewModel: MakeViewModel
    var governorate ="Alexandria"
    var  city ="Borg El Arab"
    lateinit var currentCity:Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding= DataBindingUtil.inflate(inflater,R.layout.fragment_make, container, false)
        return binding.root
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MakeViewModel::class.java)
        initSelected()
        initListener()

    }

    fun initSelected() {

        binding.GovernorateSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val govArray = resources.getStringArray(R.array.governorate)
                governorate=govArray[p2]
                Log.e( "test: ", governorate)
                // Get the corresponding city list
                val cityListResourceId = resources.getIdentifier(
                    governorate.toLowerCase().replace(" ", "_"),
                    "array",
                    requireActivity().packageName
                )
                val cityArray = resources.getStringArray(cityListResourceId)
                currentCity=cityArray

                val cityAdapter = ArrayAdapter(
                    requireContext(),
                    android.R.layout.simple_spinner_item,
                    cityArray
                )
                cityAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.CitySpinner.adapter = cityAdapter
            }


            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
        binding.CitySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                city=currentCity[p2]
                Log.e( "currentcity ", city)
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

        }
    }
    fun initListener() {
        binding.MakeButton.setOnClickListener {
            if (isValid()) return@setOnClickListener

            val intent =Intent(requireActivity(), ListPlacesActivity::class.java)
            intent.putExtra("governorate",governorate)
            intent.putExtra("city",city)

            val budget = binding.BudgetTextInput.editText!!.text.toString()

            intent.putExtra("budget",budget)
            startActivity(intent)

        }
    }
    fun isValid():Boolean {
        var valid = false
        if(binding.BudgetTextInput.editText!!.text.isEmpty()){
            binding.BudgetTextInput.error="please enter valid budget"
            valid=true
        }else if ( !binding.BudgetTextInput.editText!!.text.isDigitsOnly()){
            binding.BudgetTextInput.error="please enter valid budget"
            valid=true
        }else {
            binding.BudgetTextInput.error=""
        }
        return valid
    }

}