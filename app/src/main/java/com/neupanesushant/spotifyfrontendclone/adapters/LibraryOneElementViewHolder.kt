package com.neupanesushant.spotifyfrontendclone.adapters

import android.content.Context
import android.content.Intent
import android.provider.ContactsContract
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.neupanesushant.spotifyfrontendclone.R
import com.neupanesushant.spotifyfrontendclone.activities.LibraryAddActivity
import com.neupanesushant.spotifyfrontendclone.activities.LibraryContentOptionsActivity
import com.neupanesushant.spotifyfrontendclone.clickedLibraryObject
import com.neupanesushant.spotifyfrontendclone.data.DataLibraryContent
import com.neupanesushant.spotifyfrontendclone.databinding.LibraryOneElementRvCardBinding
import com.neupanesushant.spotifyfrontendclone.databinding.LibraryTwoElementRvCardBinding
import com.squareup.picasso.Picasso

class LibraryOneElementViewHolder(val binding: LibraryOneElementRvCardBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        item: DataLibraryContent,
        onLongClick: (DataLibraryContent, ImageView) -> Boolean,
        onClick: (DataLibraryContent, ImageView) -> Unit
    ) {
        if (item.imageString.isEmpty()) {
            binding.ivLibraryContentImage.setImageResource(R.drawable.default_card_background)
        } else {
            Picasso.get().load(item.imageString).fit().centerCrop()
                .placeholder(R.drawable.default_card_background)
                .error(R.drawable.default_card_background).into(binding.ivLibraryContentImage)
        }
        binding.tvContentTitle.text = item.title
        binding.tvContentDesc.text = item.description
        if (!item.isPlaylist) {
            binding.cvLibraryContentImageCard.setBackgroundResource(R.drawable.default_card_background)
        }
        itemView.setOnLongClickListener {
            clickedLibraryObject = item
            onLongClick(item, binding.ivLibraryContentImage)
        }
        itemView.setOnClickListener {
            clickedLibraryObject = item
            onClick(item, binding.ivLibraryContentImage)
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






