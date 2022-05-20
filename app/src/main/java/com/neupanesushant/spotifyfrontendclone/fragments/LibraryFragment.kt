package com.neupanesushant.spotifyfrontendclone.fragments


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.neupanesushant.spotifyfrontendclone.*
import com.neupanesushant.spotifyfrontendclone.adapters.LibraryContentAdapter
import com.neupanesushant.spotifyfrontendclone.data.DataLibraryContent
import com.neupanesushant.spotifyfrontendclone.databinding.FragmentLibraryBinding
import com.neupanesushant.spotifyfrontendclone.databinding.LibraryBottomDialogSheetBinding
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList


class LibraryFragment : Fragment() {

    private var _binding: FragmentLibraryBinding? = null
    private val binding get() = _binding!!
    private val librarySearchFragment = LibrarySearchFragment()



    private lateinit var bottomSheetBinding : LibraryBottomDialogSheetBinding
    private lateinit var bottomDialogSheet : BottomSheetDialog

    lateinit var currentSortingLayout : LibrarySortSetting
    lateinit var previousSortingLayout : LibrarySortSetting
    private var isList = false
    lateinit var adapter : LibraryContentAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLibraryBinding.inflate(layoutInflater)
        bottomSheetBinding = LibraryBottomDialogSheetBinding.inflate(layoutInflater)
        bottomDialogSheet = BottomSheetDialog(requireContext())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        listOfLibraryContent()

        setHeaderProfileImage("https://lh3.googleusercontent.com/Jwp7SwoCA-Eoh1m99PpbpA58GbmgbCU-JPZbF_lJnfyxv9jQIA0aiHMFbizvLP3gc9i207bZ5qwvd82i6cnHXxZHM3zdKO1BpXX6RR6uEpylst1TjjTTR-VykpJ-2na4S829lpDW3LOkVyKo1mIPbI1j1-1dFNBcdmWZ60zaZWbkbMEVFskCkQsT4eqba0lSB_zDimrGPf5i3cN_i4kMwH3JgSK84xgPW2tCn5iwq4fYHkbD3ppyFOtCZacUAOcfeUMdKC8Ny9SIRW7XfSJAlHJmPKHruhI32i_JLZY4mxOd9UtbfoELMh4AlUO0iAec-hIpDpb3vY38KFHDhAu54qC5vJG0pi8I0F6Mw52z2nFnS3TWfr3qXj4SkIz1UzrSZHLtlgdSPdXSvRcRUOyxZk2nvaG4-gj_AbUjoFWExwYYWJmAg127IjL8Pnuf8-nZkyyewjAeY9bVRAPLSjnF-KEPfRUpRjSqzuiyIznziD_8IjHKD0QugpYtrgdflrmO2rKBeaqy3mjw35lOj1UWzM3X-MVGkIUqoTarFW-uaduIilyM1c31GH8M1Qh6F5nCGittYP2nVJZC9Gtjp79ie4NWZTnVmDd1dgp_JpZO4wzuOodbYs0H__9kMDqKNjwOyaBJhjY3sdTYvLTPdSZ1mqq8N2iIURpJcDmUJVuPkm0QhTOtOoNkU5oCZHjpPXyz8IFWcgc0-fl1xDBwYTrWbd9k31REHW7S-Lh9ToX3LqPXL1hIbCd3=w165-h220-no?authuser=0")
        setHeaderAddClickListener()
        changeListViewListener()
        sortingOrderClickListener()

