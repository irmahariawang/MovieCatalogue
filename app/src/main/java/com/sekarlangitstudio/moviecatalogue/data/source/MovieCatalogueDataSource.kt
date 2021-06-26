package com.sekarlangitstudio.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.PagedList
import com.sekarlangitstudio.moviecatalogue.data.source.local.entity.MovieEntity
import com.sekarlangitstudio.moviecatalogue.data.source.local.entity.TelevisionEntity
import com.sekarlangitstudio.moviecatalogue.vo.Resource

interface MovieCatalogueDataSource {

    fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>>
    fun getAllTelevisions(): LiveData<Resource<PagedList<TelevisionEntity>>>
    fun getDetailMovie(movieID: String): LiveData<MovieEntity>
    fun getDetailTv(televisionID: String): LiveData<TelevisionEntity>
    fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>>
    fun getFavoriteTv(): LiveData<PagedList<TelevisionEntity>>
    fun setMovieFavorite(movie: MovieEntity, state: Boolean)
    fun setTvFavorite(tv: TelevisionEntity, state: Boolean)
}