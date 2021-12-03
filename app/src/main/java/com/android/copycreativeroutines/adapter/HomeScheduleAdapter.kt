package com.android.copycreativeroutines.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.copycreativeroutines.data.Great
import com.android.copycreativeroutines.databinding.ItemScheduleListBinding

class HomeScheduleAdapter() : RecyclerView.Adapter<HomeScheduleAdapter.ViewHolder>(){
    var schedules = mutableListOf<Great.Schedule>()

    inner class ViewHolder(val binding:ItemScheduleListBinding):RecyclerView.ViewHolder(binding.root){
        val titleView=binding.scheduleTitle
        val timeView=binding.scheduleTime
        init{

            itemView.setOnClickListener {

            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=ItemScheduleListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=schedules[position]
        val scheduleTime : String

        holder.titleView.text=item.title
        if(item.endTime.isEmpty()){
            scheduleTime = item.startTime
        }
        else {
            scheduleTime = "${item.startTime} - ${item.endTime}"
        }
        holder.timeView.text=scheduleTime
    }

    override fun getItemCount(): Int =schedules.size


}