package com.sekarlangitstudio.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.sekarlangitstudio.moviecatalogue.data.source.local.entity.MovieEntity
import com.sekarlangitstudio.moviecatalogue.data.source.local.entity.TelevisionEntity
import com.sekarlangitstudio.moviecatalogue.data.source.remote.RemoteDataSource
import com.sekarlangitstudio.moviecatalogue.data.source.remote.response.MovieResponse
import com.sekarlangitstudio.moviecatalogue.data.source.remote.response.TelevisionResponse

class FakeMovieCatalogueRepository(private val remoteDataSource: RemoteDataSource) :
    MovieCatalogueDataSource {

    override fun getAllMovies(): LiveData<List<MovieEntity>> {
        val movieResults = MutableLiveData<List<MovieEntity>>()
        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(movieResponse: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in movieResponse) {
                    val movie = MovieEntity(
                        response.movieId,
                        response.title,
                        response.description,
                        response.airingDate,
                        response.score,
                        response.genre,
                        response.duration,
                        response.director,
                        response.casting,
                        response.imagePath
                    )
                    movieList.add(movie)
                }
                movieResults.postValue(movieList)
            }
        })
        return movieResults
    }

    override fun getDetailMovie(extraID: MutableLiveData<String>): LiveData<MovieEntity> {
        val movieResult = MutableLiveData<MovieEntity>()
        remoteDataSource.getAllMovies(object : RemoteDataSource.LoadMoviesCallback {
            override fun onAllMoviesReceived(movieResponse: List<MovieResponse>) {
                lateinit var movie: MovieEntity
                for (response in movieResponse) {
                    if (response.movieId == extraID) {
                        movie = MovieEntity(
                            response.movieId,
                            response.title,
                            response.description,
                            response.airingDate,
                            response.score,
                            response.genre,
                            response.duration,
                            response.director,
                            response.casting,
                            response.imagePath
                        )
                        break
                    }
                }
                movieResult.postValue(movie)
            }
        })
        return movieResult
    }

    override fun getAllTelevisions(): LiveData<List<TelevisionEntity>> {
        val televisionResults = MutableLiveData<List<TelevisionEntity>>()
        remoteDataSource.getAllTelevisions(object : RemoteDataSource.LoadTvCallback {
            override fun onAllTvReceived(televisionResponse: List<TelevisionResponse>) {
                val tvList = ArrayList<TelevisionEntity>()
                for (response in televisionResponse) {
                    val tv = TelevisionEntity(
                        response.televisionId,
                        response.title,
                        response.description,
                        response.airingDate,
                        response.score,
                        response.genre,
                        response.duration,
                        response.director,
                        response.casting,
                        response.imagePath
                    )

                    tvList.add(tv)
                }
                televisionResults.postValue(tvList)
            }
        })
        return televisionResults
    }

    override fun getDetailTv(extraID: String): LiveData<TelevisionEntity> {
        val tvResult = MutableLiveData<TelevisionEntity>()

        remoteDataSource.getAllTelevisions(object : RemoteDataSource.LoadTvCallback {
            override fun onAllTvReceived(televisionResponse: List<TelevisionResponse>) {
                lateinit var tv: TelevisionEntity
                for (response in televisionResponse) {
                    if (response.televisionId == extraID) {
                        tv = TelevisionEntity(
                            response.televisionId,
                            response.title,
                            response.description,
                            response.airingDate,
                            response.score,
                            response.genre,
                            response.duration,
                            response.director,
                            response.casting,
                            response.imagePath
                        )
                        break
                    }
                }
                tvResult.postValue(tv)
            }
        })
        return tvResult
    }
}