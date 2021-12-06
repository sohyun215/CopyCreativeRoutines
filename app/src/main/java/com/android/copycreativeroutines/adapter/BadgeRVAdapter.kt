package com.android.copycreativeroutines.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.copycreativeroutines.R
import com.android.copycreativeroutines.data.User
import com.android.copycreativeroutines.databinding.ItemBadgeListBinding

class BadgeRVAdapter: RecyclerView.Adapter<BadgeRVAdapter.BadgeViewHolder>(){
    var badgeList= mutableListOf<MutableMap<String,Long>>()

    inner class BadgeViewHolder (val binding : ItemBadgeListBinding) : RecyclerView.ViewHolder(binding.root){
        val title=binding.titleCategory
        val badgeImage=binding.badgeImageView
        init {

            itemView.setOnClickListener {
            }
        }
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BadgeRVAdapter.BadgeViewHolder {
        val view= ItemBadgeListBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return BadgeViewHolder(view)
    }

    override fun onBindViewHolder(holder: BadgeRVAdapter.BadgeViewHolder, position: Int) {
       for(e in badgeList[position].entries){
           holder.title.text=e.key
           //달성한 횟수에 따라 뱃지 아이콘 변경
           if(e.key=="writer"){
               when(e.value.toInt()){
                   //in 3..4->
                   //in 5-..6->
                   7->holder.badgeImage.setColorFilter(R.color.today_text_color)
               }
           }
           if(e.key=="composer"){
               when(e.value.toInt()){
                   //1->
                   //2->
                   //3->
               }
           }
           if(e.key=="architect"){
               if(e.value>=1) {

               }
           }
           if(e.key=="biologist"){
               if(e.value>=1) {

               }
           }
           if(e.key=="philosopher"){
               when(e.value.toInt()){
                   //1->
                   //2->
               }
           }
           if(e.key=="politician"){
               if(e.value>=1) {

               }
           }

       }
    }

    override fun getItemCount(): Int {
        return badgeList.size
    }
}