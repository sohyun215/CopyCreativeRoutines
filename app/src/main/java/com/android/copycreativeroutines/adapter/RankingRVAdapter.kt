package com.android.copycreativeroutines.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.android.copycreativeroutines.databinding.ItemRankingListBinding
import com.android.copycreativeroutines.data.User
import com.android.copycreativeroutines.ui.RankingDetailActivity

class RankingRVAdapter  : RecyclerView.Adapter<RankingRVAdapter.RankingViewHolder>() {
    val userList = mutableListOf<User>()

    class RankingViewHolder (private val binding : ItemRankingListBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(data : User) {
            binding.tvUserId.text = data.id
            binding.tvUserPoint.text = data.point.toString()

            itemView.setOnClickListener {
                val intent = Intent(itemView?.context, RankingDetailActivity::class.java)
                    .putExtra("id", data.id)
                ContextCompat.startActivity(itemView.context, intent, null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RankingViewHolder {
        val binding = ItemRankingListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RankingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RankingViewHolder, position: Int) {
        holder.onBind(userList[position])
    }

    override fun getItemCount() = userList.size
}