package com.android.copycreativeroutines.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.copycreativeroutines.data.Great
import com.android.copycreativeroutines.databinding.ItemGreatScheduleListBinding

class ScheduleSelectAdapter : RecyclerView.Adapter<ScheduleSelectAdapter.ScheduleViewHolder>() {
    val sheduleList = mutableListOf<Great.Schedule>()

    class ScheduleViewHolder (private val binding : ItemGreatScheduleListBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(data : Great.Schedule) {
            binding.tvScheduleName.text = data.title

            val scheduleTime : String
            if(data.endTime.isEmpty()){
                scheduleTime = data.startTime
            }
            else {
                scheduleTime = "${data.startTime} - ${data.endTime}"
            }
            binding.tvScheduleTime.text = scheduleTime

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ScheduleViewHolder {
        val binding = ItemGreatScheduleListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ScheduleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ScheduleViewHolder, position: Int) {
        holder.onBind(sheduleList[position])
    }

    override fun getItemCount() = sheduleList.size
}