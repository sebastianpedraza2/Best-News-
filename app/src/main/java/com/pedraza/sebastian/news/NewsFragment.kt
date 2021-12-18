package com.pedraza.sebastian.news

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.AbsListView
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pedraza.sebastian.news.databinding.FragmentNewsBinding
import com.pedraza.sebastian.news.presentation.adapter.NewsAdapter
import com.pedraza.sebastian.news.presentation.viewmodel.news.NewsViewModel
import com.pedraza.sebastian.news.util.Resource


class NewsFragment : Fragment(R.layout.fragment_news) {
    private lateinit var viewModel: NewsViewModel

    private lateinit var binding: FragmentNewsBinding

    private lateinit var newsAdapter: NewsAdapter

    private var country = "us"

    private var page = 1

    private var isScrolling = false

    private var isLoading = false

    private var isLastPage = false

    private var pages = 0

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

        /**
         * setting onclicklistener
         */

        newsAdapter.onArticleClickListener { article ->
            val bundle = Bundle()
            bundle.putSerializable(
                "selected_article", article
            )

            findNavController().navigate(R.id.action_newsFragment_to_infoFragment, bundle)
        }
        initRecyclerView()
        viewNewsList()
    }

    private fun viewNewsList() {
        viewModel.getTopHeadlines(country, page)
        viewModel.getNewsHeadlines().observe(viewLifecycleOwner, Observer { respose ->
            when (respose) {
                is Resource.Loading -> {
                    showProgressBar()
                }
                is Resource.Success -> {
                    hideProgressBar()
                    respose.data?.let {
                        newsAdapter.differ.submitList(it.articles.toList())

                        /**
                         * 20 es el numero de items que el api trae para cada pagina
                         * "/" entre enteros da enteros por eso verifico si hay reciduo entonces hay una ultima pagina
                         */
                        pages = if (it.totalResults % 20 == 0) {
                            it.totalResults / 20
                        } else {
                            it.totalResults / 20 + 1
                        }
                        isLastPage = page == pages
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
            addOnScrollListener(onScrollListener)
        }


    }

    private fun showProgressBar() {
        isLoading = true
        binding.progressBar.visibility = View.VISIBLE

    }

    private fun hideProgressBar() {
        isLoading = false
        binding.progressBar.visibility = View.GONE
    }


    private val onScrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            /**
             * Si se detecta scroll cambio el flag
             */
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        /**
         * Con el layout manager del rv podemos obtener 3 valores:
         * 1. list size
         * 2. # items currently showing in the screen
         * 3. current starting item position of the showing items  (form 0 to size-1)
         */
        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = binding.rvNews.layoutManager as LinearLayoutManager

            val sizeOfCurrentList = layoutManager.itemCount
            val visibleItems = layoutManager.childCount
            val topPosition = layoutManager.findFirstVisibleItemPosition()

            /**
             * If it has reached the end of the list
             */
            val hasReachedEnd = topPosition + visibleItems >= sizeOfCurrentList

            val shouldPaginate = !isLoading && isScrolling && !isLastPage && hasReachedEnd

            if (shouldPaginate) {
                page++
                viewModel.getTopHeadlines(country, page)
                isScrolling = false
            }
        }
    }


}