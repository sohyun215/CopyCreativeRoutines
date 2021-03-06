package com.android.copycreativeroutines.adapter

import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.copycreativeroutines.data.User
import com.android.copycreativeroutines.databinding.ItemScheduleListBinding
import com.android.copycreativeroutines.util.FBAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class HomeScheduleAdapter() : RecyclerView.Adapter<HomeScheduleAdapter.ViewHolder>() {
    var schedules = mutableListOf<User.Schedule>()

    inner class ViewHolder(val binding: ItemScheduleListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val titleView = binding.scheduleTitle
        val timeView = binding.scheduleTime
        val complete = binding.completeBtn
        val line = binding.line
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            ItemScheduleListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = schedules[position]
        val scheduleTime: String

        holder.titleView.text = item.title
        if (item.end.isEmpty()) {
            scheduleTime = item.start
        } else {
            scheduleTime = "${item.start} - ${item.end}"
        }
        holder.timeView.text = scheduleTime
        if (item.success == "true") {
            holder.complete.text = "완료"
            holder.complete.setTextColor(Color.parseColor("#6EC883"))
        } else {
            holder.complete.text = "진행중"
            holder.complete.setTextColor(Color.parseColor("#CA8989"))
        }

        Log.i("dd", schedules.toString())

        holder.complete.setOnClickListener {
            if (!item.success.toBoolean()) {
                // 해당하는 category 값 증가
                val fbCat =
                    Firebase.database.getReference("User").child(FBAuth.getUid()).child("category")
                fbCat.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (ds in snapshot.children) {
                            if (ds.key.equals(item.category)) {
                                val catNum = ds.value.toString().toLong() + 1
                                Log.d("catNum", "$catNum")
                                fbCat.child("${item.category}").setValue(catNum)
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        TODO("Not yet implemented")
                    }
                })

                // success 값 변경
                val fbSchedules =
                    Firebase.database.getReference("User").child(FBAuth.getUid()).child("schedule")
                        .child(item.date)
                fbSchedules.orderByChild("start").addListenerForSingleValueEvent(object :
                    ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        for (ds in snapshot.children) {
                            if (ds.child("title").value.toString().equals(item.title)) {
                                var path = ds.key.toString()
                                fbSchedules.child(path).child("success").setValue(true.toString())
                            }
                        }
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.e("database", "database failed")
                    }
                })

                // point 값 증가
                val fbPoint =
                    Firebase.database.getReference("User").child(FBAuth.getUid()).child("point")
                fbPoint.addListenerForSingleValueEvent(object : ValueEventListener {
                    override fun onDataChange(snapshot: DataSnapshot) {
                        val point = snapshot.value.toString().toInt()
                        fbPoint.setValue("${point + 1}")
                    }

                    override fun onCancelled(error: DatabaseError) {
                        Log.e("database", "database failed")
                    }
                })
            } else {
                Log.d("complete", "이미 완료된 일과입니다.")
            }
        }
        if (position == schedules.size - 1) {
            holder.line.visibility = View.GONE
        } else holder.line.visibility = View.VISIBLE
    }

    override fun getItemCount(): Int = schedules.size

    override fun getItemViewType(position: Int) = position
}