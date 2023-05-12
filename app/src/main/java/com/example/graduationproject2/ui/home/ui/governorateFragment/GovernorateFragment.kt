package com.example.graduationproject2.ui.home.ui.governorateFragment

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationproject2.R
import com.example.graduationproject2.ui.city.CityActivity
import com.example.graduationproject2.ui.home.ui.adpter.GovernorateAdapter
import com.example.graduationproject2.ui.home.ui.adpter.GovernorateItem

class GovernorateFragment : Fragment() {
    lateinit var recyclerView: RecyclerView
    lateinit var adabter: GovernorateAdapter
    private lateinit var viewModel: GovernorateViewModel
    val AdabterList = listOf<GovernorateItem>(
        GovernorateItem(name = "minya", image = R.drawable.minya),
        GovernorateItem(name = "cairo", image = R.drawable.cairo),
        GovernorateItem(name = "alexandria", image = R.drawable.alexandria),
        GovernorateItem(name = "luxor", image = R.drawable.luxor),
        GovernorateItem(name = "aswan", image = R.drawable.aswan),
        GovernorateItem(name = "giza", image = R.drawable.giza),
        GovernorateItem(name = "port said", image = R.drawable.port_said),
        GovernorateItem(name = "suez", image = R.drawable.suez),
        GovernorateItem(name = "qena", image = R.drawable.qena),
        GovernorateItem(name = "sohag", image = R.drawable.suhaj),
        GovernorateItem(name = "ismailia", image = R.drawable.ismailiya),
        GovernorateItem(name = "damietta", image = R.drawable.damietta),
        GovernorateItem(name = "beheira", image = R.drawable.behira),
        GovernorateItem(name = "kafr el sheikh", image = R.drawable.kafr_el_sheikh),
        GovernorateItem(name = "matruh", image = R.drawable.matrouh),
        GovernorateItem(name = "north sinai", image = R.drawable.sinai_del_nord),
        GovernorateItem(name = "faiyum", image = R.drawable.faium),
        GovernorateItem(name = "qalyubia", image = R.drawable.qalubiya),
        GovernorateItem(name = "menofia", image = R.drawable.menofia),
        GovernorateItem(name = "sharqia", image = R.drawable.sharqiyah),
        GovernorateItem(name = "dakahlia", image = R.drawable.dakahlia),
        GovernorateItem(name = "gharbia", image = R.drawable.gharbiya),
        GovernorateItem(name = "red sea", image = R.drawable.red_sea),
        GovernorateItem(name = "bani Suwayf", image = R.drawable.bani_suwayf),
    )
    companion object {
        fun newInstance() = GovernorateFragment()
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_governorate, container, false)
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(GovernorateViewModel::class.java)
        // TODO: Use the ViewModel

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView=view.findViewById(R.id.GovRecycleView)
        adabter=GovernorateAdapter(AdabterList)
        recyclerView.adapter=adabter
        adabter.itemClick=object : GovernorateAdapter.ItemClick{
            override fun click(gov: GovernorateItem) {
                val intent=Intent(requireActivity(),CityActivity::class.java)
                intent.putExtra("governorate",gov.name)
                startActivity(intent)

            }

        }


    }

}