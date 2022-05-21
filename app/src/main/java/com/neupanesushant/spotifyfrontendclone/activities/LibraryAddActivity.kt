package com.neupanesushant.spotifyfrontendclone.activities

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.widget.addTextChangedListener
import com.neupanesushant.spotifyfrontendclone.data.DataLibraryContent
import com.neupanesushant.spotifyfrontendclone.dataLibraryContentList
import com.neupanesushant.spotifyfrontendclone.databinding.ActivityLibraryAddBinding

class LibraryAddActivity : AppCompatActivity() {

    private lateinit var binding : ActivityLibraryAddBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLibraryAddBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setActionText()
        actionTextClickListener()
        cancleClickListener()
       binding.etPlaylistNameInput.requestFocus()

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

    @SuppressLint("NotifyDataSetChanged")
    fun actionTextClickListener(){
        binding.tvAction.setOnClickListener {
            val imageString = ""
            val isPlaylist = true
            val artist = arrayOf("UserName")
            val title : String
            if(binding.etPlaylistNameInput.text.length != 0){
                title = binding.etPlaylistNameInput.text.toString()
            }else{
                val playlistNumber = dataLibraryContentList.size + 1
                title = "My Playlist #$playlistNumber"
            }
            dataLibraryContentList.add(DataLibraryContent(imageString, title, isPlaylist, artist))
            finish()

        }
    }

    fun cancleClickListener(){
        binding.tvCancle.setOnClickListener {
            finish()
        }
    }

}