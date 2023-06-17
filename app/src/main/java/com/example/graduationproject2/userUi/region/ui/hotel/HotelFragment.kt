package com.example.graduationproject2.userUi.region.ui.hotel

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.graduationproject2.R
import com.example.graduationproject2.databinding.FragmentHotelBinding
import com.example.graduationproject2.userUi.details.DetailsActivity
import com.example.graduationproject2.userUi.region.ui.adapters.PlaceAdapter
import com.example.graduationproject2.userUi.region.ui.base.BaseReturn
import com.example.graduationproject2.userUi.region.ui.base.PlaceWithImage


class HotelFragment : Fragment() {
    var basereturn: BaseReturn? = null

    companion object {
        fun newInstance(basereturn: BaseReturn): HotelFragment {
            val hotelFragment = HotelFragment()
            hotelFragment.basereturn = basereturn
            return hotelFragment
        }
    }

    var hotelsAdapter = PlaceAdapter(mutableListOf())
    private lateinit var viewModel: HotelViewModel
    lateinit var binding: FragmentHotelBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_hotel, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(HotelViewModel::class.java)
        viewModel.getHotels(basereturn!!)
        observation()

    }

    private fun observation() {
        viewModel.dataMutableLiveData.observe(viewLifecycleOwner) {
            hotelsAdapter= PlaceAdapter(it)
            hotelsAdapter.onClick=object :PlaceAdapter.OnClick{
                override fun click(placeWithImage: PlaceWithImage) {
                    val khaled = "khaled"
                }

            }
            binding.HotelsRecycleView.adapter=hotelsAdapter
        }
    }

}