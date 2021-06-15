package com.sekarlangitstudio.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import com.sekarlangitstudio.moviecatalogue.data.source.local.entity.MovieEntity
import com.sekarlangitstudio.moviecatalogue.data.source.local.entity.TelevisionEntity
import com.sekarlangitstudio.moviecatalogue.vo.Resource

interface MovieCatalogueDataSource {

    fun getAllMovies(): LiveData<Resource<List<MovieEntity>>>
    fun getAllTelevisions(): LiveData<Resource<List<TelevisionEntity>>>
    fun getDetailMovie(movieID: String): LiveData<MovieEntity>
    fun getDetailTv(televisionID: String): LiveData<TelevisionEntity>
    fun getFavoriteMovie(): LiveData<List<MovieEntity>>
    fun getFavoriteTv(): LiveData<List<TelevisionEntity>>
    fun setMovieFavorite(movie: MovieEntity, state: Boolean)
    fun setTvFavorite(tv: TelevisionEntity, state: Boolean)
}