package com.neupanesushant.spotifyfrontendclone.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.graphics.toColor
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.neupanesushant.spotifyfrontendclone.R
import com.neupanesushant.spotifyfrontendclone.adapters.LibraryContentAdapter
import com.neupanesushant.spotifyfrontendclone.data.DataLibraryContent
import com.neupanesushant.spotifyfrontendclone.databinding.FragmentLibraryBinding
import com.squareup.picasso.Picasso


class LibraryFragment : Fragment() {

    private var _binding: FragmentLibraryBinding? = null
    private val binding get() = _binding!!

    private var isList : Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLibraryBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHeaderProfileImage("https://lh3.googleusercontent.com/OfLlVrDNXHmjV_i3beI5bajp1OcpmOx0XewXv6oM0TTQKdP2gvVbpYQ2homDEmcHJnmZp610lpwOiKBW_7zqxuiNXbooc_qvDc9F3e9qPwdeNaE_ABd2-bzKuafsfLx6jORcEigfQidaxVDT-HGDr7kknjlO0FB4tiIuN63cRGX4y6Sku30zFyqMvtR52A9xOcRli3h83_spStNQuGXpgIsENGWWi50a0mmHI8OITOuYWXe90hsF5OjqkNci4Qvbl0Mjn3LabPXh6kUAYS0bG3cweeuK2YCrmpk6LgBRUdF3KhRXNJeQG9-TCpLRZP-vSPl7-hr4b99EvVdMY1Zhe5kDhiq-3atpQAj2PeZWUe1R52Ot9sHdwcwqa3y2PtMBdxN3y5UPcSn0vIeoTu9m5GpMlzJ2c9RZx0PbqEAVlYe-hAO4TcP5byh8sWyCR_JOdlkd9tGxCGSS_YwjwB2aQHvDoAGM58Jy3eLO0CFKL5Hai9vHY05JaU4lyRvqn8cSAbfRWyoFyY-fDS2Ottq5OMjLeTivyWmNkDRRxWP7PyWvdyf8CxzlVuMWOtPSozggzb6WCQCtjUaXpAqvBB8SzG9mJrCVi3ImXsbPCxPs3ykTksSQO9F5d4yDzHJ7Be4RWYB4HYIW04wEHMUGarlmX84OhS4BB8STADsi8cUPOnRLvtF62o5_7SMrsSgYBNEsj1El0KdKMzoI8cvbRJUjZyR8BEEhYHgN2ow3lQdJChYpGn-uOVK2=w778-h1212-no?authuser=0")

        changeListViewListener()
    }

    fun setHeaderProfileImage(uri: String) {
        Picasso.get().load(uri).fit().centerCrop()
            .placeholder(R.drawable.ic_circle)
            .error(R.drawable.ic_circle)
            .into(binding.ivSearchProfileImage)
    }

    fun setHeaderProfileImageClickListener() {

    }

    fun setHeaderAddClickListener() {

    }

    fun setHeaderSearchClickListener() {

    }

    fun changeListViewListener(){
        setupTwoElementRecyclerView()
        binding.ivChangeListView.setOnClickListener{
            binding.ivChangeListView.startAnimation(AnimationUtils.loadAnimation(
                requireContext(),
                androidx.appcompat.R.anim.abc_fade_in,
            ))
            if(isList){
                binding.rvLibraryContents.startAnimation(AnimationUtils.loadAnimation(
                    requireContext(),
                    androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom
                ))
                setupTwoElementRecyclerView()
            }else{
                binding.rvLibraryContents.startAnimation(AnimationUtils.loadAnimation(
                    requireContext(),
                    androidx.appcompat.R.anim.abc_slide_in_bottom
                ))
                setupOneElementRecyclerView()
            }
        }
    }

    fun setupOneElementRecyclerView(){
        val adapter = LibraryContentAdapter(!isList, listOfLibraryContent())
        binding.rvLibraryContents.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvLibraryContents.adapter = adapter

        isList = true
        binding.ivChangeListView.setImageResource(R.drawable.ic_grid_view)
    }

    fun setupTwoElementRecyclerView(){
        val adapter = LibraryContentAdapter(!isList, listOfLibraryContent())
        binding.rvLibraryContents.layoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
        binding.rvLibraryContents.adapter = adapter
        isList = false
        binding.ivChangeListView.setImageResource(R.drawable.ic_bullet_list)
    }

    fun listOfLibraryContent(): ArrayList<DataLibraryContent> {
        val list = ArrayList<DataLibraryContent>()
        list.add(
            DataLibraryContent(
                "https://images.pexels.com/photos/9821104/pexels-photo-9821104.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500",
                "Dhoti ko geet",
                false,
                arrayOf("Sushant Neupane")
            )
        )
        list.add(
            DataLibraryContent(
                "https://images.pexels.com/photos/11642205/pexels-photo-11642205.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500",
                "Playlist 1",
                true,
                arrayOf("Yogesh Bhatta")
            )
        )
        return list
    }
}