package com.neupanesushant.spotifyfrontendclone.adapters

import android.text.Layout
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neupanesushant.spotifyfrontendclone.R
import com.neupanesushant.spotifyfrontendclone.data.DataLibraryContent
import com.neupanesushant.spotifyfrontendclone.databinding.LibraryOneElementRvCardBinding
import com.neupanesushant.spotifyfrontendclone.databinding.LibraryTwoElementRvCardBinding
import com.squareup.picasso.Picasso

class LibraryOneElementViewHolder(val binding: LibraryOneElementRvCardBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item : DataLibraryContent, position : Int) {
        Picasso.get().load(item.imageString).fit().centerCrop().into(binding.ivLibraryContentImage)
        binding.tvContentTitle.text = item.title
        binding.tvContentDesc.text = item.description
        if(!item.isPlaylist){
            binding.cvLibraryContentImageCard.setBackgroundResource(R.drawable.default_card_background)
        }
    }

    companion object {
        fun from(parent: ViewGroup): LibraryOneElementViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = LibraryOneElementRvCardBinding.inflate(layoutInflater, parent, false)
            return LibraryOneElementViewHolder(binding)
        }
    }
}