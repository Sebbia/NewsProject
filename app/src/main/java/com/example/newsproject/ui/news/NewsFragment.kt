package com.example.newsproject.ui.news

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.newsproject.databinding.FragmentNewsBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class NewsFragment : Fragment() {
    private val TAG = "MyNewsFragment"
    val args: NewsFragmentArgs by navArgs()

    private var _binding: FragmentNewsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var viewModel: NewsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Log.d(TAG, "onCreateView called")
        _binding = FragmentNewsBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(NewsViewModelImpl::class.java)
        viewModel.getNews(args.newsId)
        viewModel.news.observe(viewLifecycleOwner) {
            Log.d(TAG, "News data was changed")
            binding.newsTitle.text = it.title
            binding.newsShortDescription.text = it.shortDescription
            //binding.newsPage.loadData
        }
        return binding.root
    }

    override fun onDestroyView() {
        Log.d(TAG, "onDestroyView called")
        super.onDestroyView()
        _binding = null
    }
}