package com.android.copycreativeroutines.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.copycreativeroutines.data.Great
import com.android.copycreativeroutines.data.User
import com.android.copycreativeroutines.databinding.ItemMypageDiaryListBinding

class MyPageRVAdapter(val diaryList:MutableList<User.Diary>)
    : RecyclerView.Adapter<MyPageRVAdapter.ViewHolder>(){


    inner class ViewHolder(val binding: ItemMypageDiaryListBinding): RecyclerView.ViewHolder(binding.root){
        val dateView=binding.diaryDate
        val contentView=binding.diaryContent
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

}