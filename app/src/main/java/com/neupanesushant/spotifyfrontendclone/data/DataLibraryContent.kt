package com.neupanesushant.spotifyfrontendclone.data

import android.provider.ContactsContract

data class DataLibraryContent(
    val imageString: String,
    val title: String,
    val isPlaylist: Boolean,
    val artistName: Array<String>

){
    var isVisibleInProfile : Boolean
    var isPinnedInLibrary : Boolean
    init{
        isVisibleInProfile = true
        isPinnedInLibrary = false
    }
    var description: String = setDescription()
    fun setDescription(): String {
        val tempDesc: String
        var allArtist: String = ""
        for (name in artistName) {
            allArtist = "$allArtist $name •"
        }
        if (isPlaylist) {
            tempDesc = "Playlist • $allArtist"
        } else {
            tempDesc = "Podcast • $allArtist"
        }
        return tempDesc
    }

    fun changeIsVisibileInProfile(){
        isVisibleInProfile = !isVisibleInProfile
    }
    fun changeIsPinnedInLibrary(){
        isPinnedInLibrary = !isPinnedInLibrary
    }

    override fun equals(other: Any?): Boolean {
        if(other == null || other !is DataLibraryContent) return false
        return this.hashCode() == other.hashCode()
    }

    override fun hashCode(): Int {
        var result = imageString.hashCode()
        result = 31 * result + title.hashCode()
        result = 31 * result + isPlaylist.hashCode()
        result = 31 * result + artistName.contentHashCode()
        result = 31 * result + isVisibleInProfile.hashCode()
        result = 31 * result + isPinnedInLibrary.hashCode()
        result = 31 * result + description.hashCode()
        return result
    }


}