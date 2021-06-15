package com.sekarlangitstudio.moviecatalogue.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "televisionentities")
data class TelevisionEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "televisionId")
    var televisionId: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "description")
    var description: String,

    @ColumnInfo(name = "airingDate")
    var airingDate: String,

    @ColumnInfo(name = "score")
    var score: String,

    @ColumnInfo(name = "genre")
    var genre: String,

    @ColumnInfo(name = "duration")
    var duration: String,

    @ColumnInfo(name = "director")
    var director: String,

    @ColumnInfo(name = "casting")
    var casting: String,

    @ColumnInfo(name = "favorite")
    var favorite: Boolean = false,

    @ColumnInfo(name = "imagePath")
    var imagePath: String
)