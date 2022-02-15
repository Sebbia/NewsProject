package com.example.newsproject.ui.categoryList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.newsproject.R
import com.example.newsproject.databinding.FragmentCategoryListBinding
import com.example.newsproject.ui.ItemClickListener
import com.example.newsproject.ui.categoryList.recycler.CategoryListAdapter
import com.example.newsproject.ui.categoryList.recycler.CategoryListDecorator
import com.example.newsproject.utils.SpanCount
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CategoryListFragment :
    Fragment(),
    ItemClickListener {
    private val TAG = "MyCategoryListFragment"
    private var _binding: FragmentCategoryListBinding? = null
    private val binding get() = _binding!!

    val viewModel: CategoryListViewModel by viewModels<CategoryListViewModelImpl>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView called")
        _binding = FragmentCategoryListBinding.inflate(inflater, container, false)
        val categoryAdapter = CategoryListAdapter(this)
        binding.categoryList.apply {
            val spanCount = SpanCount.getSpanCount(
                context,
                resources.getDimension(R.dimen.min_category_card_width)
            )
            layoutManager = GridLayoutManager(context, spanCount)
            adapter = categoryAdapter
            addItemDecoration(
                CategoryListDecorator(
                    spanCount,
                    resources.getDimension(R.dimen.content_margin),
                    true
                )
            )
        }
        viewModel.list.observe(viewLifecycleOwner) {
            Log.d(TAG, "CategoryList data was changed")
            categoryAdapter.updateList(it)
        }
        viewModel.navEvent.observe(viewLifecycleOwner) {
            Log.d(TAG, "NavEvent was called")
            binding.root.findNavController().navigate(it)
        }
        return binding.root
    }

    override fun onDestroyView() {
        Log.d(TAG, "onDestroyView called")
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClicked(id: Long) {
        viewModel.onItemClicked(id)
    }
}