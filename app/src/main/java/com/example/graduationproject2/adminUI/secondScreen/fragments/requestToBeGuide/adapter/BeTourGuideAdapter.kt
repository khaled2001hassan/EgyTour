package com.example.graduationproject2.adminUI.secondScreen.fragments.requestToBeGuide.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.graduationproject2.R
import com.example.graduationproject2.adminUI.secondScreen.fragments.requestToBeGuide.RequestTourGuide
import com.example.graduationproject2.databinding.BeTourGuideBinding

class BeTourGuideAdapter(val request: MutableList<RequestTourGuide>) :
    RecyclerView.Adapter<BeTourGuideAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val bind = DataBindingUtil.inflate<BeTourGuideBinding>(LayoutInflater.from(parent.context),
            R.layout.be_tour_guide,parent,false)
        return ViewHolder(bind)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = request.get(position)
        holder.item.NameTourGuide.text=current.name
        holder.item.DescriptionTourGuide.text=current.descreption
        holder.item.IDTourGuide.text=current.id.toString()
        holder.item.EducationTourGuide.text=current.education
        holder.item.languageTourGuide.text=current.language
        holder.item.LocationTourGuide.text=current.location
        holder.item.ButtonAccept.setOnClickListener {
            accept!!.acceptAction(current!!)
        }
        holder.item.ButtonReject.setOnClickListener {
            reject!!.rejectAction(current!!)
        }
    }
    var accept:Accept?=null
    interface Accept{
        fun acceptAction(guide:RequestTourGuide)
    }
    var reject:Reject?=null
    interface Reject{
        fun rejectAction(guide:RequestTourGuide)
    }

    override fun getItemCount(): Int {
        return request.size
    }

    class ViewHolder(val item : BeTourGuideBinding):RecyclerView.ViewHolder(item.root){
        fun setRequests(request: RequestTourGuide?){

        }
    }

}