package com.sekarlangitstudio.moviecatalogue.ui.television

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.sekarlangitstudio.moviecatalogue.data.source.MovieCatalogueRepository
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
class TelevisionViewModelTest {

    private lateinit var viewModel: TelevisionViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieCatalogueRepository: MovieCatalogueRepository

    @Mock
    private lateinit var observer: Observer<List<TelevisionEntity>>

    @Before
    fun setUp() {
        viewModel = TelevisionViewModel(movieCatalogueRepository)
    }

    @Test
    fun getTelevisions() {
        val dummyTv = DataDummy.generateDummyTelevisions()
        val tvs = MutableLiveData<List<TelevisionEntity>>()
        tvs.value = dummyTv

        `when`(movieCatalogueRepository.getAllTelevisions()).thenReturn(tvs)
        val tvEntities = viewModel.getTelevisions().value
        verify<MovieCatalogueRepository>(movieCatalogueRepository).getAllTelevisions()
        assertNotNull(tvEntities)
        assertEquals(11, tvEntities?.size)

        viewModel.getTelevisions().observeForever(observer)
        verify(observer).onChanged(dummyTv)
    }
}