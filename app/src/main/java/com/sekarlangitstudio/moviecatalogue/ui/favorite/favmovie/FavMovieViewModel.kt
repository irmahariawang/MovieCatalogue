package com.sekarlangitstudio.moviecatalogue.ui.favorite.favmovie

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sekarlangitstudio.moviecatalogue.data.source.MovieCatalogueRepository
import com.sekarlangitstudio.moviecatalogue.data.source.local.entity.MovieEntity

class FavMovieViewModel(private val movieCatalogueRepository: MovieCatalogueRepository) :
    ViewModel() {
    fun getFavoriteMovie(): LiveData<List<MovieEntity>> =
        movieCatalogueRepository.getFavoriteMovie()
}