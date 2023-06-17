package com.example.graduationproject2.userUi.region.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationproject2.R
import com.example.graduationproject2.databinding.ItemHospitalBinding
import com.example.graduationproject2.databinding.ItemResturantBinding
import com.example.graduationproject2.userUi.region.ui.base.Hospital


class HospitalAdapter(val items:MutableList<Hospital>):
    RecyclerView.Adapter<HospitalAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val bind=DataBindingUtil.inflate<ItemHospitalBinding>(LayoutInflater.from(parent.context)
            ,R.layout.item_hospital,parent,false)
        return ViewHolder(bind)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val current =items.get(position)
        holder.setTest(current)

    }

    override fun getItemCount(): Int {
       return items.size
    }

    class ViewHolder(val itemHospital:ItemHospitalBinding):RecyclerView.ViewHolder(itemHospital.root){
        fun setTest (hospital: Hospital){
            itemHospital.test=hospital

        }
    }
}