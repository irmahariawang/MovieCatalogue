package com.sekarlangitstudio.moviecatalogue.ui.movie

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.verify
import com.sekarlangitstudio.moviecatalogue.data.source.MovieCatalogueRepository
import com.sekarlangitstudio.moviecatalogue.data.source.local.entity.MovieEntity
import com.sekarlangitstudio.moviecatalogue.ui.favorite.favmovie.FavMovieViewModel
import com.sekarlangitstudio.moviecatalogue.utils.DataDummy
import com.sekarlangitstudio.moviecatalogue.utils.LiveDataTestUtil
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
    private lateinit var observer: Observer<List<MovieEntity>>

    @Before
    fun setup() {
        viewModel = FavMovieViewModel(movieCatalogueRepository)
    }

    @Test
    fun getFavMovie() {
        val movies = MutableLiveData<List<MovieEntity>>()
        movies.value = dummyMovies

        `when`(movieCatalogueRepository.getFavoriteMovie()).thenReturn(movies)
        val movieEntity = LiveDataTestUtil.getValue(movieCatalogueRepository.getFavoriteMovie())
        verify(movieCatalogueRepository).getFavoriteMovie()
        assertNotNull(movieEntity)

        viewModel.getFavoriteMovie().observeForever(observer)
        verify(observer).onChanged(dummyMovies)

    }

}