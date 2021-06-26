package com.sekarlangitstudio.moviecatalogue.ui.favorite.favtv

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sekarlangitstudio.moviecatalogue.databinding.FragmentFavTvBinding
import com.sekarlangitstudio.moviecatalogue.viewmodel.ViewModelFactory

class FavTvFragment : Fragment() {


    private lateinit var fragmentFavTvBinding: FragmentFavTvBinding

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
        super.onActivityCreated(savedInstanceState)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[FavTvViewModel::class.java]

            val favTvAdapter = FavTvAdapter()

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

}