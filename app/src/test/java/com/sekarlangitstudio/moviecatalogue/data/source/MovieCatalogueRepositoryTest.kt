package com.sekarlangitstudio.moviecatalogue.data.source

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.verify
import com.sekarlangitstudio.moviecatalogue.data.source.remote.RemoteDataSource
import com.sekarlangitstudio.moviecatalogue.utils.DataDummy
import com.sekarlangitstudio.moviecatalogue.utils.LiveDataTestUtil
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class MovieCatalogueRepositoryTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val remote = Mockito.mock(RemoteDataSource::class.java)
    private val movieCatalogueRepository = FakeMovieCatalogueRepository(remote)
    private val movieResponses = DataDummy.generateRemoteDummyMovie()
    private val televisionResponses = DataDummy.generateRemoteDummyTv()
    private val movieId = movieResponses[0].movieId
    private val televisionId = televisionResponses[0].televisionId

    @Test
    fun getAllMovies() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                .onAllMoviesReceived(movieResponses)
            null
        }.`when`(remote).getAllMovies(any())
        val movieEntities = LiveDataTestUtil.getValue(movieCatalogueRepository.getAllMovies())
        verify(remote).getAllMovies(any())
        assertNotNull(movieEntities)
        assertEquals(movieResponses.size.toLong(), movieEntities.size.toLong())
    }

    @Test
    fun getDetailMovie() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadMoviesCallback)
                .onAllMoviesReceived(movieResponses)
            null
        }.`when`(remote).getAllMovies(any())
        val movieEntities =
            LiveDataTestUtil.getValue(movieCatalogueRepository.getDetailMovie(movieId))
        verify(remote).getAllMovies(any())
        assertNotNull(movieEntities)
        assertEquals(movieResponses[0].title, movieEntities.title)
    }

    @Test
    fun getAllTelevisions() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvCallback)
                .onAllTvReceived(televisionResponses)
            null
        }.`when`(remote).getAllTelevisions(any())
        val televisionEntities =
            LiveDataTestUtil.getValue(movieCatalogueRepository.getAllTelevisions())
        verify(remote).getAllTelevisions(any())

        assertNotNull(televisionEntities)
        assertEquals(televisionResponses.size.toLong(), televisionEntities.size.toLong())
    }

    @Test
    fun getDetailTv() {
        doAnswer { invocation ->
            (invocation.arguments[0] as RemoteDataSource.LoadTvCallback)
                .onAllTvReceived(televisionResponses)
            null
        }.`when`(remote).getAllTelevisions(any())
        val televisionEntities =
            LiveDataTestUtil.getValue(movieCatalogueRepository.getDetailTv(televisionId))
        verify(remote).getAllTelevisions(any())

        assertNotNull(televisionEntities)
        assertEquals(televisionResponses[0].title, televisionEntities.title)
    }
}