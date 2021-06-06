package com.pn.appentustest.ui.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.pn.appentustest.R
import com.pn.appentustest.databinding.FragmentImagesListBinding
import com.pn.appentustest.ui.adapter.ImagesAdapter
import com.pn.appentustest.ui.adapter.PhotoLoadStateAdapter
import com.pn.appentustest.ui.viewmodel.ImagesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


@AndroidEntryPoint
internal class ImagesListFragment : Fragment(R.layout.fragment_images_list) {

    private val viewModel: ImagesViewModel by viewModels()
    private lateinit var binding: FragmentImagesListBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentImagesListBinding.bind(view)
        val adapter = ImagesAdapter()

        binding.apply {
            val layoutManager = GridLayoutManager(requireContext(), 2)
            val footerAdapter = PhotoLoadStateAdapter { adapter.retry() }
            recyclerView.setHasFixedSize(true)
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adapter.withLoadStateFooter(
                footer = footerAdapter
            )
            layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return if (position == adapter.itemCount && footerAdapter.itemCount > 0) {
                        2
                    } else {
                        1
                    }
                }
            }
        }

        // Activity CombinedLoadState
        lifecycleScope.launch {
            viewModel.apiData().collect {
                it.let {
                    adapter.submitData(it)
                }
            }
        }
    }
}