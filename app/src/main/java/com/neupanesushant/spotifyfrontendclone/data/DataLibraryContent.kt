package com.neupanesushant.spotifyfrontendclone.data

data class DataLibraryContent(
    val imageString: String,
    val title: String,
    val isPlaylist: Boolean,
    val artistName: Array<String>

){

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

}