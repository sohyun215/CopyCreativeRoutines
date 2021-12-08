package com.android.copycreativeroutines.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.copycreativeroutines.R
import com.android.copycreativeroutines.data.User
import com.android.copycreativeroutines.databinding.ItemRankingListBinding
import com.bumptech.glide.Glide

class RankingRVAdapter  : RecyclerView.Adapter<RankingRVAdapter.RankingViewHolder>() {
    val userList = mutableListOf<User>()

    class RankingViewHolder (private val binding : ItemRankingListBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(data : User, position: Int) {
            Glide.with(this.itemView.context)
                .load(R.drawable.ic_list_as)
                .circleCrop()
                .into(binding.ivUserProfile)
            binding.tvRankingNumber.text = (position+1).toString()
            binding.tvUserId.text = data.id
            binding.tvUserPoint.text = "${data.point}pt"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingViewHolder {
        val binding = ItemRankingListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RankingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RankingViewHolder, position: Int) {
        holder.onBind(userList[position], position)
    }

    override fun getItemCount() = userList.size
}