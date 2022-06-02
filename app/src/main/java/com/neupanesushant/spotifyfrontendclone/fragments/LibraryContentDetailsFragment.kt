package com.neupanesushant.spotifyfrontendclone.fragments

import android.graphics.Color
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
import com.neupanesushant.spotifyfrontendclone.listOfColors
import com.squareup.picasso.Picasso

class LibraryContentDetailsFragment : Fragment() {

    private lateinit var _binding: FragmentLibraryContentDetailsBinding
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


    fun setValuesInUI() {

        val artistName : String
        val randomNumberForColor = listOfColors.indices.random()
        val totalTimeInSeconds = getTotalPlaylistTime()
        val timeString : String

        binding.btnBack.setOnClickListener(View.OnClickListener {
            parentFragmentManager.popBackStack()
        })

        if (clickedLibraryObject.imageString.isEmpty()) {
            binding.ivContentImage.setImageResource(R.drawable.card_background_gradient_green)
        } else {
            Picasso.get().load(clickedLibraryObject.imageString).centerCrop().fit()
                .placeholder(R.drawable.default_card_background)
                .error(R.drawable.bottom_navigation_background).into(binding.ivContentImage)
        }

        binding.tvPlaylistName.text = clickedLibraryObject.title

        if(!clickedLibraryObject.artistName.isEmpty()) {
            artistName = clickedLibraryObject.artistName.get(0)
            binding.tvCreatorInitial.text = artistName[0].toString()
        }else{
            artistName = "No Name"
            binding.tvCreatorInitial.text = "*"
        }
        binding.cardViewInitialHolder.setCardBackgroundColor(listOfColors.get(randomNumberForColor))
        binding.tvCreatorName.text = artistName
        if(clickedLibraryObject.isPlaylist){
            if(timeToHours(totalTimeInSeconds) > 0 )
                timeString = "${timeToHours(totalTimeInSeconds)} hours ${timeToMinutes(totalTimeInSeconds)} min"
            else if(timeToMinutes(totalTimeInSeconds) > 0)
                timeString = "${timeToMinutes(totalTimeInSeconds)} min"
            else
                timeString = "$totalTimeInSeconds sec"
        }else{
            timeString = "Podcast"
        }
        binding.tvTotalTime.text = timeString

    }
    fun timeToHours(timeInSeconds : Int) : Int = timeInSeconds / 3600
    fun timeToMinutes(timeInSeconds: Int) : Int = timeInSeconds / 60
    fun secondsRemaining(timeInSeconds: Int) = timeInSeconds % timeToMinutes(timeInSeconds)
    fun getTotalPlaylistTime() : Int {
        var totalTime = 0
        clickedLibraryObject.songsList.forEach{
            totalTime += it.timeInSeconds
        }
        return totalTime
    }

}


