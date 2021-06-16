package com.sekarlangitstudio.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.sekarlangitstudio.moviecatalogue.data.source.MovieCatalogueRepository
import com.sekarlangitstudio.moviecatalogue.data.source.local.entity.MovieEntity
import com.sekarlangitstudio.moviecatalogue.data.source.local.entity.TelevisionEntity


class DetailViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) :
    ViewModel() {
    //private lateinit var extraID: String
    val extraID = MutableLiveData<String>()
    val favorited = MutableLiveData<Boolean>(false)


    fun setSelectedId(extraId: String) {
        this.extraID.value = extraId
    }

    var detailMovie: LiveData<MovieEntity> = Transformations.switchMap(extraID) { mMovieID ->
        movieCatalogueRepository.getDetailMovie(mMovieID)
    }

    var detailTv: LiveData<TelevisionEntity> = Transformations.switchMap(extraID) { mTvID ->
        movieCatalogueRepository.getDetailTv(mTvID)
    }

    fun setFavoriteMovie(movieEntity: MovieEntity) {
        val newState = !(movieEntity.favorite)
        movieCatalogueRepository.setMovieFavorite(movieEntity, newState)
    }

    fun setFavoriteTv(televisionEntity: TelevisionEntity) {
        val newState = !(televisionEntity.favorite)
        movieCatalogueRepository.setTvFavorite(televisionEntity, newState)
    }


    /*
    fun getDetailMovie(): LiveData<MovieEntity> =
        movieCatalogueRepository.getDetailMovie(extraID.value!!)

    fun getDetailTv(): LiveData<TelevisionEntity> =
        movieCatalogueRepository.getDetailTv(extraID.value!!)


     */
}