package com.example.graduationproject2.userUi.home.ui.adpter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationproject2.R
import com.example.graduationproject2.databinding.GovItemBinding

class GovernorateAdapter(val list: List<GovernorateItem>) :
    RecyclerView.Adapter<GovernorateAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<GovItemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.gov_item, parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list.get(position)
        holder.binding(currentItem)
        holder.itemView.setOnClickListener {
            itemClick!!.click(currentItem)
        }

    }

    override fun getItemCount(): Int {
        return list.size
    }

    var itemClick: ItemClick? = null

    interface ItemClick {
        fun click(gov: GovernorateItem)
    }

    class ViewHolder(val item: GovItemBinding) : RecyclerView.ViewHolder(item.root) {
        fun binding(listitem: GovernorateItem) {
            item.item = listitem
        }
    }
}