package com.android.copycreativeroutines.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.copycreativeroutines.data.Great
import com.android.copycreativeroutines.databinding.ItemGreatsListBinding
import com.bumptech.glide.Glide
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage


class GreatsRVAdapter : RecyclerView.Adapter<GreatsRVAdapter.GreatsViewHolder>() {
    private lateinit var itemClickListner: ItemClickListener
    var greatsList = mutableListOf<Great>()

    interface ItemClickListener{
        fun onClick(view: View, position: Int)
    }

    fun setItemClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListner = itemClickListener
    }

    class GreatsViewHolder (private val binding : ItemGreatsListBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(data : Great) {
            Firebase.storage.reference.child(data.image).downloadUrl.addOnSuccessListener {
                Glide.with(this.itemView.context)
                    .load(it.toString())
                    .circleCrop()
                    .into(binding.ivGreatsImage)
            }
            binding.tvGreatName.text = data.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GreatsViewHolder {
        val binding = ItemGreatsListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GreatsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GreatsViewHolder, position: Int) {
        holder.onBind(greatsList[position])

        holder.itemView.setOnClickListener{
            itemClickListner.onClick(it,position)
        }
    }

    override fun getItemCount() = greatsList.size
}