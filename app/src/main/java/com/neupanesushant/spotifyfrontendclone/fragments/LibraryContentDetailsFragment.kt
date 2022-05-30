package com.neupanesushant.spotifyfrontendclone.fragments

import android.os.Bundle
import android.transition.Transition
import android.transition.TransitionInflater
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import com.neupanesushant.spotifyfrontendclone.R
import com.neupanesushant.spotifyfrontendclone.clickedLibraryObject
import com.neupanesushant.spotifyfrontendclone.databinding.FragmentLibraryContentDetailsBinding
import com.squareup.picasso.Picasso

class LibraryContentDetailsFragment : Fragment() {

    private lateinit var _binding : FragmentLibraryContentDetailsBinding
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLibraryContentDetailsBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setValuesInUI()

    }


    fun setValuesInUI(){

        binding.btnBack.setOnClickListener(View.OnClickListener {
            parentFragmentManager.popBackStack()
        })

        if(clickedLibraryObject.imageString.isEmpty()){
            binding.ivContentImage.setImageResource(R.drawable.card_background_gradient_green)
        }else{
            Picasso.get().load(clickedLibraryObject.imageString).centerCrop().fit().placeholder(R.drawable.default_card_background).error(R.drawable.bottom_navigation_background).into(binding.ivContentImage)
        }
    }

}