package com.neupanesushant.spotifyfrontendclone.fragments

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.media.Image
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.graphics.drawable.DrawableCompat
import androidx.core.graphics.toColor
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.neupanesushant.spotifyfrontendclone.R
import com.neupanesushant.spotifyfrontendclone.adapters.LibraryContentAdapter
import com.neupanesushant.spotifyfrontendclone.data.DataLibraryContent
import com.neupanesushant.spotifyfrontendclone.databinding.FragmentLibraryBinding
import com.neupanesushant.spotifyfrontendclone.databinding.LibraryBottomDialogSheetBinding
import com.squareup.picasso.Picasso


class LibraryFragment : Fragment() {

    private var _binding: FragmentLibraryBinding? = null
    private val binding get() = _binding!!

    private var isList : Boolean = true
    private lateinit var bottomSheetBinding : LibraryBottomDialogSheetBinding
    private lateinit var bottomDialogSheet : BottomSheetDialog
    private val dataLibraryContentList = ArrayList<DataLibraryContent>()

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

        listOfLibraryContent()
        setHeaderProfileImage("https://lh3.googleusercontent.com/UrNljYq0p0PAZovQLACI6N4ExZ68Vp52OgVo1XpHsjl1fqFrAnliSgxWoKQDnB0w60cbH39HS0x2oLTTPAr7WNc80NT2P1NIAqS1v_KVo4svCs0jpc74RakjJbV8oX35lY0dUzxn4qYZ7sJIeU7rRBeNHNctrXpgSoF2RbG-aZ1BiPl95Nvdis1221svrOXjk_n-fK7plebUNjggr6W7j9bhc-JgOMuhaTyasnSeye7sr8oDxxbv_9WHFCWvGE7Go6ZUrakt5dr9RGoVd-h4xdEVcKVYIX1Q6QfPcvkyiwTWSUjbtdEZbcC7QJbDryzjScGsfmACcZTdtK8Bzw8PB8MOKtBVuTfi1x0i7Klixjxqot6yeME5yeBtHtvEulK0XY62RIfONlvo-mbH99Xi_DG_XXTB5Mbb-D6fLqD7KeCPvQGgIwFkC1vxZ-76UCuZxdT_FZ3kXTZniA__D9JQWu2rzhpIv7kGn4xUF71JHwpG8VmA8pQ-h4_E0os3W3GnaNWsIO4n2CGGUGOqrsl3Do1a1ME10ShXrTFJxIbYtbbBIpuRBO2g56ZUIntNqPZLXapUCwiKtb54EWPSnaw0IOSz5PtWMhgS6v7lEZIvQSuUB8JabBdV0rOEwLBzTc0QrQ8vaGBiiDMSBagqoRuo_4ih4NeCyrn7LMJfDNKgmKkNQpJsoVHamjfSxj-34F7ZLbH4W92mwqOYPFLyRM3-usXHEb7E1lW4_btRiIbms2vsNT1LbsvcuUA=w165-h220-no?authuser=0")
        changeListViewListener()
        sortingOrderClickListener()
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
        setupTwoElementRecyclerView(dataLibraryContentList)
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
                setupTwoElementRecyclerView(dataLibraryContentList)
            }else{
                binding.rvLibraryContents.startAnimation(AnimationUtils.loadAnimation(
                    requireContext(),
                    androidx.appcompat.R.anim.abc_slide_in_bottom
                ))
                setupOneElementRecyclerView(dataLibraryContentList)
            }
        }
    }

    fun setupOneElementRecyclerView(list : ArrayList<DataLibraryContent>){
        val adapter = LibraryContentAdapter(true, list)
        binding.rvLibraryContents.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        binding.rvLibraryContents.adapter = adapter

        isList = true
        binding.ivChangeListView.setImageResource(R.drawable.ic_grid_view)
    }

    fun setupTwoElementRecyclerView(list : ArrayList<DataLibraryContent>){
        val adapter = LibraryContentAdapter(false, list)
        binding.rvLibraryContents.layoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
        binding.rvLibraryContents.adapter = adapter
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
            bottomSheetBinding.tvMostRecent.setTextColor(resources.getColor(R.color.secondary))
            bottomSheetBinding.ivMostRecent.visibility = View.VISIBLE
            binding.tvSortText.setText(R.string.most_recent)
        }
        bottomSheetBinding.tvCancle.setOnClickListener{
            bottomDialogSheet.dismiss()
        }
        bottomDialogSheet.show()
    }

    fun listOfLibraryContent(){
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

        }

        }
    }
