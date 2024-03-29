package com.example.graduationproject2.userUi.region.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationproject2.R
import com.example.graduationproject2.databinding.ItemPlaceBinding
import com.example.graduationproject2.userUi.region.ui.base.PlaceWithImage

class PlaceAdapter(var items: MutableList<PlaceWithImage>) :
    RecyclerView.Adapter<PlaceAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val bind = DataBindingUtil.inflate<ItemPlaceBinding>(
            LayoutInflater.from(parent.context), R.layout.item_place, parent, false
        )
        return ViewHolder(bind)
    }

    fun changeData(newItems: MutableList<PlaceWithImage>) {
        items = newItems
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = items.get(position)
        holder.setTest(current)
        holder.itemPlace.PlaceTicketTextView.text = current.ticket.toString()
        holder.itemView.setOnClickListener {
            onClick!!.click(current)
        }
    }

    var onClick: OnClick? = null

    interface OnClick {
        fun click(placeWithImage: PlaceWithImage)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(val itemPlace: ItemPlaceBinding) : RecyclerView.ViewHolder(itemPlace.root) {
        fun setTest(place: PlaceWithImage) {
            itemPlace.test = place

        }
    }
}