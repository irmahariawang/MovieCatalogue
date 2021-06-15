package com.sekarlangitstudio.moviecatalogue.ui.television

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sekarlangitstudio.moviecatalogue.data.source.MovieCatalogueRepository
import com.sekarlangitstudio.moviecatalogue.data.source.local.entity.TelevisionEntity
import com.sekarlangitstudio.moviecatalogue.vo.Resource

class TelevisionViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) :
    ViewModel() {
    fun getTelevisions(): LiveData<Resource<List<TelevisionEntity>>> =
        movieCatalogueRepository.getAllTelevisions()
}