package com.sekarlangitstudio.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import com.nhaarman.mockitokotlin2.verify
import com.sekarlangitstudio.moviecatalogue.data.source.local.LocalDataSource
import com.sekarlangitstudio.moviecatalogue.data.source.local.entity.MovieEntity
import com.sekarlangitstudio.moviecatalogue.data.source.local.entity.TelevisionEntity
import com.sekarlangitstudio.moviecatalogue.data.source.remote.RemoteDataSource
import com.sekarlangitstudio.moviecatalogue.utils.AppExecutors
import com.sekarlangitstudio.moviecatalogue.utils.DataDummy
import com.sekarlangitstudio.moviecatalogue.utils.LiveDataTestUtil
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class MovieCatalogueRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = mock(RemoteDataSource::class.java)
    private val local = mock(LocalDataSource::class.java)
    private val appExecutors = mock(AppExecutors::class.java)

    private val movieCatalogueRepository = FakeMovieCatalogueRepository(remote, local, appExecutors)

    private val movieResponses = DataDummy.generateRemoteDummyMovie()

    private val televisionResponses = DataDummy.generateRemoteDummyTv()
    private val movieId = movieResponses[0].movieId
    private val televisionId = televisionResponses[0].televisionId

    @Test
    fun getAllMovies() {
        val dummyMovies = MutableLiveData<List<MovieEntity>>()
        dummyMovies.value = DataDummy.generateDummyMovies()
        `when`(local.getAllMovies()).thenReturn(dummyMovies)

        val movieEntities = LiveDataTestUtil.getValue(movieCatalogueRepository.getAllMovies())

        verify(local).getAllMovies()
        assertNotNull(movieEntities.data)
        assertEquals(movieResponses.size.toLong(), movieEntities.data?.size?.toLong())

    }

    @Test
    fun getDetailMovie() {

        val dummyEntity = MutableLiveData<MovieEntity>()
        dummyEntity.value = DataDummy.generateDummyMovieWithId(movieId)
        `when`(local.getMovieDetail(movieId)).thenReturn(dummyEntity)

        val movieEntities =
            LiveDataTestUtil.getValue(movieCatalogueRepository.getDetailMovie(movieId))
        verify(local).getMovieDetail(movieId)
        assertNotNull(movieEntities)
        assertEquals(movieResponses[0].title, movieEntities.title)
    }

    @Test
    fun getFavMovie() {
        val dummyMovie = MutableLiveData<List<MovieEntity>>()
        dummyMovie.value = DataDummy.generateDummyMovies()
        `when`(local.getFavoriteMovie()).thenReturn(dummyMovie)

        val movieEntities = LiveDataTestUtil.getValue(movieCatalogueRepository.getFavoriteMovie())
        verify(local).getFavoriteMovie()
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.size.toLong())
    }


    @Test
    fun getAllTelevisions() {
        val dummyTv = MutableLiveData<List<TelevisionEntity>>()
        dummyTv.value = DataDummy.generateDummyTelevisions()
        `when`(local.getAllTelevisions()).thenReturn(dummyTv)

        val televisionEntities =
            LiveDataTestUtil.getValue(movieCatalogueRepository.getAllTelevisions())
        verify(local).getAllTelevisions()
        assertNotNull(televisionEntities.data)
        assertEquals(televisionResponses.size.toLong(), televisionEntities.data?.size?.toLong())
    }

    @Test
    fun getDetailTv() {
        val dummyEntity = MutableLiveData<TelevisionEntity>()
        dummyEntity.value = DataDummy.generateDummyTvWithId(televisionId)
        `when`(local.getTvDetail(televisionId)).thenReturn(dummyEntity)

        val tvEntities =
            LiveDataTestUtil.getValue(movieCatalogueRepository.getDetailTv(televisionId))
        verify(local).getTvDetail(televisionId)
        assertNotNull(tvEntities)
        assertEquals(televisionResponses[0].title, tvEntities.title)
    }

    fun getFavTv() {
        val dummyTv = MutableLiveData<List<TelevisionEntity>>()
        dummyTv.value = DataDummy.generateDummyTelevisions()
        `when`(local.getFavoriteTv()).thenReturn(dummyTv)

        val tvEntities = LiveDataTestUtil.getValue(movieCatalogueRepository.getFavoriteTv())
        verify(local).getFavoriteTv()
        assertNotNull(tvEntities)
        assertEquals(televisionResponses.size.toLong(), tvEntities.size.toLong())
    }
}