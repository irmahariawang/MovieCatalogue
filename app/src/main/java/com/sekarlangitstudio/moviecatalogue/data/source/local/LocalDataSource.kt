package com.sekarlangitstudio.moviecatalogue.data.source.local

import androidx.lifecycle.LiveData
import com.sekarlangitstudio.moviecatalogue.data.source.local.entity.MovieEntity
import com.sekarlangitstudio.moviecatalogue.data.source.local.entity.TelevisionEntity
import com.sekarlangitstudio.moviecatalogue.data.source.local.room.MovieCatalogueDao

class LocalDataSource private constructor(private val mMovieCatDao: MovieCatalogueDao) {

    companion object {
        private var INSTANCE: LocalDataSource? = null

        fun getInstance(movieCatDao: MovieCatalogueDao): LocalDataSource =
            INSTANCE ?: LocalDataSource(movieCatDao)
    }

    fun getAllMovies(): LiveData<List<MovieEntity>> = mMovieCatDao.getMovies()

    fun getFavoriteMovie(): LiveData<List<MovieEntity>> = mMovieCatDao.getFavoriteMovie()

    fun getMovieDetail(movieId: String): LiveData<MovieEntity> =
        mMovieCatDao.getMovieById(movieId)

    fun setMovieFavorite(movie: MovieEntity, newState: Boolean) {
        movie.favorite = newState
        mMovieCatDao.updateMovie(movie)
    }

    fun insertMovies(movies: List<MovieEntity>) = mMovieCatDao.insertMovies(movies)

    fun getAllTelevisions(): LiveData<List<TelevisionEntity>> = mMovieCatDao.getTvs()

    fun getFavoriteTv(): LiveData<List<TelevisionEntity>> = mMovieCatDao.getFavoriteTv()

    fun getTvDetail(tvId: String): LiveData<TelevisionEntity> =
        mMovieCatDao.getTelevisionById(tvId)

    fun setTvFavorite(tv: TelevisionEntity, newState: Boolean) {
        tv.favorite = newState
        mMovieCatDao.updateTelevision(tv)
    }

    fun insertTvs(tvs: List<TelevisionEntity>) = mMovieCatDao.insertTvs(tvs)
}