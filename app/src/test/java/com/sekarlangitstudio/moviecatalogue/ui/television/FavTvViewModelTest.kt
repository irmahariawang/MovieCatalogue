package com.sekarlangitstudio.moviecatalogue.ui.television

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.sekarlangitstudio.moviecatalogue.data.source.MovieCatalogueRepository
import com.sekarlangitstudio.moviecatalogue.data.source.local.entity.TelevisionEntity
import com.sekarlangitstudio.moviecatalogue.ui.favorite.favtv.FavTvViewModel
import com.sekarlangitstudio.moviecatalogue.utils.DataDummy
import com.sekarlangitstudio.moviecatalogue.utils.LiveDataTestUtil
import junit.framework.TestCase.assertNotNull
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
    private val dummyTv = DataDummy.generateDummyTelevisions()

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var movieCatalogueRepository: MovieCatalogueRepository

    @Mock
    private lateinit var observer: Observer<List<TelevisionEntity>>

    @Before
    fun setup() {
        viewModel = FavTvViewModel(movieCatalogueRepository)
    }

    @Test
    fun getFavTv() {
        val tvs = MutableLiveData<List<TelevisionEntity>>()
        tvs.value = dummyTv

        `when`(movieCatalogueRepository.getFavoriteTv()).thenReturn(tvs)
        val tvEntity = LiveDataTestUtil.getValue(movieCatalogueRepository.getFavoriteTv())
        verify(movieCatalogueRepository).getFavoriteTv()
        assertNotNull(tvEntity)

        viewModel.getFavoriteTv().observeForever(observer)
        verify(observer).onChanged(dummyTv)

    }
}