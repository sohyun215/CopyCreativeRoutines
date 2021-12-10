package com.android.copycreativeroutines.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.copycreativeroutines.data.Great
import com.android.copycreativeroutines.data.User
import com.android.copycreativeroutines.databinding.ItemScheduleListBinding
import com.android.copycreativeroutines.util.FBAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class HomeScheduleAdapter() : RecyclerView.Adapter<HomeScheduleAdapter.ViewHolder>(){
    var schedules = mutableListOf<User.Schedule>()

    inner class ViewHolder(val binding:ItemScheduleListBinding):RecyclerView.ViewHolder(binding.root){
        val titleView=binding.scheduleTitle
        val timeView=binding.scheduleTime
        val complete = binding.completeBtn
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
        holder.complete.setOnClickListener{
            Log.d("btn",item.category)
            Log.d("btn",item.title)
            Log.d("btn",item.date)
            if (!item.success.toBoolean()) {
                var path =""
                var catNum :Long
                val fbSchedules = Firebase.database.getReference("User").child(FBAuth.getUid()).child("schedule").child(item.date)
                fbSchedules.orderByChild("start").addValueEventListener(object :
                    ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (ds in snapshot.children) {
                            if (ds.child("title").value.toString().equals(item.title)) {
                                Log.d("check",ds.toString())
                                path = ds.key.toString()
                                Log.d("check",path)
                                fbSchedules.child(path).child("success").setValue(true.toString())
                            }
                        }
                    }
                    override fun onCancelled(error: DatabaseError) {
                        Log.e("database", "database failed")
                    }
                })
            }
        }
        if(position==schedules.size-1){
            holder.line.visibility= View.GONE
        }
        else holder.line.visibility=View.VISIBLE
    }

    override fun getItemCount(): Int =schedules.size

}