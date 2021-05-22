package com.sekarlangitstudio.moviecatalogue.ui.home

import android.content.Context
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.sekarlangitstudio.moviecatalogue.R
import com.sekarlangitstudio.moviecatalogue.ui.movie.MovieFragment
import com.sekarlangitstudio.moviecatalogue.ui.television.TelevisionFragment

class SectionsPagerAdapter(private val mContext: Context, fm: FragmentManager) :
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    companion object {
        @StringRes
        private val TAB_TITTLES = intArrayOf(R.string.movies, R.string.televisions)
    }

    override fun getCount(): Int = TAB_TITTLES.size

    override fun getItem(position: Int): Fragment =
        when (position) {
            0 -> MovieFragment()
            1 -> TelevisionFragment()
            else -> Fragment()
        }

    override fun getPageTitle(position: Int): CharSequence? =
        mContext.resources.getString(TAB_TITTLES[position])

}