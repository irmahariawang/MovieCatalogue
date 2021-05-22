package com.sekarlangitstudio.moviecatalogue.data.source.remote

import com.sekarlangitstudio.moviecatalogue.data.source.remote.response.MovieResponse
import com.sekarlangitstudio.moviecatalogue.data.source.remote.response.TelevisionResponse
import com.sekarlangitstudio.moviecatalogue.utils.EspressoIdlingResource
import com.sekarlangitstudio.moviecatalogue.utils.JsonHelper

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {
    private val handler = android.os.Handler()

    companion object {
        private const val SERVICE_LATENCY_IN_MILLIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper)
            }
    }

    fun getAllMovies(callback: LoadMoviesCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            callback.onAllMoviesReceived(jsonHelper.loadMovies())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    interface LoadMoviesCallback {
        fun onAllMoviesReceived(movieResponse: List<MovieResponse>)
    }

    fun getAllTelevisions(callback: LoadTvCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            callback.onAllTvReceived(jsonHelper.loadTelevisions())
            EspressoIdlingResource.decrement()
        }, SERVICE_LATENCY_IN_MILLIS)
    }

    interface LoadTvCallback {
        fun onAllTvReceived(televisionResponse: List<TelevisionResponse>)
    }
}