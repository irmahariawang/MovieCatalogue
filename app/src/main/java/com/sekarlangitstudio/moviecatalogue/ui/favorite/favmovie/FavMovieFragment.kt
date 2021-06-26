package com.sekarlangitstudio.moviecatalogue.ui.favorite.favmovie

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.sekarlangitstudio.moviecatalogue.R
import com.sekarlangitstudio.moviecatalogue.databinding.FragmentFavMovieBinding
import com.sekarlangitstudio.moviecatalogue.viewmodel.ViewModelFactory

class FavMovieFragment : Fragment() {

    private lateinit var fragmentFavMovieBinding: FragmentFavMovieBinding
    private lateinit var viewModel: FavMovieViewModel
    private lateinit var favMovieAdapter: FavMovieAdapter

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
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(fragmentFavMovieBinding.rvFavmovie)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[FavMovieViewModel::class.java]
            favMovieAdapter = FavMovieAdapter()

            fragmentFavMovieBinding.progressBar.visibility = View.VISIBLE
            viewModel.getFavoriteMovie().observe(viewLifecycleOwner, { movies ->
                fragmentFavMovieBinding.progressBar.visibility = View.GONE
                favMovieAdapter.submitList(movies)
            })

            with(fragmentFavMovieBinding.rvFavmovie) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = favMovieAdapter
            }
        }
    }

    private val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
        override fun getMovementFlags(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder
        ): Int =
            makeMovementFlags(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)

        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val movieEntity = favMovieAdapter.getSwipedData(swipedPosition)
                movieEntity?.let { viewModel.setFavorite(it) }

                val snackbar =
                    Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_ok) { v ->
                    movieEntity?.let { viewModel.setFavorite(it) }
                }
                snackbar.show()
            }
        }
    })
}