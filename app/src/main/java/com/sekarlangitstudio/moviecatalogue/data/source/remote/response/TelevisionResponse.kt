package com.sekarlangitstudio.moviecatalogue.data.source.remote.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TelevisionResponse(
    var televisionId: String,
    var title: String,
    var description: String,
    var airingDate: String,
    var score: String,
    var genre: String,
    var duration: String,
    var director: String,
    var casting: String,
    var imagePath: String
) : Parcelable