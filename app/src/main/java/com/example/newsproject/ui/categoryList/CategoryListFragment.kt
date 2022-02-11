package com.example.newsproject.ui.categoryList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider//TODO mb remove
import com.example.newsproject.databinding.FragmentCategoryListBinding

class CategoryListFragment : Fragment() {

    private var _binding: FragmentCategoryListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val viewModel = ViewModelProvider(this).get(CategoryListViewModel::class.java)//TODO DI
        _binding = FragmentCategoryListBinding.inflate(inflater, container, false)

        viewModel.text.observe(viewLifecycleOwner) {
            //textView.text = it
        }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}