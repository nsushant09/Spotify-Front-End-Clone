package com.neupanesushant.spotifyfrontendclone.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.neupanesushant.spotifyfrontendclone.R
import com.neupanesushant.spotifyfrontendclone.databinding.FragmentLibraryBinding


class LibraryFragment : Fragment() {

    private var _binding : FragmentLibraryBinding ?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLibraryBinding.inflate(layoutInflater)
        return binding.root
    }


}