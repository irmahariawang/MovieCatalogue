package com.sekarlangitstudio.moviecatalogue.ui.favorite.favtv

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sekarlangitstudio.moviecatalogue.data.source.MovieCatalogueRepository
import com.sekarlangitstudio.moviecatalogue.data.source.local.entity.TelevisionEntity

class FavTvViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) : ViewModel() {
    fun getFavoriteTv(): LiveData<List<TelevisionEntity>> = movieCatalogueRepository.getFavoriteTv()
}