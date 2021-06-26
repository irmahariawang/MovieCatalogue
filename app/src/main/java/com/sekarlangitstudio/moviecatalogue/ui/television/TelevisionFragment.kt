package com.sekarlangitstudio.moviecatalogue.ui.television

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sekarlangitstudio.moviecatalogue.databinding.FragmentTelevisionBinding
import com.sekarlangitstudio.moviecatalogue.viewmodel.ViewModelFactory
import com.sekarlangitstudio.moviecatalogue.vo.Status

class TelevisionFragment : Fragment() {

    private lateinit var fragmentTelevisionBinding: FragmentTelevisionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        fragmentTelevisionBinding =
            FragmentTelevisionBinding.inflate(layoutInflater, container, false)
        return fragmentTelevisionBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (activity != null) {
            val factory = ViewModelFactory.getInstance(requireActivity())
            val viewModel = ViewModelProvider(this, factory)[TelevisionViewModel::class.java]
            val televisionAdapter = TelevisionAdapter()

            viewModel.getTelevisions().observe(viewLifecycleOwner, { tvs ->
                if (tvs != null) {
                    when (tvs.status) {
                        Status.LOADING -> fragmentTelevisionBinding.progressBar.visibility =
                            View.VISIBLE
                        Status.SUCCESS -> {
                            fragmentTelevisionBinding.progressBar.visibility = View.GONE
                            televisionAdapter.submitList(tvs.data)
                        }
                        Status.ERROR -> {
                            fragmentTelevisionBinding.progressBar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })

            with(fragmentTelevisionBinding.rvTv) {
                this.layoutManager = LinearLayoutManager(context)
                this.setHasFixedSize(true)
                this.adapter = televisionAdapter
            }
        }
    }
}