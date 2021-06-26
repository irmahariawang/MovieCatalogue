package com.sekarlangitstudio.moviecatalogue.ui.favorite.favmovie

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sekarlangitstudio.moviecatalogue.databinding.FragmentFavMovieBinding
import com.sekarlangitstudio.moviecatalogue.viewmodel.ViewModelFactory

class FavMovieFragment : Fragment() {

    private lateinit var fragmentFavMovieBinding: FragmentFavMovieBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentFavMovieBinding = FragmentFavMovieBinding.inflate(layoutInflater, container, false)
        return fragmentFavMovieBinding.root
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[FavMovieViewModel::class.java]

            val favMovieAdapter = FavMovieAdapter()

            fragmentFavMovieBinding.progressBar.visibility = View.VISIBLE
            viewModel.getFavoriteMovie().observe(viewLifecycleOwner, { movies ->
                fragmentFavMovieBinding.progressBar.visibility = View.GONE
                favMovieAdapter.setMovies(movies)
                favMovieAdapter.notifyDataSetChanged()
            })

            with(fragmentFavMovieBinding.rvFavmovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = favMovieAdapter
            }
        }
    }
}