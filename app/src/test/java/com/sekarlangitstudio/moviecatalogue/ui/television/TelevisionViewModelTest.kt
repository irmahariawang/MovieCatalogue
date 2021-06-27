package com.sekarlangitstudio.moviecatalogue.ui.television

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.sekarlangitstudio.moviecatalogue.data.source.MovieCatalogueRepository
import com.sekarlangitstudio.moviecatalogue.data.source.local.entity.TelevisionEntity
import com.sekarlangitstudio.moviecatalogue.vo.Resource
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
    private lateinit var pagedList: PagedList<TelevisionEntity>

    @Mock
    private lateinit var observer: Observer<Resource<PagedList<TelevisionEntity>>>

    @Before
    fun setUp() {
        viewModel = TelevisionViewModel(movieCatalogueRepository)
    }

    @Test
    fun getTelevisions() {
        val dummyTvs = Resource.success(pagedList)
        `when`(dummyTvs.data?.size).thenReturn(11)
        val tvs = MutableLiveData<Resource<PagedList<TelevisionEntity>>>()
        tvs.value = dummyTvs

        `when`(movieCatalogueRepository.getAllTelevisions()).thenReturn(tvs)
        val tvEntities = viewModel.getTelevisions().value?.data
        verify(movieCatalogueRepository).getAllTelevisions()
        assertNotNull(tvEntities)
        assertEquals(11, tvEntities?.size)

        viewModel.getTelevisions().observeForever(observer)
        verify(observer).onChanged(dummyTvs)
    }
}