package com.neupanesushant.spotifyfrontendclone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.neupanesushant.spotifyfrontendclone.data.DataLibraryContent
import com.neupanesushant.spotifyfrontendclone.databinding.ActivityLibraryAddBinding
import com.neupanesushant.spotifyfrontendclone.fragments.LibraryFragment

class LibraryAddActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLibraryAddBinding
    private var libraryFragment = LibraryFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLibraryAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setActionText()
        actionTextClickListener()
        cancleClickListener()


    }

    fun setActionText() {
        binding.tvAction.text = "SKIP"
        binding.etPlaylistNameInput.addTextChangedListener {
            if(it==null || it.length == 0){
                binding.tvAction.text = "SKIP"
            }else{
                binding.tvAction.text = "CREATE"
            }
        }
    }

    fun actionTextClickListener(){
        binding.tvAction.setOnClickListener {
            val imageString = ""
            val isPlaylist = true
            val artist = arrayOf("UserName")
            val title : String
            if(binding.tvAction.text.length == 0){
                val playlistNumber = libraryFragment.getLibraryContentListSize() + 1
                title = "My Playlist #$playlistNumber"
            }else{
                title = binding.etPlaylistNameInput.text.toString()
            }
            libraryFragment.addElementToLibraryContentList(DataLibraryContent(imageString, title, isPlaylist, artist))
            finish()
        }
    }

    fun cancleClickListener(){
        binding.tvCancle.setOnClickListener {
            finish()
        }
    }
}