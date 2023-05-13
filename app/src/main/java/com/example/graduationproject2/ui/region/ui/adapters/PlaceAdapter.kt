package com.example.graduationproject2.ui.region.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.graduationproject2.R
import com.example.graduationproject2.databinding.ItemPlaceBinding
import com.example.graduationproject2.ui.region.ui.base.PlaceWithImage

class PlaceAdapter(val items:MutableList<PlaceWithImage>):
    RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val bind=DataBindingUtil.inflate<ItemPlaceBinding>(LayoutInflater.from(parent.context)
            ,R.layout.item_place,parent,false)
        return ViewHolder(bind)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val current =items.get(position)
        holder.setTest(current)
        holder.itemPlace.PlaceTicketTextView.text =current.ticket.toString()



    }

    override fun getItemCount(): Int {
       return items.size
    }

    class ViewHolder(val itemPlace:ItemPlaceBinding):RecyclerView.ViewHolder(itemPlace.root){
        fun setTest (place:PlaceWithImage){
            itemPlace.test=place

        }
    }
}