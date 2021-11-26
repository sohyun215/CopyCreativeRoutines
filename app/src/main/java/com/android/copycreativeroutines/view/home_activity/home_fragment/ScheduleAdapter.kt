package com.android.copycreativeroutines.view.home_activity.home_fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.copycreativeroutines.databinding.ItemScheduleListBinding
import com.android.copycreativeroutines.view.home_activity.Schedule

class ScheduleAdapter(val schedule:ArrayList<Schedule>)
    : RecyclerView.Adapter<ScheduleAdapter.ViewHolder>(){
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
        val item=schedule[position]
        holder.timeView.text=item.time
        holder.titleView.text=item.title
    }

    override fun getItemCount(): Int =schedule.size


}