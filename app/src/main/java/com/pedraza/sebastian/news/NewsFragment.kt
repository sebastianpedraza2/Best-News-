package com.pedraza.sebastian.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.pedraza.sebastian.news.databinding.FragmentNewsBinding
import com.pedraza.sebastian.news.presentation.adapter.NewsAdapter
import com.pedraza.sebastian.news.presentation.viewmodel.NewsViewModel
import com.pedraza.sebastian.news.util.Resource


class NewsFragment : Fragment(R.layout.fragment_news) {
    private lateinit var viewModel: NewsViewModel

    private lateinit var binding: FragmentNewsBinding

    private lateinit var newsAdapter: NewsAdapter

    private var country = "us"

    private var page = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentNewsBinding.bind(view)
        /**
         * SHARED VIEWMODEL, getting the instance from the activity
         */
        viewModel = (activity as MainActivity).viewModel

        /**
         * Use the adapter instance injected in the activity
         */
        newsAdapter = (activity as MainActivity).adapter

        initRecyclerView()
        viewNewsList()
    }

    private fun viewNewsList() {
        viewModel.getTopHeadlines(country, page).observe(viewLifecycleOwner, Observer { respose ->
            when (respose) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    hideProgressBar()
                    respose.data?.let {
                        newsAdapter.differ.submitList(it.articles.toList())
                    }
                }
                is Resource.Error -> {
                    hideProgressBar()
                    respose.message?.let {
                        Toast.makeText(activity, "Error: $it", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun initRecyclerView() {
//        newsAdapter = NewsAdapter()
        binding.rvNews.apply {
            adapter = newsAdapter
            layoutManager = LinearLayoutManager(activity)
        }

    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE

    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.GONE
    }


}