        setRecyclerViewAtStart(isList)
        setBottomDialogSheetAppearanceAtStart(currentSortingLayout.textvalue)
        replaceFragmentToLibrarySearch()

    }

    override fun onResume() {
        super.onResume()
        adapter.notifyDataSetChanged()
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
        binding.ivAddLibraryItems.setOnClickListener{
            val intent = Intent(requireContext(), LibraryAddActivity::class.java)
            startActivity(intent)

        }
    }

    fun setHeaderSearchClickListener() {

    }

    fun changeListViewListener(){
        setupTwoElementRecyclerView(dataLibraryContentList)
        binding.ivChangeListView.setOnClickListener{
            binding.ivChangeListView.startAnimation(AnimationUtils.loadAnimation(
                requireContext(),
                androidx.appcompat.R.anim.abc_fade_in,
            ))
            if(isList){
                setupTwoElementRecyclerView(dataLibraryContentList)
            }else{
                setupOneElementRecyclerView(dataLibraryContentList)
            }
        }
    }

    fun setRecyclerViewWithSortedList(list : ArrayList<DataLibraryContent>){
        if(isList){
            setupOneElementRecyclerView(list)
        }else{
            setupTwoElementRecyclerView(list)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setupOneElementRecyclerView(list : ArrayList<DataLibraryContent>){

        binding.rvLibraryContents.startAnimation(AnimationUtils.loadAnimation(
            requireContext(),
            androidx.appcompat.R.anim.abc_slide_in_bottom
        ))

        adapter = LibraryContentAdapter(true, list)
        binding.rvLibraryContents.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvLibraryContents.adapter = adapter
        adapter.notifyDataSetChanged()
        isList = true
        binding.ivChangeListView.setImageResource(R.drawable.ic_grid_view)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setupTwoElementRecyclerView(list : ArrayList<DataLibraryContent>){

        binding.rvLibraryContents.startAnimation(AnimationUtils.loadAnimation(
            requireContext(),
            androidx.appcompat.R.anim.abc_grow_fade_in_from_bottom
        ))

        adapter = LibraryContentAdapter(false, list)
        binding.rvLibraryContents.layoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
        binding.rvLibraryContents.adapter = adapter
        adapter.notifyDataSetChanged()
        isList = false
        binding.ivChangeListView.setImageResource(R.drawable.ic_bullet_list)
    }

    fun sortingOrderClickListener(){
        binding.relativeLayoutSortingOrder.setOnClickListener{
            showBottomDialogSheet()
        }
    }

    fun showBottomDialogSheet(){
        bottomDialogSheet.setContentView(bottomSheetBinding.root)
        bottomSheetBinding.rlMostRecent.setOnClickListener{
            rlMostRecentClickAction()
        }
        bottomSheetBinding.rlAlphabetical.setOnClickListener{
            rlAlphabeticalClickAction()
        }
        bottomSheetBinding.rlCreator.setOnClickListener{
            rlCreatorClickAction()
        }
        bottomSheetBinding.rlRecentlyAdded.setOnClickListener{
            rlRecentlyAddedClickAction()
        }
        bottomSheetBinding.rlRecentlyPlayed.setOnClickListener{
            rlRecentlyPlayedClickAction()

        }
        bottomSheetBinding.tvCancle.setOnClickListener{
            bottomDialogSheet.dismiss()
        }

        bottomDialogSheet.show()
    }

    fun setFromShowBottomDialogSheet(){
        previousSortingLayout.imageView.visibility = View.INVISIBLE
        previousSortingLayout.textView.setTextColor(resources.getColor(R.color.white))
        currentSortingLayout.imageView.visibility = View.VISIBLE
        currentSortingLayout.textView.setTextColor(resources.getColor(R.color.secondary))
        binding.tvSortText.text = currentSortingLayout.textvalue
    }

    fun rlMostRecentClickAction(){
        currentSortingLayout = LibrarySortSetting(bottomSheetBinding.rlMostRecent, bottomSheetBinding.tvMostRecent, bottomSheetBinding.ivMostRecent, "Most Recent", dataLibraryContentList)
        setFromShowBottomDialogSheet()
        previousSortingLayout = currentSortingLayout
        setRecyclerViewWithSortedList(dataLibraryContentList)
    }
    fun rlAlphabeticalClickAction(){
        currentSortingLayout = LibrarySortSetting(bottomSheetBinding.rlAlphabetical, bottomSheetBinding.tvAlphabetical, bottomSheetBinding.ivAlphabetical, "Alphabetical", sortLibraryContentWithTitle())
        setFromShowBottomDialogSheet()
        previousSortingLayout = currentSortingLayout
        setRecyclerViewWithSortedList(sortLibraryContentWithTitle())
    }
    fun rlCreatorClickAction(){
        currentSortingLayout = LibrarySortSetting(bottomSheetBinding.rlCreator, bottomSheetBinding.tvCreator, bottomSheetBinding.ivCreator, "Creator", sortLibraryContentWithCreator())
        setFromShowBottomDialogSheet()
        previousSortingLayout = currentSortingLayout
        setRecyclerViewWithSortedList(sortLibraryContentWithCreator())
    }
    fun rlRecentlyAddedClickAction(){
        currentSortingLayout = LibrarySortSetting(bottomSheetBinding.rlRecentlyAdded, bottomSheetBinding.tvRecentlyAdded, bottomSheetBinding.ivRecentlyAdded, "Recently Added", sortLibraryContentWithRecentlyAdded())
        setFromShowBottomDialogSheet()
        previousSortingLayout = currentSortingLayout
    }
    fun rlRecentlyPlayedClickAction(){
        currentSortingLayout = LibrarySortSetting(bottomSheetBinding.rlRecentlyPlayed, bottomSheetBinding.tvRecentlyPlayed, bottomSheetBinding.ivRecentlyPlayed, "Recently Played", sortLibraryContentWithRecentlyPlayed())
        setFromShowBottomDialogSheet()
        previousSortingLayout = currentSortingLayout
    }

    fun setBottomDialogSheetAppearanceAtStart(string : String){
        when(string){
            "Most Recent" -> rlMostRecentClickAction()
            "Alphabetical" -> rlAlphabeticalClickAction()
            "Creator" -> rlCreatorClickAction()
            "Recently Played" -> rlRecentlyPlayedClickAction()
            "Recently Added" -> rlRecentlyAddedClickAction()
        }
    }

    fun setRecyclerViewAtStart(isList : Boolean ){
        if(isList){
            setupOneElementRecyclerView(currentSortingLayout.dataList)
        }else{
            setupTwoElementRecyclerView(currentSortingLayout.dataList)
        }
    }

    private fun replaceFragmentToLibrarySearch() {
        binding.ivSearchLibraryItems.setOnClickListener{
            val fragmentTransaction = parentFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.fragment_container, librarySearchFragment)
            fragmentTransaction.isAddToBackStackAllowed
            fragmentTransaction.addToBackStack(null)
            fragmentTransaction.commit()
        }
    }

    fun sortLibraryContentWithTitle() : ArrayList<DataLibraryContent> {
        val tempList = dataLibraryContentList
        for(i in 0 until tempList.size - 1){
            for (j in i until tempList.size){
                if(tempList[i].title.compareTo(tempList[j].title) >= 0){
                    val temp = tempList[i]
                    tempList[i] = tempList[j]
                    tempList[j] = temp
                }
            }
        }
        return tempList
    }

    fun sortLibraryContentWithCreator() : ArrayList<DataLibraryContent>{
        val tempList = dataLibraryContentList
        for(i in 0 until tempList.size - 1){
            for(j in i until tempList.size){
                if(tempList[i].artistName[0].compareTo(tempList[j].artistName[0]) >= 0 ){
                    val temp = tempList[i]
                    tempList[i] = tempList[j]
                    tempList[j] = temp
                }
            }
        }
        return tempList
    }

    fun sortLibraryContentWithRecentlyPlayed() : ArrayList<DataLibraryContent>{
        val tempList = dataLibraryContentList
        return tempList
    }
    fun sortLibraryContentWithRecentlyAdded() : ArrayList<DataLibraryContent>{
        val tempList = dataLibraryContentList
        return tempList
    }

    fun getLibraryContentListSize() : Int{
        return dataLibraryContentList.size
    }
    fun addElementToLibraryContentList(element : DataLibraryContent) {
        dataLibraryContentList.add(element)
    }

    fun listOfLibraryContent(){
        dataLibraryContentList.clear()
        dataLibraryContentList.apply {
            add(
                DataLibraryContent(
                    "https://images.pexels.com/photos/9821104/pexels-photo-9821104.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500",
                    "Dhoti ko geet",
                    false,
                    arrayOf("Sushant Neupane")
                )
            )

            add(
                DataLibraryContent(
                    "https://images.pexels.com/photos/11642205/pexels-photo-11642205.jpeg?auto=compress&cs=tinysrgb&dpr=2&w=500",
                    "Playlist 1",
                    true,
                    arrayOf("Yogesh Bhatta")
                )
            )

            add(
                DataLibraryContent(
                    "https://images.pexels.com/photos/167092/pexels-photo-167092.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                    "Music",
                    true,
                    arrayOf("Utsab Sapkota", "Yogesh Bhatta")
                )
            )

            add(
                DataLibraryContent(
                    "https://images.pexels.com/photos/191240/pexels-photo-191240.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                    "Piano Vibes",
                    true,
                    arrayOf("Sushant Neupane")
                )
            )

            add(
                DataLibraryContent(
                    "https://images.pexels.com/photos/352505/pexels-photo-352505.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                    "Talk Your Life",
                    false,
                    arrayOf("Cristiano Ronaldo", "Lionel Messi")
                )
            )

            add(
                DataLibraryContent(
                    "https://images.pexels.com/photos/2104882/pexels-photo-2104882.jpeg?auto=compress&cs=tinysrgb&w=1260&h=750&dpr=2",
                    "Nepali Songs",
                    true,
                    arrayOf("Bimal Ranabhat")
                )
            )

            add(
                DataLibraryContent(
                    "",
                    "Empty",
                    true,
                    arrayOf("Empty")
                )
            )

        }

        }
    }
