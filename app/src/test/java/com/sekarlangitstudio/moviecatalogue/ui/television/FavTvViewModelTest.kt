package com.sekarlangitstudio.moviecatalogue.ui.television

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import com.sekarlangitstudio.moviecatalogue.data.source.MovieCatalogueRepository
import com.sekarlangitstudio.moviecatalogue.data.source.local.entity.TelevisionEntity
import com.sekarlangitstudio.moviecatalogue.ui.favorite.favtv.FavTvViewModel
import junit.framework.TestCase.assertNotNull
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class FavTvViewModelTest {
    private lateinit var viewModel: FavTvViewModel

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieCatalogueRepository: MovieCatalogueRepository

    @Mock
    private lateinit var observer: Observer<PagedList<TelevisionEntity>>

    @Mock
    private lateinit var pagedList: PagedList<TelevisionEntity>

    @Before
    fun setup() {
        viewModel = FavTvViewModel(movieCatalogueRepository)
    }

    @Test
    fun getFavTv() {
        val dummyTvs = pagedList
        `when`(dummyTvs.size).thenReturn(11)
        val tvs = MutableLiveData<PagedList<TelevisionEntity>>()
        tvs.value = dummyTvs

        `when`(movieCatalogueRepository.getFavoriteTv()).thenReturn(tvs)
        val tvEntities = viewModel.getFavoriteTv().value
        verify<MovieCatalogueRepository>(movieCatalogueRepository).getFavoriteTv()
        assertNotNull(tvEntities)
        assertEquals(11, tvEntities?.size)

        viewModel.getFavoriteTv().observeForever(observer)
        verify(observer).onChanged(dummyTvs)

    }
}