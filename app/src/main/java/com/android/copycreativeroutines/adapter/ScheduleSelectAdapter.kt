package com.android.copycreativeroutines.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.copycreativeroutines.data.Great
import com.android.copycreativeroutines.databinding.ItemGreatScheduleListBinding

class ScheduleSelectAdapter : RecyclerView.Adapter<ScheduleSelectAdapter.ScheduleViewHolder>() {
    val sheduleList = mutableListOf<Great.Schedule>()
    var checkedList = mutableListOf<Int>()

    class ScheduleViewHolder (private val binding : ItemGreatScheduleListBinding) : RecyclerView.ViewHolder(binding.root){
        val checkBox = binding.cbScheduleSelect
        fun onBind(data : Great.Schedule) {
            binding.tvScheduleName.text = data.title

            val scheduleTime : String
            if(data.end.isEmpty()){
                scheduleTime = data.start
            }
            else {
                scheduleTime = "${data.start} - ${data.end}"
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

        holder.checkBox.setOnClickListener{
            if (holder.checkBox.isChecked) {
                checkedList.add(position)
            }
            else {
                checkedList.remove(position)
            }
        }
    }

    override fun getItemCount() = sheduleList.size

    override fun getItemViewType(position: Int) = position
}