package com.example.newsproject.ui.newsList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsproject.databinding.FragmentCategoryListBinding
import com.example.newsproject.databinding.FragmentNewsListBinding
import com.example.newsproject.ui.ItemClickListener
import com.example.newsproject.ui.categoryList.CategoryListViewModel
import com.example.newsproject.ui.categoryList.recycler.CategoryListAdapter
import com.example.newsproject.ui.categoryList.recycler.CategoryListDecorator
import com.example.newsproject.ui.newsList.recycler.NewsListAdapter
import com.example.newsproject.ui.newsList.recycler.NewsListDecorator

class NewsListFragment :
    Fragment(),
    ItemClickListener {
    private val TAG = "MyNewsListFragment"
    private var _binding: FragmentNewsListBinding? = null
    private val binding get() = _binding!!
    lateinit var viewModel: NewsListViewModel //TODO replace with interface

    val args: NewsListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView called")
        _binding = FragmentNewsListBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(NewsListViewModel::class.java)
        viewModel.getNewsList(args.categoryId, 0)
        val newsAdapter = NewsListAdapter(this)
        binding.newsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
            addItemDecoration(NewsListDecorator())
        }
        //TODO add paging after scrolling
        viewModel.list.observe(viewLifecycleOwner) {
            Log.d(TAG, "NewsList data was changed")
            newsAdapter.updateList(it)
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