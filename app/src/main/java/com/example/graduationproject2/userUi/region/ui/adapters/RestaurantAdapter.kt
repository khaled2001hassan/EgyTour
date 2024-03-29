package com.example.graduationproject2.userUi.region.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationproject2.R
import com.example.graduationproject2.databinding.ItemPlaceBinding
import com.example.graduationproject2.databinding.ItemResturantBinding
import com.example.graduationproject2.userUi.region.ui.base.PlaceWithImage
import com.example.graduationproject2.userUi.region.ui.base.Restaurant

class RestaurantAdapter(val items:MutableList<Restaurant>):
    RecyclerView.Adapter<RestaurantAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val bind=DataBindingUtil.inflate<ItemResturantBinding>(LayoutInflater.from(parent.context)
            ,R.layout.item_resturant,parent,false)
        return ViewHolder(bind)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       val current =items.get(position)
        holder.setTest(current)

    }

    override fun getItemCount(): Int {
       return items.size
    }

    class ViewHolder(val itemRestuarant:ItemResturantBinding):RecyclerView.ViewHolder(itemRestuarant.root){
        fun setTest (restaurant: Restaurant){
            itemRestuarant.test=restaurant

        }
    }
}