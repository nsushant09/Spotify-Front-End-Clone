package com.neupanesushant.spotifyfrontendclone.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.PorterDuff
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.core.graphics.drawable.toBitmap
import com.neupanesushant.spotifyfrontendclone.R
import com.neupanesushant.spotifyfrontendclone.clickedLibraryObject
import com.neupanesushant.spotifyfrontendclone.data.DataLibraryContent
import com.neupanesushant.spotifyfrontendclone.dataLibraryContentList
import com.neupanesushant.spotifyfrontendclone.databinding.ActivityLibraryContentOptionsBinding
import com.squareup.picasso.Picasso
import java.io.ByteArrayOutputStream

class LibraryContentOptionsActivity : AppCompatActivity() {
    private lateinit var binding : ActivityLibraryContentOptionsBinding
    var indexOfObject : Int = indexOfObjectInList(clickedLibraryObject)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLibraryContentOptionsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.linearLayoutContentOptionsPage.setOnClickListener{
            supportFinishAfterTransition()
        }
        setValuesInUI()
        profileVisibilityClickListener()
        playlistPinnedClickListener()
        deleteClickListener()
        shareClickListener()
        binding.linearLayoutContentOptionsPageBottomSheet.startAnimation(AnimationUtils.loadAnimation(applicationContext,androidx.transition.R.anim.abc_slide_in_bottom))
    }

    fun setValuesInUI(){
        var text : String
        if(clickedLibraryObject.imageString.isEmpty()){
            binding.ivContentImage.setImageResource(R.drawable.card_background_gradient_green)
        }else{
            Picasso.get().load(clickedLibraryObject.imageString).centerCrop().fit().placeholder(R.drawable.default_card_background).error(R.drawable.bottom_navigation_background).into(binding.ivContentImage)
        }
        binding.tvContentTitle.text = clickedLibraryObject.title
        binding.tvContentCreator.text = clickedLibraryObject.artistName.get(0)

        if(clickedLibraryObject.isVisibleInProfile){
            binding.apply {
                ivProfileVisibility.setImageResource(R.drawable.ic_lock)
                text = "Remove from profile"
                tvProfileVisibility.text = text
            }
        }else{
            binding.apply {
                ivProfileVisibility.setImageResource(R.drawable.ic_web)
                text = "Add to profile"
                tvProfileVisibility.text = text
            }
        }

        if(clickedLibraryObject.isPinnedInLibrary){
           binding.apply {
               ivPlaylistPinned.setImageResource(R.drawable.ic_pin_green)
               text = "Unpin Playlist"
               tvPlaylistPinned.text = text
           }
        }else{
            binding.apply {
                ivPlaylistPinned.setImageResource(R.drawable.ic_pin)
                text = "Pin Playlist"
                tvPlaylistPinned.text = text
            }
        }

        binding.apply {
            ivDeletePlaylist.setImageResource(R.drawable.ic_cross)
            ivShare.setImageResource(R.drawable.ic_share_svgrepo_com)
            text = "Delete Playlist"
            tvDeletePlaylist.text = text
            text = "Share"
            tvShare.text = text
        }

    }

    fun profileVisibilityClickListener(){
        binding.rlProfileVisibiltiy.setOnClickListener{
            if(indexOfObject != -1){
                dataLibraryContentList[indexOfObject].changeIsVisibileInProfile()
                Toast.makeText(applicationContext, "Profile Visibility Changed ", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(applicationContext, "Error Occured", Toast.LENGTH_SHORT).show()
            }
            supportFinishAfterTransition()
        }
    }

    fun playlistPinnedClickListener(){
        binding.rlPlaylistPinned.setOnClickListener{
            if(indexOfObject != -1){
                dataLibraryContentList[indexOfObject].changeIsPinnedInLibrary()
            }else{
                Toast.makeText(applicationContext, "Error Occured", Toast.LENGTH_SHORT).show()
            }
            supportFinishAfterTransition()
        }
    }

    fun deleteClickListener(){
        binding.rlDeletePlaylist.setOnClickListener{
            if(indexOfObject != -1){
                dataLibraryContentList.removeAt(indexOfObject)
            }else{
                Toast.makeText(applicationContext, "Error Occured", Toast.LENGTH_SHORT).show()
            }
            supportFinishAfterTransition()
        }
    }

    fun shareClickListener(){
        binding.rlShare.setOnClickListener{
            // need to send link of the playlist insted an image is sent for now.
            if(indexOfObject != -1){
                val intent = Intent(Intent.ACTION_SEND).setType("image/*")
                val bitmap = binding.ivContentImage.drawable.toBitmap()
                val bytes = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
                val path = MediaStore.Images.Media.insertImage(contentResolver, bitmap, "tempimage", null)
                val uri = Uri.parse(path)
                intent.putExtra(Intent.EXTRA_STREAM, uri)
                startActivity(intent)

            }else{
                Toast.makeText(applicationContext, "Error Occured", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun indexOfObjectInList(target : DataLibraryContent) : Int{
        for(i in 0 until dataLibraryContentList.size){
            if(dataLibraryContentList[i] == target){
                return i
            }
        }
        return -1
    }
}