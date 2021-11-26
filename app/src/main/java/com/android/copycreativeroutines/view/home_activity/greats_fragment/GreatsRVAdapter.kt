package com.android.copycreativeroutines.view.home_activity.greats_fragment

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.android.copycreativeroutines.R
import com.android.copycreativeroutines.databinding.ItemGreatsListBinding


class GreatsRVAdapter : RecyclerView.Adapter<GreatsRVAdapter.GreatsViewHolder>() {
    val greatsList = mutableListOf<Great>()

    class GreatsViewHolder (private val binding : ItemGreatsListBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(data : Great) {
            binding.ivGreatsImage.setImageResource(R.drawable.ic_greats_image)

            itemView.setOnClickListener {
                val intent = Intent(itemView?.context, GreatsDetailActivity::class.java)
                    .putExtra("image", data.image)
                    .putExtra("name", data.name)
                ContextCompat.startActivity(itemView.context, intent, null)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GreatsViewHolder {
        val binding = ItemGreatsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GreatsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GreatsViewHolder, position: Int) {
        holder.onBind(greatsList[position])
    }

    override fun getItemCount() = greatsList.size
}