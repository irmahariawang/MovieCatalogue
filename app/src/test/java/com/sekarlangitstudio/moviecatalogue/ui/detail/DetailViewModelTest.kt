package com.sekarlangitstudio.moviecatalogue.ui.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.sekarlangitstudio.moviecatalogue.data.source.MovieCatalogueRepository
import com.sekarlangitstudio.moviecatalogue.data.source.local.entity.MovieEntity
import com.sekarlangitstudio.moviecatalogue.data.source.local.entity.TelevisionEntity
import com.sekarlangitstudio.moviecatalogue.utils.DataDummy
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)

class DetailViewModelTest {
    private lateinit var viewModelMovie: DetailViewModel
    private lateinit var viewModelTv: DetailViewModel
    private val dummyMovie = DataDummy.generateDummyMovies()[0]
    private val dummyTelevision = DataDummy.generateDummyTelevisions()[0]
    private val movieId = dummyMovie.movieId
    private val televisionId = dummyTelevision.televisionId

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieCatalogueRepository: MovieCatalogueRepository

    @Mock
    private lateinit var movieObserver: Observer<MovieEntity>

    @Mock
    private lateinit var televisionObserver: Observer<TelevisionEntity>


    @Before
    fun setUp() {
        viewModelMovie = DetailViewModel(movieCatalogueRepository)
        viewModelMovie.setSelectedId(movieId)
        viewModelTv = DetailViewModel(movieCatalogueRepository)
        viewModelTv.setSelectedId(televisionId)
    }

    @Test
    fun getDetailMovie() {
        val movie = MutableLiveData<MovieEntity>()
        movie.value = dummyMovie

        `when`(movieCatalogueRepository.getDetailMovie(movieId)).thenReturn(movie)
        viewModelMovie.setSelectedId(movieId)
        val movieEntity = viewModelMovie.getDetailMovie().value as MovieEntity
        verify(movieCatalogueRepository).getDetailMovie(movieId)
        assertNotNull(movieEntity)
        assertEquals(dummyMovie.movieId, movieEntity.movieId)
        assertEquals(dummyMovie.title, movieEntity.title)
        assertEquals(dummyMovie.airingDate, movieEntity.airingDate)
        assertEquals(dummyMovie.genre, movieEntity.genre)
        assertEquals(dummyMovie.score, movieEntity.score)
        assertEquals(dummyMovie.description, movieEntity.description)
        assertEquals(dummyMovie.duration, movieEntity.duration)
        assertEquals(dummyMovie.director, movieEntity.director)
        assertEquals(dummyMovie.casting, movieEntity.casting)
        assertEquals(dummyMovie.imagePath, movieEntity.imagePath)

        viewModelMovie.getDetailMovie().observeForever(movieObserver)
        verify(movieObserver).onChanged(dummyMovie)
    }

    @Test
    fun getDetailTV() {
        val television = MutableLiveData<TelevisionEntity>()
        television.value = dummyTelevision

        `when`(movieCatalogueRepository.getDetailTv(televisionId)).thenReturn(television)
        viewModelTv.setSelectedId(televisionId)
        val tvEntity = viewModelTv.getDetailTv().value as TelevisionEntity
        verify(movieCatalogueRepository).getDetailTv(televisionId)
        assertNotNull(tvEntity)
        assertEquals(dummyTelevision.televisionId, tvEntity.televisionId)
        assertEquals(dummyTelevision.title, tvEntity.title)
        assertEquals(dummyTelevision.airingDate, tvEntity.airingDate)
        assertEquals(dummyTelevision.genre, tvEntity.genre)
        assertEquals(dummyTelevision.score, tvEntity.score)
        assertEquals(dummyTelevision.description, tvEntity.description)
        assertEquals(dummyTelevision.duration, tvEntity.duration)
        assertEquals(dummyTelevision.director, tvEntity.director)
        assertEquals(dummyTelevision.casting, tvEntity.casting)
        assertEquals(dummyTelevision.imagePath, tvEntity.imagePath)

        viewModelTv.getDetailTv().observeForever(televisionObserver)
        verify(televisionObserver).onChanged(dummyTelevision)
    }
}