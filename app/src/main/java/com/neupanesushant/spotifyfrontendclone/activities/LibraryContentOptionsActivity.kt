package com.neupanesushant.spotifyfrontendclone.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.neupanesushant.spotifyfrontendclone.clickedLibraryObject
import com.neupanesushant.spotifyfrontendclone.databinding.ActivityLibraryContentOptionsBinding

class LibraryContentOptionsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLibraryContentOptionsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLibraryContentOptionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvSomething.text = clickedLibraryObject.title
        binding.linearLayoutContentOptionsPage.setOnClickListener{
            finish()
        }

    }
}