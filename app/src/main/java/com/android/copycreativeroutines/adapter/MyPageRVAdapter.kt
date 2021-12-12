package com.android.copycreativeroutines.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.copycreativeroutines.data.User
import com.android.copycreativeroutines.databinding.ItemMypageDiaryListBinding

class MyPageRVAdapter(val diaryList:MutableList<User.Diary>)
    : RecyclerView.Adapter<MyPageRVAdapter.ViewHolder>(){
    var itemClickListener:OnItemClickListener?=null

    interface OnItemClickListener{
        fun OnItemClick(holder: ViewHolder,view:View,position: Int)
    }
    inner class ViewHolder(val binding: ItemMypageDiaryListBinding): RecyclerView.ViewHolder(binding.root){
        val dateView=binding.diaryDate
        val contentView=binding.diaryContent
        init {
            itemView.setOnClickListener {
                itemClickListener?.OnItemClick(this,it,position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view= ItemMypageDiaryListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item=diaryList[position]
        holder.dateView.text=item.date
        holder.contentView.text=item.content
    }

    override fun getItemCount(): Int =diaryList.size

    override fun getItemViewType(position: Int) = position
}