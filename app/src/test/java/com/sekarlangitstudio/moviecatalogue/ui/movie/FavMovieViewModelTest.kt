package com.sekarlangitstudio.moviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.nhaarman.mockitokotlin2.verify
import com.sekarlangitstudio.moviecatalogue.data.source.MovieCatalogueRepository
import com.sekarlangitstudio.moviecatalogue.data.source.local.entity.MovieEntity
import com.sekarlangitstudio.moviecatalogue.ui.favorite.favmovie.FavMovieViewModel
import com.sekarlangitstudio.moviecatalogue.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavMovieViewModelTest {
    private lateinit var viewModel: FavMovieViewModel
    private val dummyMovies = DataDummy.generateDummyMovies()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieCatalogueRepository: MovieCatalogueRepository

    @Mock
    private lateinit var observer: Observer<PagedList<MovieEntity>>

    @Mock
    private lateinit var pagedList: PagedList<MovieEntity>

    @Before
    fun setup() {
        viewModel = FavMovieViewModel(movieCatalogueRepository)
    }

    @Test
    fun getFavMovie() {
        val dummyMovies = pagedList
        `when`(dummyMovies.size).thenReturn(11)
        val movies = MutableLiveData<PagedList<MovieEntity>>()
        movies.value = dummyMovies

        `when`(movieCatalogueRepository.getFavoriteMovie()).thenReturn(movies)
        val movieEntities = viewModel.getFavoriteMovie().value
        verify<MovieCatalogueRepository>(movieCatalogueRepository).getFavoriteMovie()
        assertNotNull(movieEntities)
        assertEquals(11, movieEntities?.size)

        viewModel.getFavoriteMovie().observeForever(observer)
        verify(observer).onChanged(dummyMovies)
    }

}