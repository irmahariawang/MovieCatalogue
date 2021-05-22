package com.sekarlangitstudio.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.sekarlangitstudio.moviecatalogue.data.source.local.entity.MovieEntity
import com.sekarlangitstudio.moviecatalogue.data.source.local.entity.TelevisionEntity

interface MovieCatalogueDataSource {

    fun getAllMovies(): LiveData<List<MovieEntity>>
    fun getAllTelevisions(): LiveData<List<TelevisionEntity>>
    fun getDetailMovie(extraID: String): LiveData<MovieEntity>
    fun getDetailTv(extraID: String): LiveData<TelevisionEntity>
}