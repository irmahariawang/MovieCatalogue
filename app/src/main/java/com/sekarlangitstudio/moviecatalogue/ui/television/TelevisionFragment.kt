package com.sekarlangitstudio.moviecatalogue.ui.television

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sekarlangitstudio.moviecatalogue.databinding.FragmentTelevisionBinding
import com.sekarlangitstudio.moviecatalogue.viewmodel.ViewModelFactory

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

            fragmentTelevisionBinding.progressBar.visibility = View.VISIBLE
            viewModel.getTelevisions().observe(viewLifecycleOwner, { televisions ->
                fragmentTelevisionBinding.progressBar.visibility = View.GONE
                televisionAdapter.setTv(televisions)
                televisionAdapter.notifyDataSetChanged()
            })
            with(fragmentTelevisionBinding.rvTv) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = televisionAdapter
            }
        }
    }
}