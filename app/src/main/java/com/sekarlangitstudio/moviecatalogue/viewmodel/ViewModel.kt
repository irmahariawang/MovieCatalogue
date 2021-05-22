package com.sekarlangitstudio.moviecatalogue.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sekarlangitstudio.moviecatalogue.data.source.MovieCatalogueRepository
import com.sekarlangitstudio.moviecatalogue.di.Injection
import com.sekarlangitstudio.moviecatalogue.ui.detail.DetailViewModel
import com.sekarlangitstudio.moviecatalogue.ui.movie.MovieViewModel
import com.sekarlangitstudio.moviecatalogue.ui.television.TelevisionViewModel

class ViewModelFactory private constructor(private val mMovieCatalogueRepository: MovieCatalogueRepository) :
    ViewModelProvider.NewInstanceFactory() {

    companion object {
        @Volatile
        private var instance: ViewModelFactory? = null

        fun getInstance(context: Context): ViewModelFactory =
            instance ?: synchronized(this) {
                instance ?: ViewModelFactory(Injection.provideRepository(context))
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        when {
            modelClass.isAssignableFrom(MovieViewModel::class.java) -> {
                return MovieViewModel(mMovieCatalogueRepository) as T
            }
            modelClass.isAssignableFrom(TelevisionViewModel::class.java) -> {
                return TelevisionViewModel(mMovieCatalogueRepository) as T
            }
            modelClass.isAssignableFrom(DetailViewModel::class.java) -> {
                return DetailViewModel(mMovieCatalogueRepository) as T
            }
            else -> throw Throwable("Unknown ViewModel class: " + modelClass.name)
        }
    }

}