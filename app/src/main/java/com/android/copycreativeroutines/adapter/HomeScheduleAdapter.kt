package com.android.copycreativeroutines.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.copycreativeroutines.data.Great
import com.android.copycreativeroutines.data.User
import com.android.copycreativeroutines.databinding.ItemScheduleListBinding

class HomeScheduleAdapter() : RecyclerView.Adapter<HomeScheduleAdapter.ViewHolder>(){
    var schedules = mutableListOf<User.Schedule>()

    inner class ViewHolder(val binding:ItemScheduleListBinding):RecyclerView.ViewHolder(binding.root){
        val titleView=binding.scheduleTitle
        val timeView=binding.scheduleTime
        val line=binding.line
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=ItemScheduleListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=schedules[position]
        val scheduleTime : String

        holder.titleView.text=item.title
        if(item.end.isEmpty()){
            scheduleTime = item.start
        }
        else {
            scheduleTime = "${item.start} - ${item.end}"
        }
        holder.timeView.text=scheduleTime
        Log.i("dd",schedules.toString())
        if(position==schedules.size-1){
            holder.line.visibility= View.GONE
        }
        else holder.line.visibility=View.VISIBLE
    }

    override fun getItemCount(): Int =schedules.size

}