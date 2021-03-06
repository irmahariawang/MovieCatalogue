package com.sekarlangitstudio.moviecatalogue.data.source

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.sekarlangitstudio.moviecatalogue.data.source.local.LocalDataSource
import com.sekarlangitstudio.moviecatalogue.data.source.local.entity.MovieEntity
import com.sekarlangitstudio.moviecatalogue.data.source.local.entity.TelevisionEntity
import com.sekarlangitstudio.moviecatalogue.data.source.remote.ApiResponse
import com.sekarlangitstudio.moviecatalogue.data.source.remote.RemoteDataSource
import com.sekarlangitstudio.moviecatalogue.data.source.remote.response.MovieResponse
import com.sekarlangitstudio.moviecatalogue.data.source.remote.response.TelevisionResponse
import com.sekarlangitstudio.moviecatalogue.utils.AppExecutors
import com.sekarlangitstudio.moviecatalogue.vo.Resource

class MovieCatalogueRepository private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : MovieCatalogueDataSource {

    companion object {
        @Volatile
        private var instance: MovieCatalogueRepository? = null

        fun getInstance(
            remoteData: RemoteDataSource,
            localData: LocalDataSource,
            appExecutors: AppExecutors
        ): MovieCatalogueRepository =
            instance ?: synchronized(this) {
                instance ?: MovieCatalogueRepository(remoteData, localData, appExecutors).apply {
                    instance = this
                }
            }
    }

    override fun getAllMovies(): LiveData<Resource<PagedList<MovieEntity>>> {
        return object :
            NetworkBoundResource<PagedList<MovieEntity>, List<MovieResponse>>(appExecutors) {
            public override fun loadFromDB(): LiveData<PagedList<MovieEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllMovies(), config).build()
            }

            override fun shouldFetch(data: PagedList<MovieEntity>?): Boolean =
                data == null || data.isEmpty()

            public override fun createCall(): LiveData<ApiResponse<List<MovieResponse>>> =
                remoteDataSource.getAllMovies()

            public override fun saveCallResult(movieResponses: List<MovieResponse>) {
                val movieList = ArrayList<MovieEntity>()
                for (response in movieResponses) {
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
                        false,
                        response.imagePath
                    )
                    movieList.add(movie)
                }

                localDataSource.insertMovies(movieList)
            }
        }.asLiveData()
    }


    override fun getAllTelevisions(): LiveData<Resource<PagedList<TelevisionEntity>>> {
        return object :
            NetworkBoundResource<PagedList<TelevisionEntity>, List<TelevisionResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<PagedList<TelevisionEntity>> {
                val config = PagedList.Config.Builder()
                    .setEnablePlaceholders(false)
                    .setInitialLoadSizeHint(4)
                    .setPageSize(4)
                    .build()
                return LivePagedListBuilder(localDataSource.getAllTelevisions(), config).build()
            }


            override fun shouldFetch(data: PagedList<TelevisionEntity>?): Boolean =
                data == null || data.isEmpty()


            override fun createCall(): LiveData<ApiResponse<List<TelevisionResponse>>> =
                remoteDataSource.getAllTelevisions()

            override fun saveCallResult(televisionResponse: List<TelevisionResponse>) {
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
                        false,
                        response.imagePath
                    )
                    tvList.add(tv)
                }
                localDataSource.insertTvs(tvList)
            }
        }.asLiveData()
    }

    override fun getDetailMovie(movieID: String): LiveData<MovieEntity> =
        localDataSource.getMovieDetail(movieID)


    override fun getDetailTv(televisionID: String): LiveData<TelevisionEntity> =
        localDataSource.getTvDetail(televisionID)

    override fun getFavoriteMovie(): LiveData<PagedList<MovieEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteMovie(), config).build()
    }

    override fun getFavoriteTv(): LiveData<PagedList<TelevisionEntity>> {
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setInitialLoadSizeHint(4)
            .setPageSize(4)
            .build()
        return LivePagedListBuilder(localDataSource.getFavoriteTv(), config).build()
    }

    override fun setMovieFavorite(movie: MovieEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setMovieFavorite(movie, state) }

    override fun setTvFavorite(tv: TelevisionEntity, state: Boolean) =
        appExecutors.diskIO().execute { localDataSource.setTvFavorite(tv, state) }

}


