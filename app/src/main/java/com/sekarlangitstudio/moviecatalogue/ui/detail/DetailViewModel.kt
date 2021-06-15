package com.sekarlangitstudio.moviecatalogue.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sekarlangitstudio.moviecatalogue.data.source.MovieCatalogueRepository
import com.sekarlangitstudio.moviecatalogue.data.source.local.entity.MovieEntity
import com.sekarlangitstudio.moviecatalogue.data.source.local.entity.TelevisionEntity


class DetailViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) :
    ViewModel() {
    //private lateinit var extraID: String
    val extraID = MutableLiveData<String>()

    fun setSelectedId(extraId: String) {
        this.extraID.value = extraId
    }

    fun getDetailMovie(): LiveData<MovieEntity> =
        movieCatalogueRepository.getDetailMovie(extraID.value!!)

    fun getDetailTv(): LiveData<TelevisionEntity> =
        movieCatalogueRepository.getDetailTv(extraID.value!!)

}