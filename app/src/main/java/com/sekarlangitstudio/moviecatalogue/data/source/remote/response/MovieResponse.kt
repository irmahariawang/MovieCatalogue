package com.sekarlangitstudio.moviecatalogue.data.source.remote.response

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResponse(
    var movieId: String,
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