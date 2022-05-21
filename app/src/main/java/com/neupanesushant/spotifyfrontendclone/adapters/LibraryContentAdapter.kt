package com.neupanesushant.spotifyfrontendclone.adapters

import android.content.Context
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neupanesushant.spotifyfrontendclone.activities.LibraryAddActivity
import com.neupanesushant.spotifyfrontendclone.activities.LibraryContentOptionsActivity
import com.neupanesushant.spotifyfrontendclone.clickedLibraryObject
import com.neupanesushant.spotifyfrontendclone.data.DataLibraryContent
import com.neupanesushant.spotifyfrontendclone.databinding.ActivityLibraryContentOptionsBinding
import kotlinx.coroutines.NonDisposableHandle.parent
import java.util.ArrayList

class LibraryContentAdapter(private val context : Context, private val onLongClick: (DataLibraryContent) -> Boolean,private val isOneElement : Boolean = false, private val list : ArrayList<DataLibraryContent>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(isOneElement)
            return LibraryOneElementViewHolder.from(parent)
        else
            return LibraryTwoElementViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is LibraryOneElementViewHolder -> {
                holder.bind(list.get(position))
            }
            is LibraryTwoElementViewHolder -> {
                holder.bind(list.get(position))
            }
        }
        holder.itemView.setOnLongClickListener{
            clickedLibraryObject = list.get(position)
            onLongClick(list.get(position))
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


}





