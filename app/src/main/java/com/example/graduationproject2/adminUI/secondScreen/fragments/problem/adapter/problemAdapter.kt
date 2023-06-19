package com.example.graduationproject2.adminUI.secondScreen.fragments.problem.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationproject2.R
import com.example.graduationproject2.adminUI.secondScreen.fragments.askTour.RequestForTourGuide
import com.example.graduationproject2.databinding.ItemProblemBinding

class problemAdapter(var proplem: MutableList<problems>) :
    RecyclerView.Adapter<problemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val bind = DataBindingUtil.inflate<ItemProblemBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_problem,parent,false)
        return ViewHolder(bind)
    }
    fun changeProplem(item:MutableList<problems>){
        proplem = item
        notifyDataSetChanged()
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = proplem.get(position)
        holder.item.ProplemTextView.text=current.problem
        holder.item.solutionTextView.text=current.solution
        holder.item.UserNameTextView.text=current.name
        holder.item.Done.setOnClickListener {
            doneClick!!.click(current)
        }

    }
    var doneClick:DoneClick?=null
    interface DoneClick{
        fun click(proplem :problems)
    }

    override fun getItemCount(): Int {
        return proplem.size
    }

    class ViewHolder(val item :ItemProblemBinding): RecyclerView.ViewHolder(item.root){

    }

}