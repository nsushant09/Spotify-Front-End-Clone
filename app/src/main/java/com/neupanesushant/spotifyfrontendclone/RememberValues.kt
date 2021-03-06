package com.neupanesushant.spotifyfrontendclone

import android.graphics.Color
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.neupanesushant.spotifyfrontendclone.data.DataLibraryContent
import com.neupanesushant.spotifyfrontendclone.data.Song
import com.neupanesushant.spotifyfrontendclone.fragments.LibraryFragment



data class LibrarySortSetting(val relativeLayout: RelativeLayout, val textView: TextView, val imageView : ImageView, val textvalue : String, val dataList : ArrayList<DataLibraryContent>)
val dataLibraryContentList = ArrayList<DataLibraryContent>()
val listOfColors : List<Int> = listOf( Color.BLUE,Color.GRAY, Color.RED, Color.MAGENTA)
lateinit var clickedLibraryObject : DataLibraryContent

fun listOfLibraryContent(){
    dataLibraryContentList.clear()
    dataLibraryContentList.apply {
        add(
            DataLibraryContent(
                "https://images.pexels.com/photos/9821104/pexels-photo-9821104.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500",
                "Dhoti ko geet",
                false,
                arrayListOf(),
                arrayOf("Sushant Neupane")
            )
        )

        add(
            DataLibraryContent(
                "https://images.pexels.com/photos/11642205/pexels-photo-11642205.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500",
                "Playlist 1",
                true,
                arrayListOf(Song("", 250, "Rock", "Pramod Kharel"), Song("", 254, "HipHop", "Yogesh Bhatta")),
                arrayOf("Yogesh Bhatta")
            )
        )

        add(
            DataLibraryContent(
                "https://images.pexels.com/photos/167092/pexels-photo-167092.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                "Music",
                true,
                arrayListOf(Song("", 222, "Pop", "Raju Lama"), Song("", 362, "Folk", "Utsab Sapkota")),
                arrayOf("Utsab Sapkota", "Yogesh Bhatta")
            )
        )

        add(
            DataLibraryContent(
                "https://images.pexels.com/photos/191240/pexels-photo-191240.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                "Piano Vibes",
                true,
                arrayListOf(Song("", 314, "Cultural", "Amber Gurung"), Song("", 241, "Cultural", "Pratyush Adhikari")),
                arrayOf("Sushant Neupane")
            )
        )

        add(
            DataLibraryContent(
                "https://images.pexels.com/photos/352505/pexels-photo-352505.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                "Talk Your Life",
                false,
                arrayListOf(),
                arrayOf("Cristiano Ronaldo", "Lionel Messi")
            )
        )

        add(
            DataLibraryContent(
                "https://images.pexels.com/photos/2104882/pexels-photo-2104882.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                "Nepali Songs",
                true,
                arrayListOf(Song("", 643, "Nostalgic", "Narayan Gopal"), Song("", 432, "R&B", "Suprit Gautam")),
                arrayOf("Bimal Ranabhat")
            )
        )

        add(
            DataLibraryContent(
                "",
                "Empty",
                true,
                arrayListOf(),
                arrayOf("Empty")
            )
        )

    }

}
