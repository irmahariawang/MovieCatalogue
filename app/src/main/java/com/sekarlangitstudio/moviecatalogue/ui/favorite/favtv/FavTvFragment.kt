package com.sekarlangitstudio.moviecatalogue.ui.favorite.favtv

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
import com.sekarlangitstudio.moviecatalogue.databinding.FragmentFavTvBinding
import com.sekarlangitstudio.moviecatalogue.viewmodel.ViewModelFactory

class FavTvFragment : Fragment() {


    private lateinit var fragmentFavTvBinding: FragmentFavTvBinding
    private lateinit var viewModel: FavTvViewModel
    private lateinit var favTvAdapter: FavTvAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentFavTvBinding = FragmentFavTvBinding.inflate(layoutInflater, container, false)
        return fragmentFavTvBinding.root
    }

    @SuppressLint("FragmentLiveDataObserve")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        itemTouchHelper.attachToRecyclerView(fragmentFavTvBinding.rvFavtv)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            viewModel = ViewModelProvider(this, factory)[FavTvViewModel::class.java]

            favTvAdapter = FavTvAdapter()

            fragmentFavTvBinding.progressBar.visibility = View.VISIBLE
            viewModel.getFavoriteTv().observe(viewLifecycleOwner, { tvs ->
                fragmentFavTvBinding.progressBar.visibility = View.GONE
                favTvAdapter.submitList(tvs)
            })

            with(fragmentFavTvBinding.rvFavtv) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                this.adapter = favTvAdapter
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
        ): Boolean {
            return true
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            if (view != null) {
                val swipedPosition = viewHolder.adapterPosition
                val televisionEntity = favTvAdapter.getSwipedData(swipedPosition)
                televisionEntity?.let { viewModel.setFavoriteTv(it) }

                val snackbar =
                    Snackbar.make(view as View, R.string.message_undo, Snackbar.LENGTH_LONG)
                snackbar.setAction(R.string.message_ok) { v ->
                    televisionEntity?.let { viewModel.setFavoriteTv(it) }
                }
                snackbar.show()
            }
        }
    })
}