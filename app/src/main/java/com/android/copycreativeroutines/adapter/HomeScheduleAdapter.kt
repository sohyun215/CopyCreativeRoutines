package com.android.copycreativeroutines.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.copycreativeroutines.data.Great
import com.android.copycreativeroutines.databinding.ItemScheduleListBinding

class HomeScheduleAdapter(val schedule:List<Great.Schedule>)
    : RecyclerView.Adapter<HomeScheduleAdapter.ViewHolder>(){
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
        val scheduleTime : String

        holder.titleView.text=item.title
        if(item.end.isEmpty()){
            scheduleTime = item.start
        }
        else {
            scheduleTime = "${item.start} - ${item.end}"
        }
        holder.timeView.text=scheduleTime
    }

    override fun getItemCount(): Int =schedule.size


}