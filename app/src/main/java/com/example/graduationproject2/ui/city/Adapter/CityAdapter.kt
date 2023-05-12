package com.example.graduationproject2.ui.city.Adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationproject2.R
import com.example.graduationproject2.databinding.ItemCityBinding

class CityAdapter(val city: List<String>) : RecyclerView.Adapter<CityAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemCityBinding>(LayoutInflater.from(parent.context),
            R.layout.item_city,parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = city.get(position)
        holder.item.CityName.text=current
        holder.itemView.setOnClickListener {
            clickItem?.clickCity(current)

        }
    }

    override fun getItemCount(): Int {
        return city.size
    }
    var clickItem:ClickItem?=null
    interface ClickItem{
        fun clickCity(name:String)
    }

    class ViewHolder(val item: ItemCityBinding) : RecyclerView.ViewHolder(item.root) {
    }
}