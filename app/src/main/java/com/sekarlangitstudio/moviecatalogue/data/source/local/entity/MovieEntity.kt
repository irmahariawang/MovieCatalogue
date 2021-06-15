package com.sekarlangitstudio.moviecatalogue.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movieentities")
data class MovieEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "movieId")
    val movieId: String,

    @ColumnInfo(name = "title")
    val title: String,

    @ColumnInfo(name = "description")
    val description: String,

    @ColumnInfo(name = "airingDate")
    val airingDate: String,

    @ColumnInfo(name = "score")
    val score: String,

    @ColumnInfo(name = "genre")
    val genre: String,

    @ColumnInfo(name = "duration")
    val duration: String,

    @ColumnInfo(name = "director")
    val director: String,

    @ColumnInfo(name = "casting")
    val casting: String,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false,

    @ColumnInfo(name = "imagePath")
    val imagePath: String
)