package com.example.newsproject.ui.newsList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.newsproject.R
import com.example.newsproject.databinding.FragmentNewsListBinding
import com.example.newsproject.ui.ItemClickListener
import com.example.newsproject.ui.categoryList.CategoryListViewModelImpl
import com.example.newsproject.ui.newsList.recycler.NewsListAdapter
import com.example.newsproject.ui.newsList.recycler.NewsListDecorator
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsListFragment :
    Fragment(),
    ItemClickListener {
    private val TAG = "MyNewsListFragment"
    val args: NewsListFragmentArgs by navArgs()

    private var _binding: FragmentNewsListBinding? = null
    private val binding get() = _binding!!

    val viewModel: NewsListViewModel by viewModels<NewsListViewModelImpl>()
        //ViewModelProvider(this).get(NewsListViewModelImpl::class.java)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView called")
        _binding = FragmentNewsListBinding.inflate(inflater, container, false)
        viewModel.getNewsList(args.categoryId, 0)//TODO place to viewmodel
        val newsAdapter = NewsListAdapter(this)
        binding.newsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = newsAdapter
            val contentMargin =
                (resources.getDimension(R.dimen.content_margin) / resources.displayMetrics.density).toInt()
            addItemDecoration(
                NewsListDecorator(
                    contentMargin,
                    contentMargin,
                    contentMargin
                )
            )
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