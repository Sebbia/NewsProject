package com.example.newsproject.ui.categoryList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.newsproject.databinding.FragmentCategoryListBinding
import com.example.newsproject.ui.ItemClickListener
import com.example.newsproject.ui.categoryList.recycler.CategoryListAdapter
import com.example.newsproject.ui.categoryList.recycler.CategoryListDecorator

class CategoryListFragment :
    Fragment(),
    ItemClickListener {
    private val TAG = "MyCategoryListFragment"
    private var _binding: FragmentCategoryListBinding? = null
    private val binding get() = _binding!!

    lateinit var viewModel: CategoryListViewModel //TODO replace with interface

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView called")
        _binding = FragmentCategoryListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(CategoryListViewModel::class.java)
        val categoryAdapter = CategoryListAdapter(this)
        binding.categoryList.apply {
            layoutManager = GridLayoutManager(context, 2)
            //TODO add dynamic spanCount based on screen wide or smt
            adapter = categoryAdapter
            addItemDecoration(CategoryListDecorator())
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