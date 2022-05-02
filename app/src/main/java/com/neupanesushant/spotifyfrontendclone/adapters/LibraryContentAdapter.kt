package com.neupanesushant.spotifyfrontendclone.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neupanesushant.spotifyfrontendclone.data.DataLibraryContent

class LibraryContentAdapter(private val isOneElement : Boolean = false, private val list : ArrayList<DataLibraryContent>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if(isOneElement)
            return LibraryOneElementViewHolder.from(parent)
        else
            return LibraryTwoElementViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder){
            is LibraryOneElementViewHolder -> {
                holder.bind(list.get(position), position)
            }
            is LibraryTwoElementViewHolder -> {
                holder.bind(list.get(position), position)
            }

        }
    }

    override fun getItemCount(): Int {
        return list.size
    }


}