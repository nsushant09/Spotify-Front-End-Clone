package com.neupanesushant.spotifyfrontendclone.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neupanesushant.spotifyfrontendclone.databinding.WhyPremiumRvCardBinding

class WhyPremiumAdapter(private val context : Context, private val arraylist : ArrayList<String>) : RecyclerView.Adapter<WhyPremiumAdapter.ViewHolder>() {

    inner class ViewHolder(binding : WhyPremiumRvCardBinding ) : RecyclerView.ViewHolder(binding.root){
        val description = binding.tvPremiumRvContent
    }
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): WhyPremiumAdapter.ViewHolder {
        return ViewHolder(
            WhyPremiumRvCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: WhyPremiumAdapter.ViewHolder, position: Int) {
        val string = arraylist.get(position)
        holder.description.text = string
    }

    override fun getItemCount(): Int = arraylist.size

}