package com.sekarlangitstudio.moviecatalogue.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import com.sekarlangitstudio.moviecatalogue.R
import com.sekarlangitstudio.moviecatalogue.utils.DataDummy
import com.sekarlangitstudio.moviecatalogue.utils.EspressoIdlingResource
import org.junit.After
import org.junit.Before
import org.junit.FixMethodOrder
import org.junit.Test
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
class HomeActivityTest {
    private val dummyMovie = DataDummy.generateDummyMovies()
    private val dummyTelevision = DataDummy.generateDummyTelevisions()

    @Before
    fun setUp() {
        ActivityScenario.launch(HomeActivity::class.java)
        IdlingRegistry.getInstance().register(EspressoIdlingResource.idlingResource)
    }

    @After
    fun tearDown() {
        IdlingRegistry.getInstance().unregister(EspressoIdlingResource.idlingResource)
    }

    @Test
    fun load1Movie() {
        onView(withId(R.id.rv_movie)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_movie)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyMovie.size
            )
        )
    }

    @Test
    fun load2DetailMovie() {
        //onView(withText("MOVIES")).perform(click())
        onView(withId(R.id.rv_movie)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.tv_detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_title)).check(matches(withText(dummyMovie[0].title)))
        onView(withId(R.id.tv_detail_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_date)).check(matches(withText(dummyMovie[0].airingDate)))
        onView(withId(R.id.tv_detail_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_genre)).check(matches(withText(dummyMovie[0].genre)))
        onView(withId(R.id.text_description)).check(matches(isDisplayed()))
        onView(withId(R.id.text_description)).check(matches(withText(dummyMovie[0].description)))
        onView(withId(R.id.text_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.text_duration)).check(matches(withText(dummyMovie[0].duration)))

        onView(withId(R.id.btn_add_favorite)).perform(click())
    }

    @Test
    fun load3Tv() {
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_tv)).check(matches(isDisplayed()))
        onView(withId(R.id.rv_tv)).perform(
            RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(
                dummyTelevision.size
            )
        )
    }

    @Test
    fun load4DetailTv() {
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_tv)).perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )

        onView(withId(R.id.tv_detail_title)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_title)).check(matches(withText(dummyTelevision[0].title)))
        onView(withId(R.id.tv_detail_date)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_date)).check(matches(withText(dummyTelevision[0].airingDate)))
        onView(withId(R.id.tv_detail_genre)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_detail_genre)).check(matches(withText(dummyTelevision[0].genre)))
        onView(withId(R.id.text_description)).check(matches(isDisplayed()))
        onView(withId(R.id.text_description)).check(matches(withText(dummyTelevision[0].description)))
        onView(withId(R.id.text_duration)).check(matches(isDisplayed()))
        onView(withId(R.id.text_duration)).check(matches(withText(dummyTelevision[0].duration)))

        onView(withId(R.id.btn_add_favorite)).perform(click())
    }


    @Test
    fun load5Favorite() {
        onView(withId(R.id.menu_favorite)).perform(click())
        onView(withId(R.id.rv_favmovie)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.btn_remove_favorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
        onView(withText("TV SHOWS")).perform(click())
        onView(withId(R.id.rv_favtv)).perform(
            RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0,
                click()
            )
        )
        onView(withId(R.id.btn_remove_favorite)).perform(click())
        onView(isRoot()).perform(ViewActions.pressBack())
    }


}