package com.example.graduationproject2.adminUI.secondScreen.fragments.askTour.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationproject2.R
import com.example.graduationproject2.adminUI.secondScreen.fragments.askTour.RequestForTourGuide
import com.example.graduationproject2.databinding.AskForTourGuideBinding

class AskForTourGuideAdapter(val request: MutableList<RequestForTourGuide>) :
    RecyclerView.Adapter<AskForTourGuideAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
                val bind = DataBindingUtil.inflate<AskForTourGuideBinding>(
            LayoutInflater.from(parent.context),
            R.layout.ask_for_tour_guide,parent,false)
        return ViewHolder(bind)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = request.get(position)
        holder.item.GovernorateUser.text=current.governorate
        holder.item.LanguageUser.text=current.language
        holder.item.PhoneUser.text=current.phone.toString()
        holder.item.TourDays.text=current.days.toString()
        holder.item.ButtonAcceptRequest.setOnClickListener {
            accept!!.acceptAction(current!!)
        }
        holder.item.ButtonRejectRequest.setOnClickListener {
            reject!!.rejectAction(current!!)
        }

    }
    var accept:Accept?=null
    interface Accept{
        fun acceptAction(request:RequestForTourGuide)
    }
    var reject:Reject?=null
    interface Reject{
        fun rejectAction(request:RequestForTourGuide)
    }
    override fun getItemCount(): Int {
        return request.size
    }

    class ViewHolder(val item : AskForTourGuideBinding): RecyclerView.ViewHolder(item.root){

    }




}