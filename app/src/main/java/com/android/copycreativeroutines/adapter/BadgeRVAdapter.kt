package com.android.copycreativeroutines.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.copycreativeroutines.R
import com.android.copycreativeroutines.databinding.ItemBadgeListBinding

class BadgeRVAdapter : RecyclerView.Adapter<BadgeRVAdapter.BadgeViewHolder>() {
    var badgeList = mutableListOf<MutableMap<String, Long>>()
    var badgeImageList = mutableListOf<MutableMap<String, MutableMap<String, Int>>>()

    inner class BadgeViewHolder(val binding: ItemBadgeListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val title = binding.badgeTitle
        val badgeImage = binding.badgeImage

        fun onBind(m: MutableMap<String, MutableMap<String, Int>>) {
            for (e in m.entries) { //{writer1 = {작가 1명달성 = 이미지 id}}
                for (m in e.value) {
                    title.text = m.key
                    badgeImage.setImageResource(m.value)
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BadgeRVAdapter.BadgeViewHolder {
        val view = ItemBadgeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BadgeViewHolder(view)
    }

    override fun onBindViewHolder(holder: BadgeRVAdapter.BadgeViewHolder, position: Int) {
        holder.onBind(badgeImageList[position])
        for (i in badgeImageList[position].entries) {
            for (e in 0 until badgeList.size) {
                for (title in badgeList[e]) {
                    if (i.key == "composer_bronze") {
                        if (title.key == "composer" && title.value.toInt()>=1) {
                            holder.badgeImage.setImageResource(R.drawable.ic_composer_bronze)
                        }
                    }
                    if (i.key == "composer_silver") {
                        if (title.key == "composer" && title.value.toInt()>=2) {
                            holder.badgeImage.setImageResource(R.drawable.ic_composer_silver)
                        }
                    }
                    if (i.key == "composer_gold") {
                        if (title.key == "composer" && title.value.toInt()>=3) {
                            holder.badgeImage.setImageResource(R.drawable.ic_composer_gold)
                        }
                    }
                    if (i.key == "writer_bronze") {
                        if (title.key == "writer" && title.value.toInt()>=3) {
                            holder.badgeImage.setImageResource(R.drawable.ic_writer_bronze)
                        }
                    }
                    if (i.key == "writer_silver") {
                        if (title.key == "writer" && title.value.toInt()>=5) {
                            holder.badgeImage.setImageResource(R.drawable.ic_writer_silver)
                        }
                    }
                    if (i.key == "writer_gold") {
                        if (title.key == "writer" && title.value.toInt()>=7) {
                            holder.badgeImage.setImageResource(R.drawable.ic_writer_gold)
                        }
                    }
                    if (i.key == "philosopher_silver") {
                        if (title.key == "philosopher" && title.value.toInt()>=1) {
                            holder.badgeImage.setImageResource(R.drawable.ic_philosopher_silver)
                        }
                    }
                    if (i.key == "philosopher_gold") {
                        if (title.key == "philosopher" && title.value.toInt()>=2) {
                            holder.badgeImage.setImageResource(R.drawable.ic_philosopher_gold)
                        }
                    }
                    if (i.key == "politician") {
                        if (title.key == "politician" && title.value.toInt()>=1) {
                            holder.badgeImage.setImageResource(R.drawable.ic_politician_gold)
                        }
                    }
                    if (i.key == "architect") {
                        if (title.key == "architect" && title.value.toInt()>=1) {
                            holder.badgeImage.setImageResource(R.drawable.ic_architect_gold)
                        }
                    }
                    if (i.key == "biologist") {
                        if (title.key == "biologist" && title.value.toInt()>=1) {
                            holder.badgeImage.setImageResource(R.drawable.ic_biologist_gold)
                        }
                    }
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return badgeImageList.size
    }
}