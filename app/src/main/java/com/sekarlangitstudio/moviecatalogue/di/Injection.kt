package com.sekarlangitstudio.moviecatalogue.di

import android.content.Context
import com.sekarlangitstudio.moviecatalogue.data.source.MovieCatalogueRepository
import com.sekarlangitstudio.moviecatalogue.data.source.remote.RemoteDataSource
import com.sekarlangitstudio.moviecatalogue.utils.JsonHelper

object Injection {
    fun provideRepository(context: Context): MovieCatalogueRepository {
        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))

        return MovieCatalogueRepository.getInstance(remoteDataSource)
    }
}